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
	    
	    public void test(String fileDir) 
	    {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
	        if (files == null) 
	        {
	        	// 如果目录为空，直接退出  
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
	                test(f.getAbsolutePath());  
	            }  
	        }  
	        
	        for (File f1 : fileList) 
	        {   
	        	Calculate cal=new Calculate(); //生成统计类
	        	String filepath=f1.getAbsolutePath(); //生成该文本文件绝对路径
	        	cal.calfile(filepath);    //统计该文本文件字符数，单词数，行数
	        	cal.writefile(f1, cal, outpath);  //将结果写入指定文件
	        }  
	    }	     
}
