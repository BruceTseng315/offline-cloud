package com.turingmaker.controll.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.turingmaker.common.util.HttpRequestTool;

public class Testresultfull2 {

	
		
		@Test
		public void testEditStudent() throws IOException {

			HttpRequestTool httpRequestTool = new HttpRequestTool();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					Testresultfull2.class.getClassLoader().getResourceAsStream("student.json"), Charset.forName("GBK")));

			StringBuilder builder = new StringBuilder();

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}

			System.out.println(builder);
			System.out.println(httpRequestTool.httpPostForResult(
					"http://localhost:52807/turing/api/v2/school_manage/student/edit", builder.toString()));

		}
}
