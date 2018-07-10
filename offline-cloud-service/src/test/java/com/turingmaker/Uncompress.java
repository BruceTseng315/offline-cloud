package com.turingmaker;

import java.io.File;
import java.io.IOException;

import com.github.junrar.exception.RarException;
import com.turingmaker.service.imp.UnCompressionServiceSimpleImpl;

public class Uncompress {

	public static void main(String[] args) throws RarException, IOException {
		
		
		
	UnCompressionServiceSimpleImpl compressionServiceSimpleImpl=new UnCompressionServiceSimpleImpl();
	
	compressionServiceSimpleImpl.unCompressZip(new File("E:\\upload\\compressfile\\draw-master.zip"),new File("E:/extractArchive"));
		
	
		

	}

}
