package com.turingmaker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.turingmaker.entity.operation.TLesson;
import com.turingmaker.service.api.DataexportService;
import com.turingmaker.service.api.DataimportService;
import com.turingmaker.service.imp.DataexportServiceImpl;
import com.turingmaker.service.imp.DataimportServiceImpl;
import com.turingmaker.service.operation.api.DataMode;

public class TestImport {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		DataimportService dataimportServiceImpl=new DataimportServiceImpl();
		
		DataMode<TLesson> dataMode=new DataMode<>();
		
		dataMode.setHeadLine(1);
		dataMode.setDataStart(2);
		dataMode.setDataEnd(10);
		dataMode.setDataClass(TLesson.class);
	
		Map<String, String> map=new HashMap<>();
		map.put("LESSON_DESCRIPTION", "lessonDescription");
		map.put("LESSON_NAME", "lessonName");
		map.put("LESSON_AVATAR", "lessonAvatar");
		map.put("LESSON_SECTION", "lessonSection");
		map.put("LESSON_STATE", "lessonState");
		map.put("CREATE_TIME", "createTime");
		map.put("UPDATE_TIME", "updateTime");
		map.put("COURSE_ID", "courseId");
		
		dataMode.setColumnandFieldNameMap(map);
		dataMode.setExcelType(DataimportService.xlsxType);
		
		
		
		System.out.println(
				dataimportServiceImpl.importExcel(new FileInputStream("D:/Downloads/aa.xlsx"), dataMode));
		
		List<TLesson> lessons=
				dataimportServiceImpl.importExcel(new FileInputStream("D:/Downloads/aa.xlsx"), dataMode);
		
		
		DataexportService dataexportService=new DataexportServiceImpl();
		dataexportService.exportExcel(new FileOutputStream("E:/a.xls"), dataMode, lessons);
		
		dataMode.setExcelType(DataimportService.xlsType);
		System.out.println(
				dataimportServiceImpl.importExcel(new FileInputStream("E:/a.xls"), dataMode));
	}
}
