package com.turingmaker.service.schoolManage.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.turingmaker.service.school.bo.ExportTeacherResponse;
import com.turingmaker.service.school.bo.TeacherSelectAss;
import com.turingmaker.service.teachter.request.UpdatePwdRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassCourseTeacherMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.dao.mapper.school.ext.TSchoolMapperExt;
import com.turingmaker.dao.mapper.school.ext.TTeacherMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.entity.school.TClassCourseTeacher;
import com.turingmaker.entity.school.TTeacher;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.api.DataimportService;
import com.turingmaker.service.exception.ServiceException;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.entity.QueryTeachterResult;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.operation.response.TeacherInfo;
import com.turingmaker.service.operation.response.TeacherListResponse;
import com.turingmaker.service.operation.response.TeacherResponse;
import com.turingmaker.service.school.api.TeacherService;


@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	DataimportService dataimportService;

	@Autowired
	AuthService authService;

	@Autowired
	UserMapperExt userMapperExt;

	@Autowired
	TSchoolMapperExt schoolMapperExt;

	@Autowired
	TTeacherMapperExt teacherMapperExt;

	@Autowired
	TClassCourseTeacherMapperExt tClassCourseTeacherMapperExt;

	@Autowired
	TClassMapperExt classMapperExt;

	@Autowired
	CourseMapperExt courseMapperExt;

	@Override
	@Transactional
	public YUser addTeacher(Long schoolId, String teacherName, String phone) {
		// 添加生成账号
		String account = authService.generateAccount(4);
		YUser user = userMapperExt.findUserByUserName(account);
		TTeacher teacher = new TTeacher();
		teacher.setAccountId(user.getId());
		teacher.setTeacherName(teacherName);
		teacher.setPhone(phone);
		teacher.setSchoolId(schoolId);
		teacher.setTeacherCode(String.valueOf(user.getId()));
		teacherMapperExt.insertSelective(teacher);
		return user;
	}

	@Override
	@Transactional
	public TeacherResponse findAllTeacherBySchoolId(Long schoolId, Integer pageNo, Integer pageSize) {
		Pageinfo pageinfo = new Pageinfo();
		pageinfo.setPageNo(pageNo);
		pageinfo.setPageSize(pageSize);
		List<TTeacher> list = teacherMapperExt.findAllBySchoolId(pageinfo, schoolId);
		TeacherResponse teacherResponse = new TeacherResponse();
		teacherResponse.setPageinfo(pageinfo);
		teacherResponse.setTeacherList(list);
		return teacherResponse;
	}

	@Override
	@Transactional
	public void updateInfo(Long teacherId, String teacherName, String phone, Integer refresh) {
		TTeacher teacher = teacherMapperExt.selectByPrimaryKey(teacherId);
		teacher.setTeacherName(teacherName);
		teacher.setPhone(phone);
		teacherMapperExt.updateByPrimaryKeySelective(teacher);
		if (refresh == 1) {
			YUser user = userMapperExt.selectByPrimaryKey(teacher.getAccountId());
			user.setPassword("123456");
			userMapperExt.updateByPrimaryKeySelective(user);
		}
	}

	/*
	@Override
	@Transactional
	public TeacherListResponse getTeacherList(Long schoolId,String teacherCode, String teacherName, String className, Long courseId,
			Integer pageNo, Integer pageSize) {
		Pageinfo page = new Pageinfo();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<TTeacher> list = teacherMapperExt.selectForListTeacherPage(page, schoolId,teacherCode, teacherName, className,
				courseId);
		TeacherListResponse response = new TeacherListResponse();
		response.setPageinfo(page);
		if (list == null || list.size() <= 0) {
			return response;
		}
		List<TeacherInfo> teacherInfos = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.setId(list.get(i).getId());
			teacherInfo.setTeacherCode(list.get(i).getTeacherCode());
			teacherInfo.setTeacherName(list.get(i).getTeacherName());
			teacherInfo.setAccount(userMapperExt.selectByPrimaryKey(list.get(i).getAccountId()).getAccount());
			List<TClassCourseTeacher> classCourseTeachers = tClassCourseTeacherMapperExt
					.findByTeacherId(list.get(i).getId());

			List<String> courses = new ArrayList<>();
			List<String> classes = new ArrayList<>();
			if (classCourseTeachers != null && classCourseTeachers.size() > 0) {
				for (int j = 0; j < classCourseTeachers.size(); j++) {
					TCourse course = courseMapperExt.selectByPrimaryKey(classCourseTeachers.get(j).getCourseId());
					if (course != null) {
						courses.add(course.getCourseName());
					}
					TClass tClass = classMapperExt.selectByPrimaryKey(classCourseTeachers.get(j).getClassId());
					if (tClass != null) {
						classes.add(tClass.getClassName());
					}
				}
			}
			teacherInfo.setCourses(courses);
			teacherInfo.setClasses(classes);
			teacherInfos.add(teacherInfo);
		}
		response.setList(teacherInfos);
		return response;
	}
	*/

	/**
	 * 导入教师
	 * 
	 * @param schoolId
	 * @param is
	 */
	@Override
	public void importTeachers(long schoolId, InputStream is) {
		DataMode<TTeacher> dataMode = new DataMode<>();
		dataMode.setHeadLine(0);
		dataMode.setDataStart(1);
		dataMode.setDataClass(TTeacher.class);

		// Excell模板表头字段与AddClassRequest属性字段映射表
		Map<String, String> map = new HashMap<>();
		map.put("教师姓名", "teacherName");
		map.put("手机号", "phone");
		dataMode.setColumnandFieldNameMap(map);

		List<TTeacher> teacherList = dataimportService.importExcel(is, dataMode);
		// 新增教师
		if (teacherList != null && teacherList.size() > 0) {
			for (TTeacher tTeacher : teacherList) {
				this.addTeacher(schoolId, tTeacher.getTeacherName(), tTeacher.getPhone());
			}
		}

	}
