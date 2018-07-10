package com.turingmaker.service.operation.api;

import java.util.Map;

/**
 * 
 * @author tzj
 *
 * @param <T>
 */
public class DataMode<T> {

	int headLine=1;
	
	int dataStart=2;
	
	int dataEnd=100;

	/**
	 * xls  xlsx
	 */
	int excelType;
	
	Class<T> dataClass;
	
	
	
	Map<String, String> columnandFieldNameMap;
	

	public int getHeadLine() {
		return headLine;
	}

	public void setHeadLine(int headLine) {
		this.headLine = headLine;
	}

	public int getDataStart() {
		return dataStart;
	}

	public void setDataStart(int dataStart) {
		this.dataStart = dataStart;
	}

	public int getDataEnd() {
		return dataEnd;
	}

	public void setDataEnd(int dataEnd) {
		this.dataEnd = dataEnd;
	}

	public Class<T> getDataClass() {
		return dataClass;
	}

	public void setDataClass(Class<T> dataClass) {
		this.dataClass = dataClass;
	}

	
	
	public int getExcelType() {
		return excelType;
	}

	public void setExcelType(int excelType) {
		this.excelType = excelType;
	}

	public Map<String, String> getColumnandFieldNameMap() {
		return columnandFieldNameMap;
	}

	public void setColumnandFieldNameMap(Map<String, String> columnandFieldNameMap) {
		this.columnandFieldNameMap = columnandFieldNameMap;
	}

	@Override
	public String toString() {
		return "DataMode [headLine=" + headLine + ", dataStart=" + dataStart + ", dataEnd=" + dataEnd + ", dataClass="
				+ dataClass + ", columnandFieldNameMap=" + columnandFieldNameMap + "]";
	}

	
	
	
}
