package common;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.*;  
import java.awt.image.*;  

import javax.imageio.ImageIO;  

import com.sun.image.codec.jpeg.*;

import listener.ServletContextListener;

import org.apache.commons.logging.LogFactory;

public class CommonFunction {
	
	//图片压缩
	private Image img;  
    private int width;  
	private int height;

	//保存文件
	public String saveFile(String oldFileName,String fileName,File srcFile,String pathName, String fileType)
	{
		try {
			
    		String path =ServletContextListener.getWEB_ABSOLUTE_PATH() + Constants.UPLOAD_PATH +"/"+pathName+"/";
    		
    		
    		//定义允许上传的文件扩展名   
    		HashMap<String, String> extMap = new HashMap<String, String>();   
    		extMap.put("image", "gif,jpg,jpeg,png,bmp");   
    		extMap.put("media", "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    		extMap.put("file", "doc,docx");
    		extMap.put("ppt", "ppt,pptx");
    		
    		//允许最大上传文件大小 struts.xml struts.multipart.maxSize=3G   
    		long maxSize = 3000000000l; 
    		
    		File file = new File(path);
    		
    		
    		if (!file.exists()  && !file.isDirectory()) {
    			file.mkdir(); 
    		} 
    		//检查目录写入权限   
    		if (!file.canWrite()) {   
    			return "上传目录没有写入权限。";   
    		}
    		
    		//检查扩展名   
    		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();   
    		if(!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)){   
    		    return "上传文件扩展名是不允许的扩展名。只允许" + extMap.get(fileType) + "格式。";   
    		}   
    		//检查文件大小   
    		if (file.length() > maxSize) {   
		        return "上传文件大小超过限制。";   
    		}    
    		
    		File[] tempFile = file.listFiles();
    		
			for (int i = 0; i < tempFile.length; i++) {
				if (tempFile[i].getName().equals(oldFileName)) {
					
					File file1 = new File(path + tempFile[i].getName());
					try {
						if (file1.isFile()) {
							file1.delete();
							break;
						}
					} catch (Exception ex) {
						throw new Exception(ex);
					}
				}
			}
			
			tempFile = file.listFiles();
    		
			for (int i = 0; i < tempFile.length; i++) {
				if (tempFile[i].getName().equals(fileName)) {
					
					File file1 = new File(path + tempFile[i].getName());
					try {
						if (file1.isFile()) {
							file1.delete();
							break;
						}
					} catch (Exception ex) {
						throw new Exception(ex);
					}
				}
			}
			
			FileOutputStream outPut = new FileOutputStream(new File(path+fileName));
			InputStream inputStream = new FileInputStream(srcFile);
			byte[] data = new byte[4048];
			int i = 0;
			while((i=inputStream.read(data))>0)
			{
				outPut.write(data, 0, i);
			}
			
			outPut.flush();
			outPut.close();
			//文件压缩
			if(fileType.equals(Constants.UPLOAD_IMAGE)){
				
//				ImageCompressUtil.saveMinPhoto(path+fileName, 139, 0.9d);
				
				compressImg(path+fileName);
				
			}
    		
		} catch (Exception ex) {
			LogFactory.getLog(getClass()).info("policyBizI-saveFile:", ex);
		}
		
		return "";
	}
	
	//保存文件(不压缩)
	public String saveFileWithoutCompress(String oldFileName,String fileName,File srcFile,String pathName, String fileType)
	{
		try {
			
    		String path =ServletContextListener.getWEB_ABSOLUTE_PATH() + Constants.UPLOAD_PATH +"/"+pathName+"/";
    		
    		
    		//定义允许上传的文件扩展名   
    		HashMap<String, String> extMap = new HashMap<String, String>();   
    		extMap.put("image", "gif,jpg,jpeg,png,bmp");   
    		extMap.put("media", "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
    		extMap.put("file", "doc,docx");
    		extMap.put("ppt", "ppt,pptx");
    		
    		//允许最大上传文件大小 struts.xml struts.multipart.maxSize=3G   
    		long maxSize = 3000000000l; 
    		
    		File file = new File(path);
    		
    		
    		if (!file.exists()  && !file.isDirectory()) {
    			file.mkdir(); 
    		} 
    		//检查目录写入权限   
    		if (!file.canWrite()) {   
    			return "上传目录没有写入权限。";   
    		}
    		
    		//检查扩展名   
    		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();   
    		if(!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)){   
    		    return "上传文件扩展名是不允许的扩展名。只允许" + extMap.get(fileType) + "格式。";   
    		}   
    		//检查文件大小   
    		if (file.length() > maxSize) {   
		        return "上传文件大小超过限制。";   
    		}    
    		
    		File[] tempFile = file.listFiles();
    		
			for (int i = 0; i < tempFile.length; i++) {
				if (tempFile[i].getName().equals(oldFileName)) {
					
					File file1 = new File(path + tempFile[i].getName());
					try {
						if (file1.isFile()) {
							file1.delete();
							break;
						}
					} catch (Exception ex) {
						throw new Exception(ex);
					}
				}
			}
			
			tempFile = file.listFiles();
    		
			for (int i = 0; i < tempFile.length; i++) {
				if (tempFile[i].getName().equals(fileName)) {
					
					File file1 = new File(path + tempFile[i].getName());
					try {
						if (file1.isFile()) {
							file1.delete();
							break;
						}
					} catch (Exception ex) {
						throw new Exception(ex);
					}
				}
			}
			
			FileOutputStream outPut = new FileOutputStream(new File(path+fileName));
			InputStream inputStream = new FileInputStream(srcFile);
			byte[] data = new byte[4048];
			int i = 0;
			while((i=inputStream.read(data))>0)
			{
				outPut.write(data, 0, i);
			}
			
			outPut.flush();
			outPut.close();
    		
		} catch (Exception ex) {
			LogFactory.getLog(getClass()).info("policyBizI-saveFile:", ex);
		}
		
		return "";
	}
	
	//删除文件
	public void deleteFile(String fileName,String pathName){
		try{
			
    		String path =ServletContextListener.getWEB_ABSOLUTE_PATH() + "uploadFile/"+pathName+"/";
    		
    		File file = new File(path);
    		File[] tempFile = file.listFiles();
    		
			for (int i = 0; i < tempFile.length; i++) {
				if (tempFile[i].getName().equals(fileName)) {
					
					File file1 = new File(path + tempFile[i].getName());
					try {
						if (file1.isFile()) {
							file1.delete();
							break;
						}
					} catch (Exception ex) {
						throw new Exception(ex);
					}
				}
			}
		}catch(Exception ex) {
			LogFactory.getLog(getClass()).info("policyBizI-deleteFile:", ex);
		}
	}
	
	
	//压缩图片
	public String compressImg(String fileName){
		try{
			int w = 0;
			int h = 0;
			
			File file = new File(fileName);// 读入文件  
	        img = ImageIO.read(file);      // 构造Image对象  
	        width = img.getWidth(null);    // 得到源图宽  
	        height = img.getHeight(null);  // 得到源图长
	        
	       
			if (width / height > 1) {  
	        	 h =  (height * 400 / width);
	        	 w = 400;
	        } else {  
	        	 w =  (width * 400/ height);
	        	 h = 400;
	        }
	        
	        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
	        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
	        File destFile = new File(fileName);  
	        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
	        // 可以正常实现bmp、png、gif转jpg  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        encoder.encode(image); // JPEG编码  
	        out.close(); 
		}catch(Exception ex) {
			LogFactory.getLog(getClass()).info("policyBizI-compressImg:", ex);
		}
		return "";
		 
		
	}
    
}
