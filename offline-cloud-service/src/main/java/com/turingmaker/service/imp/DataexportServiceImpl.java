package com.turingmaker.service.imp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.turingmaker.common.util.TypeConvertUtil;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.operation.api.DataMode;

/**
 * 
 * @author tzj
 *
 */
@Service
public class DataexportServiceImpl implements DataexportService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public <T> void exportExcel(OutputStream excelOut, DataMode<T> dataMode,List<T> datas) {

		try {
			writeExcel(excelOut, dataMode, datas);
		} catch (IOException e) {
			logger.warn("写入Excel IO 异常",e);
		}
	}

	@Override
	public <T> void  exportCsv(OutputStream csvOut, DataMode<T> dataMode,List<T> datas) {

	
	}

	/**
	 * 写入到数据到Excel
	 * @param excelOut
	 * @param dataMode
	 * @param datas
	 * @throws IOException
	 */
	public <T> void writeExcel(OutputStream excelOut, DataMode<T> dataMode, List<T> datas) throws IOException {

		HSSFWorkbook workbook=HSSFWorkbook.create(InternalWorkbook.createWorkbook());
	
		writeWorkbook(workbook, dataMode, datas);
		
		try {
			workbook.write(excelOut);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			workbook.close();
		}
		
	}

	/**
	 * 写一个sheet
	 * @param workbook
	 * @param dataMode
	 * @param datas
	 * @return
	 */
	public <T> List<T> writeWorkbook(Workbook workbook, DataMode<T> dataMode, List<T> datas) {

		if (datas == null || datas.isEmpty()) {
			return null;
		}

		Sheet sheet = workbook.createSheet();
		final Row headRow = sheet.createRow(dataMode.getHeadLine());

		
		Map<String, String> filenames = dataMode.getColumnandFieldNameMap();
		
		String[] fieldNames = new String[filenames.size()];
		int i=0;
			
		Iterator<Entry<String, String>> iterator=
					filenames.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> e=iterator.next();
			fieldNames[i]=e.getKey();
			String colname = fieldNames[i];
			Cell cell = headRow.createCell(i);
			setCellValue(cell,colname);
			i++;
		}
		

		i=dataMode.getDataStart();
		for(T t:datas) {
			Row dataRow = sheet.createRow(i);//创造当前行
			try {
				writeDataRow(dataRow, t, filenames, fieldNames);
			} catch (Exception e) {
				logger.warn("写入异常,行号:"+i, e);
			}
			i++;
			
			if(i>=dataMode.getDataEnd()) {
				break;
			}
		}

		return datas;
	}

	/**
	 * 写入一行
	 * @param row
	 * @param object
	 * @param filenames
	 * @param fieldNames
	 * @throws Exception
	 */
	public void writeDataRow(Row row, Object object, Map<String, String> filenames, String[] fieldNames)
			throws Exception {

		for (int i = 0; i < fieldNames.length; i++) {

			String colname = fieldNames[i];
			if (!filenames.containsKey(colname)) {
				continue;
			}
			Cell cell = row.createCell(i);
			Object cellValue = null;
			try {
				cellValue = BeanUtils.getPropertyDescriptor(object.getClass(), filenames.get(colname)).getReadMethod()
						.invoke(object);
			} catch (Exception e) {
				logger.warn("cell设置属性异常,列号:" + i, e);
			}

			if (cellValue != null) {
				setCellValue(cell, cellValue);
			}
		}

	}

	/**
	 * 设置单元格格式的值
	 * @param cell
	 * @param value
	 */
	public void setCellValue(Cell cell, Object value) {

		if (value instanceof String || value instanceof Date) {
			cell.setCellType(CellType.STRING);
			cell.setCellValue(TypeConvertUtil.convertIfNecessary(value, String.class));
		}

		else if (value instanceof Number) {

			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(TypeConvertUtil.convertIfNecessary(value, Double.class));
		}
		
		else if(value instanceof Collection) {
		
			Collection<?> collection=(Collection<Object>)value;
			cell.setCellType(CellType.STRING);
			StringBuilder builder=new StringBuilder();
			for(Object object:collection) {
				builder.append(String.valueOf(object));
				builder.append(",");
			}
			
			cell.setCellValue(builder.toString());
		}

	}
}
