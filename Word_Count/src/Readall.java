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
	    
	    public void test(String fileDir) 
	    {  
	        List<File> fileList = new ArrayList<File>();  
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// ��ȡĿ¼�µ������ļ����ļ���  
	        if (files == null) 
	        {
	        	// ���Ŀ¼Ϊ�գ�ֱ���˳�  
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
	                test(f.getAbsolutePath());  
	            }  
	        }  
	        
	        for (File f1 : fileList) 
	        {   
	        	Calculate cal=new Calculate(); //����ͳ����
	        	String filepath=f1.getAbsolutePath(); //���ɸ��ı��ļ�����·��
	        	cal.calfile(filepath);    //ͳ�Ƹ��ı��ļ��ַ�����������������
	        	cal.writefile(f1, cal, outpath);  //�����д��ָ���ļ�
	        }  
	    }	     
}
