package com.turingmaker.service.api;

import java.io.InputStream;
import java.util.List;

import com.turingmaker.service.operation.api.DataMode;

/**
 * 导入的service
 * @author Administrator
 *
 */
public interface DataimportService {

	int xlsType=0;
	int xlsxType=1;
	
	/**
	 * 导入Excel
	 * @param dataMode
	 * @return
	 */
	public <T> List<T> importExcel(InputStream excelInput,DataMode<T> dataMode);
	
	/**
	 * 导入CSV文件
	 * @param dataMode
	 * @return
	 */
	public <T> List<T> importCsv(InputStream csvInput,DataMode<T> dataMode);
}
