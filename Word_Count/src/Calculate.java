import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Calculate    //�����ļ����ַ������ܵ�����
{

	public int character;
	public int word;
	public int lines;
public void calfile(String filepath)  //�����ļ����ַ���
{
	character=word=lines=0; //��ʼ��
	 try 
	    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

	        /* ����TXT�ļ� */  
	        File filename = new File(filepath); // Ҫ��ȡ����·����input.txt�ļ�  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
	        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	        String line = "";  
	        while (line != null) 
	        {  
	            line = br.readLine();  //��ȡ�ļ���һ��
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



public int calLineWord(String line)   //ͳ�Ƶ��е�����
{
	int flag=1;
	int lineword=0; //ÿ�е�����
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)==','|line.charAt(i)=='��')
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
		System.out.println( "��շ����ʼ���־�ɹ�!");
	} 
	catch (Exception ex) 
	{ 
		System.out.println("����ļ�������ʧ��,��Ϊû�з����ʼ���־�ļ�!"); 
	} 
}

public void writefile(File file,Calculate cal,String outpath)
{
	try
	{
		/* д���ļ� */  
        File output = new File(outpath); // ���·�������û����Ҫ����һ���µ�����ļ�
        if (!output.exists()) 
        {    
            output.createNewFile();// �������򴴽�    
        }  
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  
        out.write(file.getName()+":\r\n"+"�ַ���:"+cal.character+"\r\n"+"������:"+cal.word+"\r\n"+"����:"+cal.lines+"\r\n"); // \r\n��Ϊ����  
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}


public boolean match(String filename,String temp) //�Ա��ļ����Ƿ���ϼ���Ҫ��
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
