package com.turingmaker.service.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.aliyun.oss.OSSClient;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

/**
 * 解压缩service
 * 提供解压缩相关的服务
 * @author tzj
 *
 */
public interface UnCompressionService {

	
	
	String indexname="index.html";
	
	String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
	String accessKeyId = "LTAIZdbzNu5EJNd3";
	String accessKeySecret = "VVO34quxK5uDBERnzGucFTi8CQhvg4";
	String directory="course/ppt/";
	String  bucketname="turing-assets";

	/**
	 * 解压缩ZIP 或者rar
	 * @param type 可选值 0:1:2  代表 zip，rar,tar
	 * @param inputStream
	 */
	public default void  unCompress(File compressFile,File outputPath,int type) throws IOException {
		
		
		switch (type) {
		
		case 0:
			unCompressZip(compressFile, outputPath);
			break;
		case 1:
			unCompressRar(compressFile, outputPath);
			break;

		default:
			break;
		}
	}
	/**
	 * 解压缩ZIP
	 * @param inputStream
	 */
	public void  unCompressZip(File zipFile,File outputPath) throws IOException ;
	
	
	/**
	 * 解压缩rar
	 * @param inputStream
	 */
	public void unCompressRar(File rarFile,File outputPath) throws IOException ;
	
	/**
	 * 寻找index.html
	 * @param file
	 * @return
	 */
	public default File lookForIndex(File file,String indexname) {
		
		File filereturn=null;
		if(file.isDirectory()) {
			File[] files=file.listFiles();
			for(File filefind:files) {
				filereturn=lookForIndex(filefind,indexname);
				if(filereturn!=null) {
					return filereturn;
				}
			}
			
		}else if(file.isFile()) {
			if(file.getName().contains(indexname))
				return file;
		}
		
		return null;
	}
	
	
	public  default void upload2Oss(File dest,String filepaht) {
		
		OSSClient ossClient=null; 
		try {
			ossClient= new OSSClient(endpoint, accessKeyId, accessKeySecret);
			upload2OssAlldirectory(dest, filepaht, ossClient);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(ossClient!=null) {				
				ossClient.shutdown();
			}
		}
	}
	
	/**
	 * 将目标文件夹 上传到OSS
	 * @param dest
	 * @param filepaht
	 * @param ossClient
	 * @throws IOException
	 */
	public default void upload2OssAlldirectory(File dest,String filepaht,OSSClient ossClient) throws IOException  {
		
		
		
		if(dest.isDirectory()) {
		
			for(File file:dest.listFiles()) {
				
				upload2OssAlldirectory(file, filepaht+"/"+file.getName(),ossClient);
			}
			
		}else {			
			ossClient.putObject(bucketname, directory+filepaht, new FileInputStream(dest));
		}
		
	}
	
	
	
}