/*
	@Override
	public TeacherListResponse getTeacherListNew(String teacherCode, String teacherName, Long classId,
			String courseCode, Integer pageNo, Integer pageSize) {
		Pageinfo pageinfo = new Pageinfo(pageNo, pageSize);
		List<QueryTeachterResult> queryTeachterResults = teacherMapperExt.selectForListTeacherNewPage(pageinfo,
				teacherCode, teacherName, classId, courseCode);

		TeacherListResponse listResponse = new TeacherListResponse();
		List<TeacherInfo> teacherInfos = new ArrayList<>();

		Map<Long, List<String>> teachterCourseNames = new HashMap<>();

		Map<Long, List<String>> teachterClassNames = new HashMap<>();

		List<String> courseNames = null;
		List<String> classNames = null;

		for (QueryTeachterResult queryTeachterResult : queryTeachterResults) {

			Long id = queryTeachterResult.getId();

			int count = 0;

			if (teachterClassNames.containsKey(id)) {
				classNames = teachterClassNames.get(id);
				classNames.add(queryTeachterResult.getClassName());
				count++;
			}

			if (teachterCourseNames.containsKey(id)) {
				courseNames = teachterCourseNames.get(id);
				courseNames.add(queryTeachterResult.getCourseName());
				count++;
			}

			if (count > 1) {
				continue;
			}

			if (count <= 0) {
				TeacherInfo info = new TeacherInfo();
				if (!teachterClassNames.containsKey(id)) {
					teachterClassNames.put(id, classNames = new ArrayList<>());
					info.setClasses(classNames);
				} else {
					classNames = teachterClassNames.get(id);
				}

				if (!teachterCourseNames.containsKey(id)) {
					teachterCourseNames.put(id, courseNames = new ArrayList<>());
					info.setCourses(courseNames);
				} else {
					courseNames = teachterCourseNames.get(id);
				}

				classNames.add(queryTeachterResult.getClassName());
				courseNames.add(queryTeachterResult.getCourseName());
				
				info.setAccount(queryTeachterResult.getAccount());
				info.setTeacherCode(queryTeachterResult.getTeacherCode());
				info.setTeacherName(queryTeachterResult.getTeacherName());
				teacherInfos.add(info);
			}

		}
		listResponse.setList(teacherInfos);
		return listResponse;
	}

*/

    /**
     * 导出教师信息
     * @param teacherCode
     * @param teacherName
     * @param className
     * @param courseId
     * @return
     */
    @Override
    public List<ExportTeacherResponse> getTeacherForExport(Long schoolId,String teacherCode, String teacherName, String className, Long courseId) {
        List<ExportTeacherResponse> exportTeacherResponses = new ArrayList<>();
        List<TeacherSelectAss> teacherSelectAsses = teacherMapperExt.selectTeacherForExport(schoolId,teacherCode,teacherName,className,courseId);
        if(teacherSelectAsses != null && teacherSelectAsses.size() > 0) {
        	for (TeacherSelectAss teacherSelectAss:teacherSelectAsses){
        		ExportTeacherResponse exportTeacherResponse = new ExportTeacherResponse();
        		exportTeacherResponse.setAccount(teacherSelectAss.getAccount());
        		exportTeacherResponse.setPassword(teacherSelectAss.getPassword());
        		exportTeacherResponse.setTeacherName(teacherSelectAss.getTeacherName());
        		List<String> courses = teacherSelectAss.getCourses();
        		if(courses != null && courses.size() > 0) {
					StringBuilder courseBuilder = new StringBuilder();
					for(String str:courses) {
						courseBuilder.append(str);
						courseBuilder.append("\n\r");
					}
					exportTeacherResponse.setCourse(courseBuilder.toString());
				}

				List<String> classes = teacherSelectAss.getClasses();
        		if(classes != null && classes.size() > 0){
        			StringBuilder classBuilder = new StringBuilder();
        			for(String str:classes){
        				classBuilder.append(str);
        				classBuilder.append("\n\r");
					}
					exportTeacherResponse.setClassIn(classBuilder.toString());
				}
				exportTeacherResponses.add(exportTeacherResponse);



			}
		}
        return exportTeacherResponses;

    }

	@Override
	public TeacherListResponse getTeacherListAssPage(Long schoolId, String teacherCode, String teacherName, String className, Long courseId,Integer pageNo,Integer pageSize) {
    	TeacherListResponse teacherListResponse = new TeacherListResponse();
    	Pageinfo pageinfo = new Pageinfo();
    	pageinfo.setPageSize(pageSize);
    	pageinfo.setPageNo(pageNo);
    	List<TeacherSelectAss> teacherSelectAsses = teacherMapperExt.selectTeacherListAssPage(pageinfo,schoolId,teacherCode,teacherName,className,courseId);

    	teacherListResponse.setPageinfo(pageinfo);
    	teacherListResponse.setTeachers(teacherSelectAsses);

		return teacherListResponse;
	}

	
	@Override
	public void updatePwd(Long teachterid,UpdatePwdRequest pwdRequest) {
			
		String oldpwd=pwdRequest.getPasswordOld();
		String newpwd=pwdRequest.getPasswordNew();
		
		TTeacher teacher=teacherMapperExt.selectByPrimaryKey(teachterid);
		
		if(teacher==null) {
			throw new ServiceException(500, "没有找到这个教师");
		}
		
		
		YUser user=userMapperExt.selectByPrimaryKey(teacher.getAccountId());
		if(user==null) {
			throw new ServiceException(500, "没有找到个老师的账号");
		}
		

		if(!oldpwd.equals(user.getPassword())){
			throw new ServiceException(500, "旧密码不对");
		}
		
		user.setPassword(newpwd);
		userMapperExt.updateByPrimaryKeySelective(user);
		
		
	}

}
