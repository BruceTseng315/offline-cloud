package com.turingmaker.service.api;

import java.io.OutputStream;
import java.util.List;

import com.turingmaker.service.operation.api.DataMode;

/**
 * 导入的service
 * @author Administrator
 *
 */
public interface DataexportService {

	
	/**
	 * 导入Excel
	 * @param dataMode
	 * @return
	 */
	public <T> void exportExcel(OutputStream excelOut,DataMode<T> dataMode,List<T> datas);
	
	/**
	 * 导入CSV文件
	 * @param dataMode
	 * @return
	 */
	public <T> void exportCsv(OutputStream csvOut,DataMode<T> dataMode,List<T> datas);
}
