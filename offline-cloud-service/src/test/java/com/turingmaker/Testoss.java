package com.turingmaker;

import java.io.File;
import java.io.IOException;

import com.turingmaker.service.api.UnCompressionService;

public class Testoss {

	public static void main(String[] args) throws Exception {
		
	
		 UnCompressionService compressionService=new UnCompressionService() {
			
			@Override
			public void unCompressZip(File zipFile, File outputPath) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void unCompressRar(File rarFile, File outputPath) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		compressionService.upload2Oss(new File("E:\\upload\\uncompressppt\\draw-master.zip"),"draw-master.zip");

	}

}
