import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Calculate    //�����ļ����ַ������ܵ�����
{

public int calcCharacter(String filepath)  //�����ļ����ַ���
{
	int character=0; //�ļ����ַ���
	 try 
	    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

	        /* ����TXT�ļ� */  
	        File filename = new File(filepath); // Ҫ��ȡ����·����input.txt�ļ�  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
	        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	        String line = "";  
	        line = br.readLine();  //��ȡ�ļ���һ��
	        character=line.length();
	        while (line != null) 
	        {  
	            line = br.readLine(); // һ�ζ���һ������ 
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

public int calcWord(String filepath) //ͳ���ļ��ܵ�����
{
	  int word=0; //�ܴ���
	  try 
	    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

	        /* ����TXT�ļ� */  
	        File filename = new File(filepath); // Ҫ��ȡ����·����input.txt�ļ�  
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
	        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	        String line = "";  
	        line = br.readLine();  //��ȡ�ļ���һ��
	        word=calLineWord(line);
	        while (line != null) 
	        {  
	            line = br.readLine(); // һ�ζ���һ������ 
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

public int calLineWord(String line)   //ͳ�Ƶ��е�����
{
	int flag=1;
	int lineword=0; //ÿ�е�����
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)=='��')
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
