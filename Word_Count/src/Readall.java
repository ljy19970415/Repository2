import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;  
import java.util.List;     
import java.io.BufferedWriter;  
import java.io.FileWriter;  

public class Readall   //�ݹ��ȡ�ı��ļ�
{
	    String outpath; //������·��
	    String temp;   //�û�Ҫ��ĺ�׺��
	    
	    /**����ݹ����·��**/
	    public void deal(String filepath)
	    {
	    	gettemp(filepath);  //��ȡ�û�Ҫ�������ļ���׺
	    	if(filepath.matches("^\\*.*"))  //���������ļ�·������"*.c"
	    	{
	    		filepath=".";               //ֱ������wc.exe�����ļ���
	    	}
	    	else
	    	{
	    		filepath=filepath.substring(0,filepath.lastIndexOf("\\*")).toLowerCase(); //�����ȡ"*.c"֮ǰ��·��
	    	}
	    	find(filepath);                 //�����ļ���
	    }
	    	    
	    /**�ݹ�����ļ������ͳ�ƽ��**/
	    public void find(String fileDir) 
	    {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// ��ȡĿ¼�µ������ļ����ļ���  
        	Calculate cal=new Calculate(); //����ͳ����
        	
	        if (files == null) 
	        {
	        	System.out.println("���ļ���·����");
	            return;  
	        }  
	        // ����Ŀ¼�µ������ļ�  
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
	        	String filepath=f1.getAbsolutePath(); //���ɸ��ı��ļ�����·��
	        	cal.calfile(filepath);    //ͳ�Ƹ��ı��ļ��ַ�����������������
	        	cal.writefile(f1, cal, outpath);  //�����д��ָ���ļ�
	        	}
	        }  
	    }	     
	    
	    
	    /**�Ա��ļ���׺���Ƿ���ϼ���Ҫ��**/
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
	    
	    /**����û�Ҫ�������ļ���׺��**/
	    /**���������л���û�����ļ�������ʱʹ��**/
	    public void gettemp(String filename) 
	    {
	    	temp=filename.substring(filename.lastIndexOf(".")+1,filename.length()).toLowerCase();
	    }
	    
}
