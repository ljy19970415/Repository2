import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Calculate    //计算文件总字符数，总单词数
{

	public int character;
	public int word;
	public int lines;
public void calfile(String filepath)  //计算文件总字符数
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



public int calLineWord(String line)   //统计单行单词数
{
	int flag=1;
	int lineword=0; //每行单词数
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)==','|line.charAt(i)=='，')
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

public void clearfile(String outpath)
{
	try 
	{ 
		FileOutputStream out = new FileOutputStream(outpath,false); 
		out.write(new String("").getBytes()); 
		out.close(); 
		System.out.println( "清空发送邮件日志成功!");
	} 
	catch (Exception ex) 
	{ 
		System.out.println("清空文件的内容失败,因为没有发送邮件日志文件!"); 
	} 
}

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
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  
        out.write(file.getName()+":\r\n"+"字符数:"+cal.character+"\r\n"+"单词数:"+cal.word+"\r\n"+"行数:"+cal.lines+"\r\n"); // \r\n即为换行  
        out.flush(); // 把缓存区内容压入文件  
        out.close(); // 最后记得关闭文件  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}


public boolean match(String filename,String temp) //对比文件名是否符合检索要求
{
	
	String type=filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
	if(type.equals(temp))
	{
		return true;
	}
	else 
	{
		return false;
	}
}

}
