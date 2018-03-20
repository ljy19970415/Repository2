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
	int left=0;            // "/*"�ĸ���
	int right=0;           // "*/"�ĸ���
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
	        File filename = new File(filepath); // Ҫ��ȡfilepathָ�����ļ� 
	        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
	        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
	        String line =null;     //������
	        while ((line=br.readLine() )!= null)    //��δ�����ļ�β
	        {  
	            character+=line.length();       //ͳ�����ַ������ݲ����ǻ��з�
	            word+=calLineWord(line);            //�����ڲ�����ͳ���е��ʣ��ۼӵ��ܴ���
	            lines++;                            //��������������lines����
	        }  
	        character+=(lines-1);      //�ӻ��з�����
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
	    	   temp++;
	       }
	     }
	    }
	    word+=temp;
	}
	 character+=(lines-1);
	/**ͳ�����**/
	}
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**ͳ���ļ���������**/
public void calAll(String filepath)
{
	note=code=empty=0;
	int flag=0; //�ж϶���ע�ͱ�־
	String note1="(\\s*)(.?)(\\s*)//.*"; // "..?..//.."ע�͵�������ʽ
	String note2="(\\s*)(/\\*)(.*)(\\*/)(\\s*)(.?)(\\s*)"; // "../*..*/..?.."ע�͵�������ʽ
	String note3="(\\s*)(.?)(\\s*)(/\\*)(.*)(\\*/)(\\s*)"; // "..?../*..*/.."ע�͵�������ʽ
	String note4="(\\s*)(.?)(\\s*)(/\\*)(.*)"; // "..?../*.."ע�͵�������ʽ
	String empty1="(\\s*)(.?)(\\s*)";      //����������ʽ
	try 
    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  
        /* ����TXT�ļ� */  
        File filename = new File(filepath); 
        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename));   
        BufferedReader br = new BufferedReader(reader);
        String line =null;  
        while ((line=br.readLine() )!= null) 
        {  
          left=right=0;
          if(flag==0)    //��ǰ�Ƕ���ע��
          {
        	  if(line.matches(note1))  // ����//
        	       note++;
        	  else if(line.matches(note2)) //  ����/*..*/
        		   note++;
        	  else if(line.matches(note3))  // ����/*..*/
        		   note++;
        	  else if(line.matches(note4))   
        	  {
        		  sub(line);      //ͳ��"/*","*/"����
        		  flag+=left;  	  //ͳ��"/*"����,flag+=����
        		  flag-=right;    //ͳ��"*/"����,flag-=����
        		  if(flag==0)             //�պϣ���������ע����������Ϊ������
        			 code++;
        		  else                    //����/*..�Ҳ��պϣ�Ϊ����ע�Ϳ�ʼ
        			 note++;
        	  }
        	  else if(line.matches(empty1))  //������б��ʽ��Ϊ����
        		  empty++;
        	  else                          //���϶�������Ϊ������
        		  code++;
          }
          else       //��ǰ�Ƕ���ע��
          { 
              sub(line);      //ͳ��"/*","*/"����
    		  flag+=left;  	  //ͳ��"/*"����,flag+=����
    		  flag-=right;    //ͳ��"*/"����,flag-=����
    		  if(flag==0)       //����ע�͵��˽���
    		  {
    			  if(line.matches("(.*)(\\*/)(\\s*)(.?)(\\s*)"))  //��*/��ֻ�����1���������
    			  {
    				 note++;
    			  }
    			  else                  //��*/���ж���1���������
    			  {
    				 code++;
    			  }
    		  }  
    		  else              //��ǰ���Ƕ���ע��
    			  note++;
          }
        }  
    } 
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
	
}

/**ͳ��ע�ͷ��Ÿ�������**/
public void sub(String string) 
{
	int i=0;
	while (i <= string.length()-2 ) 
	{
	if (string.substring(i, i + 2).equals("/*")) 
	{
		left++;
		i+=2;
	}
	else if(string.substring(i, i + 2).equals("*/"))
	{
		right++;
		i+=2;
	}
	else
		i++;
	}
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
        BufferedWriter out = new BufferedWriter(new FileWriter(output,true));  //ʵ��׷��д�ļ�
        if(isC)
        {
        out.write(file.getName()+",�ַ���:"+character+"\r\n"); 
        System.out.println(file.getName()+",�ַ���:"+character+"\r\n");
        }
        if(isW)
        {
        out.write(file.getName()+",������:"+word+"\r\n");
        System.out.println(file.getName()+",������:"+word+"\r\n");
        }
        if(isL)
        {
        out.write(file.getName()+",����:"+lines+"\r\n");
        System.out.println(file.getName()+",����:"+lines+"\r\n");
        }
        if(isA)
        {
        out.write(file.getName()+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        System.out.println(file.getName()+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        }
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
        {
        out.write(filename+",�ַ���:"+character+"\r\n"); 
        System.out.println(filename+",�ַ���:"+character+"\r\n");
        }
        if(isW)
        {
        out.write(filename+",������:"+word+"\r\n");
        System.out.println(filename+",������:"+word+"\r\n");
        }
        if(isL)
        {
        out.write(filename+",����:"+lines+"\r\n");
        System.out.println(filename+",����:"+lines+"\r\n");
        }
        if(isA)
        {
        out.write(filename+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        System.out.println(filename+",������/����/ע����:"+code+"/"+empty+"/"+note+"\r\n");
        }
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
    }
    catch (Exception e) 
	    {  
	        e.printStackTrace();  
	    } 	
}

}
