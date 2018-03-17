import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Calculate    //计算文件总字符数，总单词数
{

public int calcCharacter(String filepath)  //计算文件总字符数
{
	int character=0; //文件总字符数
	 try 
	    { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

	        /* 读入TXT文件 */  
	        File filename = new File(filepath); // 要读取以上路径的input.txt文件  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line = "";  
	        line = br.readLine();  //读取文件第一行
	        character=line.length();
	        while (line != null) 
	        {  
	            line = br.readLine(); // 一次读入一行数据 
	            if(line!=null)
	            {
	            character+=line.length();
	            }
	        }  
	    } 
	    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 
	 return character;
}

public int calcWord(String filepath) //统计文件总单词数
{
	  int word=0; //总词数
	  try 
	    { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw  

	        /* 读入TXT文件 */  
	        File filename = new File(filepath); // 要读取以上路径的input.txt文件  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // 建立一个输入流对象reader  
	        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
	        String line = "";  
	        line = br.readLine();  //读取文件第一行
	        word=calLineWord(line);
	        while (line != null) 
	        {  
	            line = br.readLine(); // 一次读入一行数据 
	            if(line!=null)
	            {
	            word+=calLineWord(line);
	            }
	        }  
	    } 
	    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 
	  return word;	 
}

public int calLineWord(String line)   //统计单行单词数
{
	int flag=1;
	int lineword=0; //每行单词数
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)=='，')
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
