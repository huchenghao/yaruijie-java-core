package com.yrj.common;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;
public class FileHelper {/*
	*//**
	 * 
	 * @Title: uploadFile
	 * @Description: 上传附件
	 * @param enclosure
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @author huchenghao
	 *//*
	public static String uploadFile(MultipartFile enclosure,String filePath) throws Exception {
		String fileType = getFileType( enclosure.getOriginalFilename());
		String path =  filePath;
		File filePaths=new File(path);
		if(!filePaths.exists()){
			filePaths.mkdirs();
		}
		String newName = UUID.randomUUID() + fileType;
		File newFile = new File(path + newName);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		enclosure.transferTo(newFile);
		return newName;

	}
	public static String uploadFile_file(MultipartFile  enclosure,String filePath) throws Exception {
		String fileType = getFileType_file( enclosure.getOriginalFilename());
		String path =  filePath;
		File filePaths=new File(path);
		if(!filePaths.exists()){
			filePaths.mkdirs();
		}
		String newName = UUID.randomUUID() + fileType;
		File newFile = new File(path + newName);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		enclosure.transferTo(newFile);
		return newName;
		
	}
	public static String uploadMvFile(MultipartFile  enclosure,String filePath) throws Exception {
		String fileType = getMvFileType( enclosure.getOriginalFilename());
		String path =  filePath;
		File filePaths=new File(path);
		if(!filePaths.exists()){
			filePaths.mkdirs();
		}
		String newName = UUID.randomUUID() + fileType;
		File newFile = new File(path + newName);
		if(!newFile.exists()){
			newFile.createNewFile();
		}
		enclosure.transferTo(newFile);
		return newName;
		
	}
	
	
	*//**
	 * 获取文件的后缀
	 * @param name
	 * @return .jpeg
	 *//*
	public static String getFileType(String name) {
		if (name != null && !"".equals(name)) {
			int index = name.lastIndexOf(".");
			if (index != -1) {
				return name.substring(index);
			}
		}else{
			return ".jpeg";
		}
		return "";
	}
	*//**
	 * 获取文件的后缀
	 * @param name
	 * @return .jpeg
	 *//*
	public static String getFileType_file(String name) {
		if (name != null && !"".equals(name)) {
			int index = name.lastIndexOf(".");
			if (index != -1) {
				return name.substring(index);
			}
		}else{
			return "";
		}
		return "";
	}
	public static String getMvFileType(String name) {
		if (name != null && !"".equals(name)) {
			int index = name.lastIndexOf(".");
			if (index != -1) {
				return name.substring(index);
			}
		}else{
			return ".mp4";
		}
		return "";
	}
	
	
	public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }  
	*//**
	 * 
	 * @Title: convertFileUnit
	 * @Description: 文件字节数转换为合适的单位
	 * @author: 李国刚
	 * @param size
	 * @return
	 * @return: String
	 *//*
	public static String convertFileUnit(long size) {
		 // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义  
       double value = (double) size;  
       if (value < 1024) {  
           return String.valueOf(value) + "B";  
       } else {  
           value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();  
       }  
       // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位  
       // 因为还没有到达要使用另一个单位的时候  
       // 接下去以此类推  
       if (value < 1024) {  
           return String.valueOf(value) + "KB";  
       } else {  
           value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();  
       }  
       if (value < 1024) {  
           return String.valueOf(value) + "MB";  
       } else {  
           // 否则如果要以GB为单位的，先除于1024再作同样的处理  
           value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();  
           return String.valueOf(value) + "GB";  
       }
	}
	
*/}
