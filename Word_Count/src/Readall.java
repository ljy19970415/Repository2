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
	    String stoppath; //禁用词路径
	    Calculate cal=new Calculate();  //统计类
	    
	    /**处理递归检索路径**/
	    public void deal(String filepath)  /**只能处理带通配符的情况**/
	    {
	    	gettemp(filepath);  //获取用户要检索的文件后缀
	    	if(filepath.indexOf('\\')!=-1) //若文件名含斜杠
	    	{
	    		filepath=filepath.substring(0,filepath.lastIndexOf("\\")).toLowerCase(); //获取最后一个斜杠之前的路径
	    	}
	    	else
	    	{
	    		filepath=".";               //否则直接搜索wc.exe所在文件夹
	    	}
	    	find(filepath);                 //遍历文件夹
	    }
	    	    
	    /**递归检索文件并输出统计结果**/
	    public void find(String fileDir)    //输入参数为文件夹路径
	    {  
	        List<File> fileList = new ArrayList<File>();  //动态数组保存检索到的文本文件
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        	
	        if (files == null)      //若里面无文件
	        {
	        	System.out.println("非文件夹路径！");
	            return;  
	        }  
	        
	        for (File f : files)    // 遍历目录下的所有文件  
	        {  
	            if (f.isFile())     //若是文件则加入待处理集合
	            {  
	                fileList.add(f);  
	            } 
	            else if (f.isDirectory())  //若是文件夹则递归检索
	            {  
	                find(f.getAbsolutePath());  
	            }  
	        }  
	        
	        for (File f1 : fileList)      //遍历待处理文件集合
	        {   
	        	if(match(f1.getName()))  //若文件后缀名满足用户要求，则统计
	        	{
	        	String filepath=f1.getAbsolutePath(); //生成该文本文件绝对路径
	            //停用判断
	        	if(cal.isStop)    //若指定停用词表
	        		cal.s_calfile(filepath,stoppath);  //停用方式统计
	        	else              //若无停用词表
	        	    cal.calfile(filepath);             //非停用方式统计
	        	if(cal.isA)
	        		cal.calAll(filepath);
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
