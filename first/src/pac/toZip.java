package pac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class toZip {
	
	//Test the mothod
	public static void main(String[] args) throws Exception {
		toZip to=new toZip("D:/data", "D:/test");
		to.zip();
	}
	//
    private String zipFileName;      // Ŀ�ĵ�Zip�ļ�
    private String sourceFileName;   //Դ�ļ�����ѹ�����ļ����ļ��У�
    
    public toZip(String zipFileName,String sourceFileName)
    {
        this.zipFileName=zipFileName;
        this.sourceFileName=sourceFileName;
    }
    
    public void zip() throws Exception
    {
        //File zipFile = new File(zipFileName);
        System.out.println("ѹ����...");
        
        //����zip�����
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));
        
        //�������������
        BufferedOutputStream bos = new BufferedOutputStream(out);
        
        File sourceFile = new File(sourceFileName);
        
        //���ú���
        compress(out,bos,sourceFile,sourceFile.getName());
        
        bos.close();
        out.close();
        System.out.println("ѹ�����");
        
    }
    
    public void compress(ZipOutputStream out,BufferedOutputStream bos,File sourceFile,String base) throws Exception
    {
        //���·��ΪĿ¼���ļ��У�
        if(sourceFile.isDirectory())
        {
        
            //ȡ���ļ����е��ļ��������ļ��У�
            File[] flist = sourceFile.listFiles();
            
            if(flist.length==0)//����ļ���Ϊ�գ���ֻ����Ŀ�ĵ�zip�ļ���д��һ��Ŀ¼�����
            {
                System.out.println(base+"/");
                out.putNextEntry(  new ZipEntry(base+"/") );
            }
            else//����ļ��в�Ϊ�գ���ݹ����compress���ļ����е�ÿһ���ļ������ļ��У�����ѹ��
            {
                for(int i=0;i<flist.length;i++)
                {
                    compress(out,bos,flist[i],base+"/"+flist[i].getName());
                }
            }
        }
        else//�������Ŀ¼���ļ��У�����Ϊ�ļ�������д��Ŀ¼����㣬֮���ļ�д��zip�ļ���
        {
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);
            
            int tag;
            System.out.println(base);
            //��Դ�ļ�д�뵽zip�ļ���
            while((tag=bis.read())!=-1)
            {
                out.write(tag);
            }
            bis.close();
            fos.close();
            
        }
    }
}