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

	public int character=0;  //�ַ���
	public int word=0;       //������
	public int lines=0;      //����
	public int note=0;     //ע������
	public int code=0;       //��������
	public int empty=0;      //��������
	 //����һ��map���ϱ���ͣ�õ���
    public TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
    
    //���ֱ�־��
    boolean isStop=false; //�Ƿ���ͣ�ôʱ�
    boolean isC=false;    //�Ƿ���ַ���
    boolean isW=false;    //�Ƿ�ǵ�����
    boolean isL=false;    //�Ƿ������
    boolean isA=false;    //�Ƿ��¼�����ӵ�����ͳ��
	
/**�޽��ôʱ������ͳ���ļ��ַ�����������������**/
public void calfile(String filepath)  
{
	character=word=lines=0; //��ʼ��
	 try 
	    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

	        /* ����TXT�ļ� */  
	        File filename = new File(filepath); // Ҫ��ȡ����·����input.txt�ļ� 
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
	        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	        String line =null;  
	        while ((line=br.readLine() )!= null) 
	        {  
	            character+=(line.length())+1;
	            word+=calLineWord(line);
	            lines++;
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

/**�н��ôʱ������ͳ���ļ��ַ�����������������**/
public void s_calfile(String filepath,String stoppath)
{
	character=word=lines=0;
	try 
	{
	 File file=new File(filepath); //Ҫ��ȡ���ļ�
	 BufferedReader br = new BufferedReader(new FileReader(file));
	 String line = null;  //�ļ���ȡ��
	 make_tree(stoppath); //���ɽ��ôʼ���
	 
	/**��ȡ�ļ���ͳ�Ƶ�����**/ 
	 while((line=br.readLine())!=null)
	 { 
		int temp=0;               //ͳ�Ƶ��е���
		character+=(line.length()+1); //ͳ���ַ�
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
	 System.out.println("ʣ�൥��");
	/**ͳ�����**/
	}
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**ͳ���ļ���������**/
public void calALL()
{
	note=code=empty=0;
	
}

/**���ɽ��ôʱ���**/
public void make_tree(String stoppath)
{
	try 
	{
	 File stop=new File(stoppath); //ͣ�ôʱ��ļ�
	 BufferedReader br = new BufferedReader(new FileReader(stop));
	 String line = null;  //�ļ���ȡ��
	
	 /**����ͣ�ôʱ���**/
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
	/**�������**/
	 System.out.println("���õ���");
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
		System.out.println("���ʧ��!"); 
	} 
}

/**�ݹ������µ��ļ�д����**/
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
        if(isC)
            out.write(file.getName()+",�ַ���:"+character+"\r\n"); // \r\n��Ϊ����  
            if(isW)
            out.write(file.getName()+",������:"+word+"\r\n");
            if(isL)
            out.write(file.getName()+",����:"+lines+"\r\n");
            if(isA)
            out.write(file.getName()+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ر��ļ�  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}

/**�ǵݹ������µ��ļ�д����**/
public void writefile_single(String filename,String outpath)
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
        if(isC)
        out.write(filename+",�ַ���:"+character+"\r\n"); // \r\n��Ϊ����  
        if(isW)
        out.write(filename+",������:"+word+"\r\n");
        if(isL)
        out.write(filename+",����:"+lines+"\r\n");
        if(isA)
        out.write(filename+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}

}
