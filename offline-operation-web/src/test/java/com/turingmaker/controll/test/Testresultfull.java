package com.turingmaker.controll.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.turingmaker.common.util.HttpRequestTool;

public class Testresultfull {

	// @Test
	public void testrequest() throws IOException {

		HttpRequestTool httpRequestTool = new HttpRequestTool();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				Testresultfull.class.getClassLoader().getResourceAsStream("a.json"), Charset.forName("utf-8")));

		StringBuilder builder = new StringBuilder();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder);
		httpRequestTool.httpPostForResult("http://localhost:52809/turing/api/v2/admin_manage/add_school",
				builder.toString());

	}

	// @Test
	public void testrequest2() throws IOException {

		HttpRequestTool httpRequestTool = new HttpRequestTool();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				Testresultfull.class.getClassLoader().getResourceAsStream("a2.json"), Charset.forName("GBK")));

		StringBuilder builder = new StringBuilder();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder);
		httpRequestTool.httpPostForResult("http://localhost:52809//turing/api/v2/manage/course/add",
				builder.toString());

	}

	//@Test
	public void testrequestlesson() throws IOException {

		HttpRequestTool httpRequestTool = new HttpRequestTool();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				Testresultfull.class.getClassLoader().getResourceAsStream("lesson.json"), Charset.forName("GBK")));

		StringBuilder builder = new StringBuilder();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder);
		System.out.println(httpRequestTool.httpPostForResult(
				"http://localhost:52808/turing/api/v2/manage/course/lesson/save", builder.toString()));

	}
	
	
	//@Test
	public void testaddResource() throws IOException {

		HttpRequestTool httpRequestTool = new HttpRequestTool();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				Testresultfull.class.getClassLoader().getResourceAsStream("lessonresource.json"), Charset.forName("GBK")));

		StringBuilder builder = new StringBuilder();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder);
		System.out.println(httpRequestTool.httpPostForResult(
				"http://localhost:52808/turing/api/v2/manage/course/lesson/addlesson_resources", builder.toString()));

	}
	
	//@Test
	public void testaddTask() throws IOException {

		HttpRequestTool httpRequestTool = new HttpRequestTool();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				Testresultfull.class.getClassLoader().getResourceAsStream("lessontask.json"), Charset.forName("GBK")));

		StringBuilder builder = new StringBuilder();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder);
		System.out.println(httpRequestTool.httpPostForResult(
				"http://localhost:52808/turing/api/v2/manage/course/lesson/addlesson_task", builder.toString()));

	}
	
	
	
	
		@Test
		public void testLogin() throws IOException {

			HttpRequestTool httpRequestTool = new HttpRequestTool();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					Testresultfull.class.getClassLoader().getResourceAsStream("lessontask.json"), Charset.forName("GBK")));

			StringBuilder builder = new StringBuilder();

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}

			System.out.println(builder);
		
			
			Map<String, String> username=new HashMap<>();
			username.put("username", "f810000");
			username.put("password", "123456");
			
			System.out.println(
					httpRequestTool.httpPostForResult("http://localhost:52808/turing/api/v2/admin_manage/operation_login", username));

		}
		
		@Test
		public void testLoginJson() throws IOException {

			HttpRequestTool httpRequestTool = new HttpRequestTool();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					Testresultfull.class.getClassLoader().getResourceAsStream("login.json"), Charset.forName("GBK")));

			StringBuilder builder = new StringBuilder();

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}

			System.out.println(builder);
			System.out.println(httpRequestTool.httpPostForResult(
					"http://localhost:52809/turing/api/v2/admin_manage/operation_login", builder.toString()));

		}
}
