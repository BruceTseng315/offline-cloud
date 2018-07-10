package com.turingmaker.controll.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.turingmaker.common.domain.Result;
import com.turingmaker.service.api.UnCompressionService;

@RequestMapping("/turing/api/v2/manage/fileupload")
@Controller
public class FileUploadControll {


	@Autowired
	UnCompressionService unCompressionService;
	
	
	/**
	 * 将要解压的文件所在目录
	 */
	@Value("${compress-upload}")
	private String compressOutPut;
	
	/**
	 * 解压之后PPT目录
	 */
	@Value("${uncompress-ppt-upload}")
	private String uncompressPPTPath;

	@Value("${accessresources-url}")
	private String accessresourcesUrl;
	
	
	
	
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("index")
	public String index(String mode){
		
	
		
		return mode;
				
	}
	
	@PostMapping("excel")
	@ResponseBody
	public Result<?> uploadFile(MultipartFile file){
		

		return Result.successresult;
				
	}
	
	
	
	@PostMapping("compressppt")
	@ResponseBody
	public Result<?> uploadCompressFile(@RequestParam("file") MultipartFile file,int type){
		
		String filename=file.getOriginalFilename();
		
		File compressOutPutDest=new File(compressOutPut);
		File uncompressPPTPathDest=new File(uncompressPPTPath);
		
		if(!compressOutPutDest.exists()) {
			compressOutPutDest.mkdirs();
		}
		
		if(!uncompressPPTPathDest.exists()) {
			uncompressPPTPathDest.mkdirs();
		}
		
		File compressFile=new File(compressOutPutDest,filename);
		File dest=new File(uncompressPPTPathDest,filename);
		
	
		if(!dest.exists()) {
			dest.mkdirs();
		}
		
		try {
			file.transferTo(compressFile);
			unCompressionService.unCompress(compressFile, dest,type);
		} catch (IOException e) {
			logger.warn("上传压缩文件异常",e);
		}
		
		File indexfile=unCompressionService.lookForIndex(dest, UnCompressionService.indexname);
		if(indexfile==null) {
			return  Result.errorrResult("解压环节可能出错,因为没有找到index.html");	
		}
		
		String filepath=indexfile.toURI().getPath().substring(uncompressPPTPathDest.toURI().getPath().length());
		unCompressionService.upload2Oss(dest,filename );
		return  new Result<String>(filepath);
	}
	
	
	
	
	
	
}
