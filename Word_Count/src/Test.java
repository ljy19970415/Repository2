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
    try 
    { // ��ֹ�ļ��������ȡʧ�ܣ���catch��׽���󲢴�ӡ��Ҳ����throw  

        /* ����TXT�ļ� */  
        String pathname = "file.c";   // ʹ�����·��
        File filename = new File(pathname); // Ҫ��ȡ����·����input.txt�ļ�  
        InputStreamReader reader = new InputStreamReader( new FileInputStream(filename)); // ����һ������������reader  
        BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
        String line = "";  
        line = br.readLine();  //��ȡ�ļ���һ��
        System.out.println(line); //���Զ�ȡ����
        while (line != null) 
        {  
            line = br.readLine(); // һ�ζ���һ������ 
            if(line!=null)
            System.out.println(line);
        }  
        
        /* д���ļ� */  
        File writename = new File("output.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�  
        writename.createNewFile(); // �������ļ�  
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
        out.write("�һ�д���ļ���\r\n"); // \r\n��Ϊ����  
        out.flush(); // �ѻ���������ѹ���ļ�  
        out.close(); // ���ǵùر��ļ�  
    } 
    catch (Exception e) 
    {  
        e.printStackTrace();  
    } 
}
}