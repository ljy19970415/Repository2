import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate    //计算文件总字符数，总单词数
{

	public int character=0;  //字符数
	public int word=0;       //单词数
	public int lines=0;      //行数
	public int note=0;     //注释行数
	public int code=0;       //代码行数
	public int empty=0;      //空行行数
	int left=0;            // "/*"的个数
	int right=0;           // "*/"的个数
	 //定义一个map集合保存停用单词
    public TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
    
    //各种标志量
    boolean isStop=false; //是否有停用词表
    boolean isC=false;    //是否记字符数
    boolean isW=false;    //是否记单词数
    boolean isL=false;    //是否记行数
    boolean isA=false;    //是否记录更复杂的行类统计
	
/**无禁用词表情况下统计文件字符数单词数行数函数**/
public void calfile(String filepath)  
{
	character=word=lines=0; //初始化
	 try 
	    { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

	        /* 读入TXT文件 */  
	        File filename = new File(filepath); // 要读取filepath指定的文件 
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line =null;     //读入行
	        while ((line=br.readLine() )!= null)    //当未读至文件尾
	        {  
	            character+=line.length();       //统计行字符数，暂不考虑换行符
	            word+=calLineWord(line);            //调用内部函数统计行单词，累加到总词数
	            lines++;                            //代表行数的属性lines自增
	        }  
	        character+=(lines-1);      //加换行符个数
	    } 
	    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 
}


/**统计单行单词数函数**/
public int calLineWord(String line)   
{
	int flag=1;
	int lineword=0; //每行单词数
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)==','|line.charAt(i)=='，'|line.charAt(i)=='\t')
		  {
			  flag=1;
		  }
		  else
		  {
			  if(flag==1)
				  {
				    lineword++;
				    flag=0;
				  }
		  }
      }
    return lineword;
}

/**有禁用词表情况下统计文件字符数单词数行数函数**/
public void s_calfile(String filepath,String stoppath)
{
	character=word=lines=0;
	try 
	{
	 File file=new File(filepath); //要读取的文件
	 BufferedReader br = new BufferedReader(new FileReader(file));
	 String line = null;  //文件读取行
	 make_tree(stoppath); //生成禁用词集合
	 
	/**读取文件，统计单词数**/ 
	 while((line=br.readLine())!=null)
	 { 
		int temp=0;               //统计单行单词
		character+=line.length(); //统计字符
		lines++;                  //统计行数
	    line.toLowerCase();      //转换为小写
	    String str[] = line.split(",+|\\s+|，+");
	    for(String s: str)
	    {
	    	if(!s.matches(""))
	     {		
	      //判断集合中是否已经存在该单词，如果存在则个数加一，否则将单词添加到集合中，且个数置为1
	       if(!tm.containsKey(s))
	       {
	    	   temp++;
	       }
	     }
	    }
	    word+=temp;
	}
	 character+=(lines-1);
	/**统计完毕**/
	}
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**统计文件各类行数**/
public void calAll(String filepath)
{
	note=code=empty=0;
	int flag=0; //判断多行注释标志
	String note1="(\\s*)(.?)(\\s*)//.*"; // "..?..//.."注释的正则表达式
	String note2="(\\s*)(/\\*)(.*)(\\*/)(\\s*)(.?)(\\s*)"; // "../*..*/..?.."注释的正则表达式
	String note3="(\\s*)(.?)(\\s*)(/\\*)(.*)(\\*/)(\\s*)"; // "..?../*..*/.."注释的正则表达式
	String note4="(\\s*)(.?)(\\s*)(/\\*)(.*)"; // "..?../*.."注释的正则表达式
	String empty1="(\\s*)(.?)(\\s*)";      //空行正则表达式
	try 
    { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  
        /* 读入TXT文件 */  
        File filename = new File(filepath); 
        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename));   
        BufferedReader br = new BufferedReader(reader);
        String line =null;  
        while ((line=br.readLine() )!= null) 
        {  
          left=right=0;
          if(flag==0)    //当前非多行注释
          {
        	  if(line.matches(note1))  // 形如//
        	       note++;
        	  else if(line.matches(note2)) //  形如/*..*/
        		   note++;
        	  else if(line.matches(note3))  // 形如/*..*/
        		   note++;
        	  else if(line.matches(note4))   
        	  {
        		  sub(line);      //统计"/*","*/"个数
        		  flag+=left;  	  //统计"/*"个数,flag+=个数
        		  flag-=right;    //统计"*/"个数,flag-=个数
        		  if(flag==0)             //闭合，但不符合注释行条件，为代码行
        			 code++;
        		  else                    //形如/*..且不闭合，为多行注释开始
        			 note++;
        	  }
        	  else if(line.matches(empty1))  //满足空行表达式则为空行
        		  empty++;
        	  else                          //以上都不满足为代码行
        		  code++;
          }
          else       //当前是多行注释
          { 
              sub(line);      //统计"/*","*/"个数
    		  flag+=left;  	  //统计"/*"个数,flag+=个数
    		  flag-=right;    //统计"*/"个数,flag-=个数
    		  if(flag==0)       //多行注释到此结束
    		  {
    			  if(line.matches("(.*)(\\*/)(\\s*)(.?)(\\s*)"))  //若*/后只有最多1个代码符号
    			  {
    				 note++;
    			  }
    			  else                  //若*/后有多于1个代码符号
    			  {
    				 code++;
    			  }
    		  }  
    		  else              //当前仍是多行注释
    			  note++;
          }
        }  
    } 
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**统计注释符号个数函数**/
public void sub(String string) 
{
	int i=0;
	while (i <= string.length()-2 ) 
	{
	if (string.substring(i, i + 2).equals("/*")) 
	{
		left++;
		i+=2;
	}
	else if(string.substring(i, i + 2).equals("*/"))
	{
		right++;
		i+=2;
	}
	else
		i++;
	}
}

