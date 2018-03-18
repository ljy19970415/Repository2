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

	public int character;
	public int word;
	public int lines;
	
/**无禁用词表情况下统计文件字符数单词数函数**/
public void calfile(String filepath)  
{
	character=word=lines=0; //初始化
	 try 
	    { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

	        /* 读入TXT文件 */  
	        File filename = new File(filepath); // 要读取以上路径的input.txt文件  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line = "";  
	        while (line != null) 
	        {  
	            line = br.readLine();  //读取文件第一行
	            if(line!=null)
	            {
	            character+=line.length();
	            word+=calLineWord(line);
	            lines++;
	            }
	            else
	            break;
	        }  
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

/**有禁用词表情况下统计文件字符数单词数函数**/
public void s_calfile(String filepath,String stoppath)
{
	character=word=lines=0;
	try 
	{
	 File file=new File(filepath); //要读取的文件
	 File stop=new File(stoppath); //停用词表文件
	 BufferedReader br = new BufferedReader(new FileReader(stop));
	 BufferedReader br2 = new BufferedReader(new FileReader(file));
	 String line = null;  //文件读取行
	
	 /**制作停用词表树**/
	 //定义一个map集合保存停用单词
	 TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
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
	       System.out.println(s);
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
	 //System.out.println(tm);
	/**制作完毕**/
	 System.out.println("以上为停用词表单词");
	/**读取文件，统计单词数**/ 
	 while((line=br2.readLine())!=null)
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
	    	   System.out.println(s);
	    	   temp++;
	       }
	     }
	    }
	    word+=temp;
	}
	/**统计完毕**/
	 System.out.println("以上为去掉停用词表单词后的剩余单词");
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
		//System.out.println("清空失败!"); 
	} 
}

/**文件写函数**/
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
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  //实现追加
        out.write(file.getName()+":\r\n"+"字符数:"+cal.character+"\r\n"+"单词数:"+cal.word+"\r\n"+"行数:"+cal.lines+"\r\n"); // \r\n即为换行  
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后记得关闭文件  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}


}
