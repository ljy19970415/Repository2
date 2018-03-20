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
	    String stoppath; //���ô�·��
	    Calculate cal=new Calculate();  //ͳ����
	    
	    /**����ݹ����·��**/
	    public void deal(String filepath)  /**ֻ�ܴ����ͨ��������**/
	    {
	    	gettemp(filepath);  //��ȡ�û�Ҫ�������ļ���׺
	    	if(filepath.indexOf('\\')!=-1) //���ļ�����б��
	    	{
	    		filepath=filepath.substring(0,filepath.lastIndexOf("\\")).toLowerCase(); //��ȡ���һ��б��֮ǰ��·��
	    	}
	    	else
	    	{
	    		filepath=".";               //����ֱ������wc.exe�����ļ���
	    	}
	    	find(filepath);                 //�����ļ���
	    }
	    	    
	    /**�ݹ�����ļ������ͳ�ƽ��**/
	    public void find(String fileDir)    //�������Ϊ�ļ���·��
	    {  
	        List<File> fileList = new ArrayList<File>();  //��̬���鱣����������ı��ļ�
	        File file = new File(fileDir);  
	        File[] files = file.listFiles();// ��ȡĿ¼�µ������ļ����ļ���  
        	
	        if (files == null)      //���������ļ�
	        {
	        	System.out.println("���ļ���·����");
	            return;  
	        }  
	        
	        for (File f : files)    // ����Ŀ¼�µ������ļ�  
	        {  
	            if (f.isFile())     //�����ļ�������������
	            {  
	                fileList.add(f);  
	            } 
	            else if (f.isDirectory())  //�����ļ�����ݹ����
	            {  
	                find(f.getAbsolutePath());  
	            }  
	        }  
	        
	        for (File f1 : fileList)      //�����������ļ�����
	        {   
	        	if(match(f1.getName()))  //���ļ���׺�������û�Ҫ����ͳ��
	        	{
	        	String filepath=f1.getAbsolutePath(); //���ɸ��ı��ļ�����·��
	            //ͣ���ж�
	        	if(cal.isStop)    //��ָ��ͣ�ôʱ�
	        		cal.s_calfile(filepath,stoppath);  //ͣ�÷�ʽͳ��
	        	else              //����ͣ�ôʱ�
	        	    cal.calfile(filepath);             //��ͣ�÷�ʽͳ��
	        	if(cal.isA)
	        		cal.calAll(filepath);
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
