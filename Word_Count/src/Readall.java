import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;  
import java.util.List;     
import java.io.BufferedWriter;  
import java.io.FileWriter;  

public class Readall   //递归读取文本文件
{
	    String outpath; //结果输出路径
	    String temp;   //用户要求的后缀名
	    
	    /**处理递归检索路径**/
	    public void deal(String filepath)
	    {
	    	gettemp(filepath);  //获取用户要检索的文件后缀
	    	if(filepath.matches("^\\*.*"))  //如果输入的文件路径形如"*.c"
	    	{
	    		filepath=".";               //直接搜索wc.exe所在文件夹
	    	}
	    	else
	    	{
	    		filepath=filepath.substring(0,filepath.lastIndexOf("\\*")).toLowerCase(); //否则获取"*.c"之前的路径
	    	}
	    	find(filepath);                 //遍历文件夹
	    }
	    	    
	    /**递归检索文件并输出统计结果**/
	    public void find(String fileDir) 
	    {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        	Calculate cal=new Calculate(); //生成统计类
        	
	        if (files == null) 
	        {
	        	System.out.println("非文件夹路径！");
	            return;  
	        }  
	        // 遍历目录下的所有文件  
	        for (File f : files) 
	        {  
	            if (f.isFile()) 
	            {  
	                fileList.add(f);  
	            } 
	            else if (f.isDirectory()) 
	            {  
	                find(f.getAbsolutePath());  
	            }  
	        }  
	        
	        for (File f1 : fileList) 
	        {   
	        	if(match(f1.getName()))
	        	{
	        	String filepath=f1.getAbsolutePath(); //生成该文本文件绝对路径
	        	cal.calfile(filepath);    //统计该文本文件字符数，单词数，行数
	        	cal.writefile(f1, cal, outpath);  //将结果写入指定文件
	        	}
	        }  
	    }	     
	    
	    
	    /**对比文件后缀名是否符合检索要求**/
	    public boolean match(String filename) 
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
	    
	    /**获得用户要检索的文件后缀名**/
	    /**在主函数中获得用户输入的检索类型时使用**/
	    public void gettemp(String filename) 
	    {
	    	temp=filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
	    }
	    
}
