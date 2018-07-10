package com.turingmaker;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.entity.Resourceinfo;
import com.turingmaker.service.operation.entity.Taskinfo;
import com.turingmaker.service.operation.request.SaveLessonRequest;
import com.turingmaker.service.operation.response.Pageinfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudApplication.class)
public class LessonTests {

	@Autowired
	LessonService lessonService;

	@Test
	public void addLesson() {

		SaveLessonRequest lessonRequest = new SaveLessonRequest();
		lessonRequest.setCourseId(100004l);
		lessonRequest.setLessonName("python入门第一课");
		lessonRequest.setLessonDescription("简单描述");
		lessonRequest.setLessonSection(11);
		lessonRequest.setLessonAvatar(
				"https://cdn.liaoxuefeng.com/cdn/files/attachments/00138676512923004999ceca5614eb2afc5c0efdd2e4640000/0");

		List<Resourceinfo> resourceinfos = new ArrayList<>();
		resourceinfos.add(new Resourceinfo("123.ppt", "http://www.baidu.com"));
		resourceinfos.add(new Resourceinfo("bbb.ppt", "http://www.baidu.com/s"));
		lessonRequest.setPpts(resourceinfos);

		List<Resourceinfo> programs = new ArrayList<>();
		programs.add(new Resourceinfo("123.exe", "http://www.baidu.com"));
		programs.add(new Resourceinfo("222.exe", "http://www.baidu.com/program"));
		lessonRequest.setPrograms(programs);

		List<Taskinfo> taskinfos = new ArrayList<>();
		Taskinfo taskinfo = new Taskinfo(new Resourceinfo("aaa.exe", "http://a.com/program"));
		taskinfo.setName("1111");
		taskinfo.setUrl("11");
		taskinfos.add(taskinfo);
		lessonRequest.setTasks(taskinfos);

		lessonService.addLesson(lessonRequest);
	}

	// @Test
	public void detailLesson() {

		System.out.println(lessonService.detailsLesson(1L));
	}

	// @Test
	public void pagelLesson() {

		System.out.println(lessonService.findByPage(new Pageinfo(1, 10)));
	}

}
