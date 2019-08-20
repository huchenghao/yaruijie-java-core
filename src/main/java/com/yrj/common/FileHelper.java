package com.yrj.common;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
public class FileHelper {
	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 上传附件
	 * @param enclosure
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @author huchenghao
	 */
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
	
	
	/**
	 * 获取文件的后缀
	 * @param name
	 * @return .jpeg
	 */
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
	/**
	 * 获取文件的后缀
	 * @param name
	 * @return .jpeg
	 */
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
	
}
