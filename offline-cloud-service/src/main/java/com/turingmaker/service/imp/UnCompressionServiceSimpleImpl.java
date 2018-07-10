package com.turingmaker.service.imp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.junrar.exception.RarException;
import com.github.junrar.extract.ExtractArchive;
import com.turingmaker.service.api.UnCompressionService;

/**
 * 解压缩的简单实现
 * 
 * @author tzj
 *
 */
@Service
public class UnCompressionServiceSimpleImpl implements UnCompressionService {

	Logger logger = LoggerFactory.getLogger(getClass());

	ExtractArchive extractArchive = new ExtractArchive();

	int buffersie = 4096;

	@Override
	public void unCompressZip(File zipFile, File outputPath) throws IOException {

		if (!zipFile.exists()) {
			throw new IOException("zip文件不存在" + zipFile.getAbsolutePath());
		}

		if (!outputPath.exists()) {
			outputPath.mkdir();
		}

		ZipFile file = new ZipFile(zipFile);
		InputStream inputStream=null;
		OutputStream outputStream=null;
		Enumeration<? extends ZipEntry> enumeration = file.entries();

		while (enumeration.hasMoreElements()) {

			ZipEntry entry = enumeration.nextElement();
			File filedest = new File(outputPath, entry.getName());
			if (entry.isDirectory()) {
				if (!filedest.exists()) {
					filedest.mkdirs();
				}
			} else {
				
				inputStream=file.getInputStream(entry);
				outputStream=new FileOutputStream(filedest);
				try {
					IOUtils.copy(file.getInputStream(entry), new FileOutputStream(filedest));
				} catch (IOException e) {
					logger.warn("写入"+entry.getName()+"出错");
				}finally {
					if(inputStream!=null) {
						inputStream.close();
					}
					
					if(outputStream!=null) {
						outputStream.close();
					}
				}

			}

		}
		
		file.close();

	}

	@Override
	public void unCompressRar(File rarFile, File outputPath) throws IOException {

		if (!rarFile.exists()) {
			throw new IOException("rar文件不存在" + rarFile.getAbsolutePath());
		}

		if (!outputPath.exists()) {
			outputPath.mkdirs();
		}

		try {
			extractArchive.extractArchive(rarFile, outputPath);
		} catch (RarException e) {
			logger.warn("解压失败rar", e);
			throw new IOException(e);
		}

	}

	/**
	 * 
	 * @param zipFile
	 *            输入文件
	 * @param outputPath
	 *            输出目录
	 * @throws IOException
	 */
	public void zipDecompressing(ZipInputStream zis, File outputPath) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(zis, buffersie);
		byte[] bs = new byte[buffersie];

		File fOut = null;
		ZipEntry entry = null;
		BufferedOutputStream bos = null;

		try {
			while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {

				fOut = new File(outputPath, entry.getName());

				if (!fOut.exists()) {
					(new File(fOut.getParent())).mkdirs();
				}

				bos = new BufferedOutputStream(new FileOutputStream(fOut));
				int b;
				while ((b = bis.read(bs)) != -1) {
					bos.write(bs, 0, b);
				}
			}
		} catch (Exception e) {
			logger.warn("写入entry失败", e);
		} finally {
			if (bos != null) {
				bos.close();
			}
		}

		bis.close();

	}

}
