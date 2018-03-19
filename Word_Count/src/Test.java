/*
 * ����word_count
 * ��ǰ���ܣ���д�ļ�
 * �����ߣ��׼���
 * �汾��1.0
 */
    import java.io.File;  
    import java.io.InputStreamReader;  
    import java.io.BufferedReader;  
    import java.io.BufferedWriter;  
    import java.io.FileInputStream;  
    import java.io.FileWriter;  

public class Test 
{
public static void main(String[] args)
{
	Calculate cal=new Calculate();
	boolean isInput=false;  //�Ƿ������뱻ͳ���ļ�
	boolean isReadall=false;  //�Ƿ�ݹ鴦��
	String filepath="original";
	String stoppath="original";
	String outputpath="result.txt";
		if (args.length == 0) 
		{
			System.out.println("��û��ָ���κβ�����");  
			return;  
	    }  
			//System.out.println("������������ǣ�"); 
		/**�������������������**/
	    for (int i = 0; i < args.length; i++) 
	    {  if(args[i].equals("-c"))
	    	cal.isC=true;
	       else if(args[i].equals("-w"))
	    	cal.isW=true;
	       else if(args[i].equals("-l"))
	    	cal.isL=true;
	       else if(args[i].equals("-s"))
		    isReadall=true;
	       else if(args[i].equals("-o"))
	       {
	    	   if(!isInput)
	    		   System.out.println("δ�����ͳ���ļ�������������");
	       }
	       else if(args[i].equals("-e"))
	       {
	    	   if(!isInput)
	    		   System.out.println("δ�����ͳ���ļ�������������");
	    	   else
	    		   cal.isStop=true;
	       }
	       else if(args[i-1].equals("-o"))
	       {
	    	   outputpath=args[i];
	       }
	       else if(args[i-1].equals("-e"))
	       {
	    	   stoppath=args[i];
	       }
	       else
	       {
	    	   filepath=args[i];
	    	   isInput=true;
	       }
			
	    }  

	    /**����������������ж�**/
	    File output = new File(outputpath); //���·����������������������
        if (output.exists())            
        {    
            cal.clearfile(outputpath);// ���������    
        } 
        if(isReadall)  //�еݹ�
        {
        	Readall read=new Readall();
        	read.cal=cal;
        	read.outpath=outputpath;
        	if(cal.isStop)  //��ͣ��
        	{
        		read.stoppath=stoppath;
        		read.deal(filepath);
        	}
        	else            //��ͣ��
        	{
        		read.deal(filepath);
        	}
        }
        else           //�������ļ�
        {
        	if(cal.isStop)   //��ͣ��
        	{
        		cal.s_calfile(filepath, stoppath);
        	}
        	else             //��ͣ��
        	{
        		cal.calfile(filepath);
        	}
        	cal.writefile_single(filepath,outputpath);
        }

}
}