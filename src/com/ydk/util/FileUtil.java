package com.ydk.util;


import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *@author :ydk
 *@date: 2015-7-7
 *@version: 
 */
public class FileUtil {
    private static final Logger log = Logger.getLogger(FileUtil.class.getName());
    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';
    
    /**
     * �жϳ�ʼ�ļ��Ƿ����
     *
     * @param fileName �ļ���
     */
    public static void isExists(String fileName) {

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    //��ʼ�������ļ�
                    initFile(fileName);
                } else {
                    //����ʧ��
                    log.error(new Exception("create file failure"));
                }
            }
        } catch (Exception e) {
            log.error(" *** Exception ***", e);
        }
    }

    /**
     * ��ʼ���ļ�
     *
     * @param fileName �ļ���
     */
    private static void initFile(String fileName) {

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("auth");
        Element billdata = root.addElement("balance");
        billdata.setText("100");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileWriter(new File(fileName)), format);
            writer.write(document); // ������ļ�
            writer.close();
        } catch (IOException e) {
            log.info(" *** IOException *** ", e);
        }
    }

    /**
     * ��ӡ��־
     *
     * @param fileName �ļ���
     */
    @SuppressWarnings("resource")
	public static void log(String fileName) {

        InputStream in = null;
        try {
            in = new FileInputStream(new File(fileName));
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer xmlfile = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                xmlfile.append(str);
            }
            log.info(xmlfile.toString());
            in.close();
            bf.close();
        } catch (FileNotFoundException e) {
            log.error(" *** FileNotFoundException ***", e);
        } catch (IOException e) {
            log.error(" *** IOException ***", e);
        }
    }


    /**
     * http����
     */
    public static boolean httpDownload(String httpUrl, String saveFile) {
        // ���������ļ�
        boolean reStr = false;
        log.info("---   download wav start   ---");
        log.info("---   link:" + httpUrl + "   ---");
        File file = new File(saveFile);
        if (!file.exists()) {
            file.mkdirs();
        }

        int bytesum = 0;
        int byteread = 0;

        URL url = null;
        URLConnection conn = null;
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        try {
            conn = url.openConnection();
            inStream = conn.getInputStream();
            String fileName = conn.getHeaderField("Content-Disposition");
            if(fileName!=null&&!"".equals(fileName))
			  {
				  //��ͷ�л�ȡ�ļ���
				  fileName=fileName.split(";")[1].split("=")[1].replaceAll("\"","");
			  }else{
				  //�ӵ�ַ�л�ȡ�ļ���
				  fileName=httpUrl.substring(httpUrl.lastIndexOf("/")+1);		//��ȡ�ļ�����+1��Ϊ�˰�ǰ���\ȥ��
			  }
            saveFile = saveFile + File.separator + fileName;
            fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            reStr = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close file.
            if (fs != null) {
                try {
                    fs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Close connection to server.
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("---   download wav end   ---");

        return reStr;
    }
    /**
     * �����ļ�
     * @param inputFile Դ�ļ�
     * @param outputFile Ŀ���ļ�
     * @param isOverWrite �Ƿ񸲸�
     * @throws IOException 
     */
    public static void copy(File inputFile,File outputFile,boolean isOverWrite) throws IOException{
    	if(!inputFile.exists()){
    		throw new RuntimeException(inputFile.getPath()+"Դ�ļ�������");
    	}
    	copyPri(inputFile,outputFile,isOverWrite);
    }
    
    /**
     * Ϊcopy���ݹ�
     * @param inputFile
     * @param outputFile
     * @param isOverWrite
     * @throws IOException 
     */
    private static void copyPri(File inputFile,File outputFile,boolean isOverWrite) throws IOException{
    	if(inputFile.isFile()){
    		copySimpleFile(inputFile,outputFile,isOverWrite);
    	}else{
    		if(!outputFile.exists()){
    			outputFile.mkdir();
    		}
    		
    		for(File child:inputFile.listFiles()){
        		copy(child,new File(outputFile.getPath() + File.separator + child.getName()),isOverWrite);
        	}
    	}
    }
    /**
     * �����ļ�
     * @param inputFile
     * @param outputFile
     * @param isOverWrite
     * @throws IOException
     */
    private static void copySimpleFile(File inputFile,File outputFile, boolean isOverWrite) throws IOException {
    	if(outputFile.exists()){
    		if(isOverWrite){
    			if(!outputFile.delete()){
    				throw new RuntimeException(outputFile.getPath() + "�޷����ǣ�");
    			}
    		}else{
    			return;
    		}
    	}
    	InputStream in = new FileInputStream(inputFile);
    	OutputStream out = new FileOutputStream(outputFile);
    	byte[] buffer = new byte[1024*10];
    	
    	int read = -1;
    	while((read = in.read(buffer))!=-1){
    		out.write(buffer,0,read);
    	}
    	in.close();
    	out.close();
    }
    
    /**
     * ɾ���ļ�
     * @param file
     */
    public static void deleteFile(File file){
    	if(file == null || !file.exists()){
    		return;
    	}
    	if(!file.isDirectory()){
    		boolean delFlag = file.delete();
    		if(!delFlag){
    			throw new RuntimeException(file.getPath()+"ɾ��ʧ��");
    		}else{
    			return;
    		}
    	}
    	for(File child : file.listFiles()){
    		deleteFile(child);//�ݹ�ɾ��
    	}
    }
    
    /**
     * �õ��ļ�����չ��
     * @param path
     * @return
     */
    public static String getFileExtensionName(String path){
    	String returnValue=null;
    	if(StringUtil.isNotEmpty(path)){
    		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
    		if(extIndex!=-1){
    			int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
    			if(folderIndex<=extIndex){
    				returnValue= path.substring(extIndex+1);
    			}
    		}
    	}
    	return returnValue;
    }
    
    /**
     * �����ļ�·����ȡ�ļ���
     * @param path
     * @return
     */
    public static String getFileName(String pathName){
    	String returnValue = null;
    	if(StringUtil.isNotEmpty(pathName)){
    		int separatorIndex = pathName.lastIndexOf(FOLDER_SEPARATOR);
    		returnValue = separatorIndex!=-1 ? pathName.substring(separatorIndex+1)
    				:pathName;
    	}
    	return returnValue;
    }
    
    public static void save(byte[] content,File file) throws IOException{
    	if(null == file){
    		throw new RuntimeException("Ҫ������ļ�Ϊ��");
    	}
    	if(content == null){
    		throw new RuntimeException("�ļ�������Ϊ��");
    	}
    	InputStream is = new ByteArrayInputStream(content);
    	save(is,file);
    }
    
    public static void save(InputStream streamIn,File file) throws IOException{
    	if (file == null) {
            throw new RuntimeException("�����ļ�����Ϊ��");
        }
        if (streamIn == null) {
            throw new RuntimeException("�ļ�������Ϊ��");
        }
        // �����
        OutputStream streamOut = null;
        // �ļ��в����ھʹ�����
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        streamOut = new FileOutputStream(file);
        int bytesRead = 0;
        int length = 1024*10;
        byte[] buffer = new byte[length];
        while ((bytesRead = streamIn.read(buffer, 0, length)) != -1) {
            streamOut.write(buffer, 0, bytesRead);
        }
        streamOut.close();
        streamIn.close();
    }
    public static void main(String[] args) throws IOException {
    	isExists("a.xml");
    	log("a.xml");
    	httpDownload("http://pic004.cnblogs.com/news/201210/20121019_112626_4.gif", "");
    	copy(new File("a.xml"), new File("d:/a.xml"), false);
    	
    	System.out.println(getFileExtensionName("D:\\a.text"));
    	
    	System.out.println(getFileName("d://wjfjdklsajfdlsa/fjdsaljf/fjsdkalj/456/a.txt"));
    	
    	save("����һ������".getBytes(),new File("/a.in"));
	}
}





















