/*
 * 程序：word_count
 * 当前功能：读写文件
 * 创作者：雷佳谕
 * 版本：1.0
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
	boolean isInput=false;  //是否已输入被统计文件
	boolean isReadall=false;  //是否递归处理
	String filepath="original";
	String stoppath="original";
	String outputpath="result.txt";
		if (args.length == 0) 
		{
			System.out.println("您没有指定任何参数！");  
			return;  
	    }  
			//System.out.println("您输入的命令是："); 
		/**根据输入参数调整设置**/
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
	    		   System.out.println("未输入待统计文件！请重新输入");
	       }
	       else if(args[i].equals("-e"))
	       {
	    	   if(!isInput)
	    		   System.out.println("未输入待统计文件！请重新输入");
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

	    /**根据输入参数进行判断**/
	    File output = new File(outputpath); //相对路径，如果有则清除已有内容
        if (output.exists())            
        {    
            cal.clearfile(outputpath);// 存在则清除    
        } 
        if(isReadall)  //有递归
        {
        	Readall read=new Readall();
        	read.cal=cal;
        	read.outpath=outputpath;
        	if(cal.isStop)  //有停用
        	{
        		read.stoppath=stoppath;
        		read.deal(filepath);
        	}
        	else            //无停用
        	{
        		read.deal(filepath);
        	}
        }
        else           //处理单个文件
        {
        	if(cal.isStop)   //有停用
        	{
        		cal.s_calfile(filepath, stoppath);
        	}
        	else             //无停用
        	{
        		cal.calfile(filepath);
        	}
        	cal.writefile_single(filepath,outputpath);
        }

}
}