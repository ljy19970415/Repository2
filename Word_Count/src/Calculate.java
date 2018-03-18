import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate    //�����ļ����ַ������ܵ�����
{

	public int character;
	public int word;
	public int lines;
	
/**�޽��ôʱ������ͳ���ļ��ַ�������������**/
public void calfile(String filepath)  
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


/**ͳ�Ƶ��е���������**/
public int calLineWord(String line)   
{
	int flag=1;
	int lineword=0; //ÿ�е�����
	  for(int i = 0; i < line.length(); i++) 
	  {  
          //System.out.println(str.charAt(i));  
		  if(line.charAt(i)==' '|line.charAt(i)==','|line.charAt(i)=='��'|line.charAt(i)=='\t')
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

/**�н��ôʱ������ͳ���ļ��ַ�������������**/
public void s_calfile(String filepath,String stoppath)
{
	character=word=lines=0;
	try 
	{
	 File file=new File(filepath); //Ҫ��ȡ���ļ�
	 File stop=new File(stoppath); //ͣ�ôʱ��ļ�
	 BufferedReader br = new BufferedReader(new FileReader(stop));
	 BufferedReader br2 = new BufferedReader(new FileReader(file));
	 String line = null;  //�ļ���ȡ��
	
	 /**����ͣ�ôʱ���**/
	 //����һ��map���ϱ���ͣ�õ���
	 TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
	 while((line=br.readLine())!=null)
	 { 
	    line.toLowerCase(); //ת��ΪСд
	 //���������ַ����ָ�Ϊ����
	    String str[] = line.split(",+|\\s+|��+");
	    //�����õ��ĵ���
	    for(String s: str)
	    {
	    	if(!s.matches(""))
	     {		
	      //�жϼ������Ƿ��Ѿ����ڸõ��ʣ���������������һ�����򽫵�����ӵ������У��Ҹ�����Ϊ1
	       System.out.println(s);
	       if(!tm.containsKey(s))
	       {
	         tm.put(s,1);
	       }
	       else
	       {
	         tm.put(s,tm.get(s)+1);
	       }
	     }
	    }
	}
	 //System.out.println(tm);
	/**�������**/
	 System.out.println("����Ϊͣ�ôʱ���");
	/**��ȡ�ļ���ͳ�Ƶ�����**/ 
	 while((line=br2.readLine())!=null)
	 { 
		int temp=0;               //ͳ�Ƶ��е���
		character+=line.length(); //ͳ���ַ�
		lines++;                  //ͳ������
	    line.toLowerCase();      //ת��ΪСд
	    String str[] = line.split(",+|\\s+|��+");
	    for(String s: str)
	    {
	    	if(!s.matches(""))
	     {		
	      //�жϼ������Ƿ��Ѿ����ڸõ��ʣ���������������һ�����򽫵�����ӵ������У��Ҹ�����Ϊ1
	       if(!tm.containsKey(s))
	       {
	    	   System.out.println(s);
	    	   temp++;
	       }
	     }
	    }
	    word+=temp;
	}
	/**ͳ�����**/
	 System.out.println("����Ϊȥ��ͣ�ôʱ��ʺ��ʣ�൥��");
	}
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**����ļ����ݺ���**/
/**���������е��ã���������Ѵ��ڵ�����ļ�������**/
public void clearfile(String outpath)
{
	try 
	{ 
		FileOutputStream out = new FileOutputStream(outpath,false); 
		out.write(new String("").getBytes()); 
		out.close(); 
	} 
	catch (Exception ex) 
	{ 
		//System.out.println("���ʧ��!"); 
	} 
}

/**�ļ�д����**/
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
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  //ʵ��׷��
        out.write(file.getName()+":\r\n"+"�ַ���:"+cal.character+"\r\n"+"������:"+cal.word+"\r\n"+"����:"+cal.lines+"\r\n"); // \r\n��Ϊ����  
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}


}