/**生成禁用词表函数**/
public void make_tree(String stoppath)
{
	try 
	{
	 File stop=new File(stoppath); //停用词表文件
	 BufferedReader br = new BufferedReader(new FileReader(stop));
	 String line = null;  //文件读取行
	
	 /**制作停用词表树**/
	 while((line=br.readLine())!=null)
	 { 
	    line.toLowerCase(); //转换为小写
	 //将读到的字符串分割为单词
	    String str[] = line.split(",+|\\s+|，+");
	    //遍历得到的单词
	    for(String s: str)
	    {
	    	if(!s.matches(""))
	     {		
	      //判断集合中是否已经存在该单词，如果存在则个数加一，否则将单词添加到集合中，且个数置为1
	       if(!tm.containsKey(s))
	       {
	         tm.put(s,1);
	       }
	       else
	       {
	         tm.put(s,tm.get(s)+1);
	       }
	     }
	    }
	}
	/**制作完毕**/
	}

    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}


/**清除文件内容函数**/
/**在主函数中调用，用于清空已存在的输出文件的内容**/
public void clearfile(String outpath)
{
	try 
	{ 
		FileOutputStream out = new FileOutputStream(outpath,false); 
		out.write(new String("").getBytes()); 
		out.close(); 
	} 
	catch (Exception ex) 
	{ 
		System.out.println("清空失败!"); 
	} 
}

/**递归搜索下的文件写函数**/
public void writefile(File file,Calculate cal,String outpath)  
{
	try
	{
		/* 写入文件 */  
        File output = new File(outpath); // 相对路径，如果没有则要建立一个新的输出文件
        if (!output.exists())            
        {    
            output.createNewFile();// 不存在则创建    
        } 
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  //实现追加写文件
        if(isC)
        {
        out.write(file.getName()+",字符数:"+character+"\r\n"); 
        System.out.println(file.getName()+",字符数:"+character+"\r\n");
        }
        if(isW)
        {
        out.write(file.getName()+",单词数:"+word+"\r\n");
        System.out.println(file.getName()+",单词数:"+word+"\r\n");
        }
        if(isL)
        {
        out.write(file.getName()+",行数:"+lines+"\r\n");
        System.out.println(file.getName()+",行数:"+lines+"\r\n");
        }
        if(isA)
        {
        out.write(file.getName()+",代码行/空行/注释行:"+code+"/"+empty+"/"+note+"\r\n");
        System.out.println(file.getName()+",代码行/空行/注释行:"+code+"/"+empty+"/"+note+"\r\n");
        }
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后关闭文件  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}

/**非递归搜索下的文件写函数**/
public void writefile_single(String filename,String outpath)
{
	try
	{
		/* 写入文件 */
        File output = new File(outpath); // 相对路径，如果没有则要建立一个新的输出文件
        if (!output.exists())            
        {    
            output.createNewFile();// 不存在则创建    
        } 
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  //实现追加
        if(isC)
        {
        out.write(filename+",字符数:"+character+"\r\n"); 
        System.out.println(filename+",字符数:"+character+"\r\n");
        }
        if(isW)
        {
        out.write(filename+",单词数:"+word+"\r\n");
        System.out.println(filename+",单词数:"+word+"\r\n");
        }
        if(isL)
        {
        out.write(filename+",行数:"+lines+"\r\n");
        System.out.println(filename+",行数:"+lines+"\r\n");
        }
        if(isA)
        {
        out.write(filename+",代码行/空行/注释行:"+code+"/"+empty+"/"+note+"\r\n");
        System.out.println(filename+",代码行/空行/注释行:"+code+"/"+empty+"/"+note+"\r\n");
        }
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后记得关闭文件  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}

}
