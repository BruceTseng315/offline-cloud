package com.turingmaker.service.imp;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.turingmaker.common.util.TypeConvertUtil;
import com.turingmaker.service.api.DataimportService;
import com.turingmaker.service.operation.api.DataMode;

/**
 * 
 * @author tzj
 *
 */
@Service
public class DataimportServiceImpl implements DataimportService {

	Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	public <T> List<T> importExcel(InputStream inputStream, DataMode<T> dataMode) {

		List<T> datas = null;
		try {
			datas = readExcel(inputStream, dataMode);
		} catch (IOException e) {
			logger.warn("处理Excel inputstream 异常", e);
		}

		return datas;
	}

	public <T> List<T> readExcel(InputStream inputStream, DataMode<T> dataMode) throws IOException {

		List<T> datas = null;
		Workbook workbook = null;
		
		
		try {
				workbook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e2){
			logger.warn("处理Excel workbook 异常", e2.getMessage());
		}
		if (workbook == null) {
			return null;
		}
		datas = readWorkbook(workbook, dataMode);
		workbook.close();
		return datas;
	}

	public <T> List<T> readWorkbook(Workbook workbook, DataMode<T> dataMode) {

		Sheet sheet = null;
		sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		Row row = sheet.getRow(dataMode.getHeadLine());

		if (row == null) {
			return null;
		}

		int dataEnd = dataMode.getDataEnd();

		int firstCellNum = row.getFirstCellNum();
		int lastCellNum = row.getPhysicalNumberOfCells();
		String[] fieldNames = new String[lastCellNum];
		for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
			Cell cell = row.getCell(cellNum);
			fieldNames[cellNum - firstCellNum] = getCellValue(cell, String.class);
		}

		List<T> datas = new ArrayList<>();
		Map<String, String> filenames = dataMode.getColumnandFieldNameMap();

		for (int rowNum = dataMode.getDataStart(); rowNum <= dataEnd && rowNum <= sheet.getLastRowNum(); rowNum++) {
			// 获得当前行
			row = sheet.getRow(rowNum);
			try {
				datas.add(readDataRow(row, dataMode.getDataClass(), filenames,fieldNames));
			} catch (Exception e) {
				logger.warn("读取异常", e);
			}

		}

		return datas;
	}

	/**
	 * 
	 * @param row
	 * @param class1
	 * @param filenames
	 * @return
	 * @throws Exception
	 */
	public <T> T readDataRow(Row row, Class<T> class1, Map<String, String> filenames, String[] fieldNames)
			throws Exception {

		int firstCellNum = row.getFirstCellNum();
		int lastCellNum = row.getPhysicalNumberOfCells();
		T object = class1.newInstance();
		firstCellNum = row.getFirstCellNum();
		lastCellNum = row.getPhysicalNumberOfCells();
		// 循环当前行
		for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
			Cell cell = row.getCell(cellNum);

			String colname = fieldNames[cellNum - firstCellNum];
			if (!filenames.containsKey(colname)) {
				continue;
			}
			PropertyDescriptor descriptor=BeanUtils.getPropertyDescriptor(class1, filenames.get(colname));
			
			descriptor.getWriteMethod().invoke(object,
					getCellValue(cell,descriptor.getWriteMethod().getParameterTypes()[0]));
		}

		return object;
	}

	/**
	 * 
	 * @param cell
	 *            单元格
	 * @param class1
	 *            期待类型
	 * @return
	 */
	public <T> T getCellValue(Cell cell, Class<T> class1) {

		Object obj = null;
		if (cell == null) {
			return null;
		}

		CellType cellType = cell.getCellTypeEnum();

		switch (cellType) {
		case STRING:
			obj = cell.getStringCellValue();
			break;
		case BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case FORMULA:
			obj = cell.getCellFormula();
			break;
		case NUMERIC:
			/*
			 消除科学计数法以及浮点数
			 */
			DecimalFormat df = new DecimalFormat("0");
			 obj = df.format(cell.getNumericCellValue());
			break;
		default:
			break;
		}
		
		return TypeConvertUtil.convertIfNecessary(obj, class1);
	}

	@Override
	public <T> List<T> importCsv(InputStream inputStream, DataMode<T> dataMode) {

		return null;
	}

}
