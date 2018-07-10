package com.turingmaker.service.operation.imp;


import com.turingmaker.config.Constant;
import com.turingmaker.dao.mapper.operation.ext.TAreaMapperExt;
import com.turingmaker.dao.mapper.school.ext.TSchoolAdminMapperExt;
import com.turingmaker.dao.mapper.school.ext.TSchoolCourseMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TSchool;
import com.turingmaker.entity.school.TSchoolAdmin;
import com.turingmaker.entity.school.TSchoolCourse;
import com.turingmaker.service.operation.entity.QuerySchoolResult;
import com.turingmaker.service.operation.request.SaveSchoolRequest;
import com.turingmaker.service.operation.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turingmaker.dao.mapper.operation.TAreaMapper;
import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;

import com.turingmaker.dao.mapper.school.ext.TSchoolMapperExt;

import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tzj
 *
 */
@Service
public class SchoolServiceImpl implements  com.turingmaker.service.school.api.SchoolService{

	
	@Autowired
	TSchoolMapperExt schoolMapperExt;
	
	@Autowired
	TSchoolCourseMapperExt schoolCourseMapperExt;

	@Autowired
	TSchoolAdminMapperExt schoolAdminMapperExt;

	@Autowired
	UserMapperExt userMapperExt;

	@Autowired
	CourseMapperExt courseMapperExt;

	@Autowired
	TAreaMapperExt areaMapper;

	@Autowired
	AuthService authService;

	
	Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * 添加学校并且添加学校的课程
	 */
	@Override
	@Transactional
	public void saveSchool(SaveSchoolRequest schoolRequest) {

		TSchool school=new TSchool();
		BeanUtils.copyProperties(schoolRequest, school);
		school.setAreaCode(schoolRequest.getRegionCode());
		schoolMapperExt.insertSelective(school);
		//学校编号 前缀+学校id（5位）
		school.setSchoolCode(Constant.SCHOOL_CODE_PREFIX + String.format("%05d",school.getId()));
		schoolMapperExt.updateByPrimaryKeySelective(school);

		//添加生成运营端账号
		String account = authService.generateAccount(2);
		YUser user = userMapperExt.findUserByUserName(account);
		TSchoolAdmin schoolAdmin = new TSchoolAdmin();
		schoolAdmin.setName(schoolRequest.getAdminName());
		schoolAdmin.setPhone(schoolRequest.getAdminPhone());
		schoolAdmin.setSchoolId(school.getId());
		schoolAdmin.setAccountId(user.getId());
		schoolAdminMapperExt.insertSelective(schoolAdmin);
		
		if(schoolRequest.getCourses()!=null) {
			schoolRequest.getCourses().forEach((e)->{
				TSchoolCourse schoolCourse=new TSchoolCourse();
				schoolCourse.setSchoolId(school.getId());
				schoolCourse.setCourseId(e);
				schoolCourseMapperExt.insertSelective(schoolCourse);
			});
		}
	}

	@Override
	public YUser getSchoolAdmin(Long schoolId) {
		TSchoolAdmin schoolAdmin = schoolAdminMapperExt.findBySchoolId(schoolId);
		YUser user = userMapperExt.selectByPrimaryKey(schoolAdmin.getAccountId());
		return user;
	}

	@Override
	public TSchoolAdmin getSchoolAdminInfo(Long schoolId) {
		TSchoolAdmin schoolAdmin = schoolAdminMapperExt.findBySchoolId(schoolId);
		return schoolAdmin;
	}

	@Override
	public TSchool findBySchoolId(Long schoolId) {
		TSchool school = schoolMapperExt.selectByPrimaryKey(schoolId);
		return school;
	}

	@Override
	@Transactional
	public List<SchoolCourseInfo> findCoursesBySchoolId(Long schoolId){
		List<TSchoolCourse> schoolCourses = schoolCourseMapperExt.findAllBySchoolId(schoolId);
		List<SchoolCourseInfo> courses = new ArrayList<>();
		for(int i=0;i<schoolCourses.size();i++){
			SchoolCourseInfo schoolCourse = new SchoolCourseInfo();
			BeanUtils.copyProperties(courseMapperExt.selectByPrimaryKey(schoolCourses.get(i).getCourseId()),schoolCourse);
			schoolCourse.setState(schoolCourses.get(i).getState());
			courses.add(schoolCourse);
		}
		return courses;
	}

	@Override
	@Transactional
	public void editSchool(SaveSchoolRequest schoolRequest){
		TSchool school = schoolMapperExt.selectByPrimaryKey(schoolRequest.getId());
		BeanUtils.copyProperties(schoolRequest, school);
		school.setAreaCode(schoolRequest.getRegionCode());
		schoolMapperExt.updateByPrimaryKeySelective(school);
		TSchoolAdmin schoolAdmin = schoolAdminMapperExt.findBySchoolId(schoolRequest.getId());
		schoolAdmin.setName(schoolRequest.getAdminName());
		schoolAdmin.setPhone(schoolRequest.getAdminPhone());
		schoolAdminMapperExt.updateByPrimaryKeySelective(schoolAdmin);
		//refresh=1时重置密码
		if(schoolRequest.getRefresh()==1){
			YUser user = userMapperExt.selectByPrimaryKey(schoolAdmin.getAccountId());
			user.setPassword("123456");
			userMapperExt.updateByPrimaryKeySelective(user);
		}
		if(schoolRequest.getCourseList()!=null) {
			for(int i=0;i<schoolRequest.getCourseList().size();i++){
				Long courseId = schoolRequest.getCourseList().get(i).getId();
				TSchoolCourse schoolCourse = schoolCourseMapperExt.findBySchoolIdAndCourseId(schoolRequest.getId(),courseId);
				Long state = Long.valueOf(schoolRequest.getCourseList().get(i).getState());
				Byte istate = 0;
				if(state==1){
					istate = 1;
				}
				if(schoolCourse==null){
					TSchoolCourse course=new TSchoolCourse();
					course.setSchoolId(school.getId());
					course.setCourseId(courseId);
					course.setState(istate);
					schoolCourseMapperExt.insertSelective(course);
				}else {
					schoolCourse.setState(istate);
					schoolCourseMapperExt.updateByPrimaryKeySelective(schoolCourse);
				}
			}
		}
	}

	@Override
	@Transactional
	public void updateState(Long schoolId ,Long courseId ,Byte state){
		TSchoolCourse schoolCourse = schoolCourseMapperExt.findBySchoolIdAndCourseId(schoolId,courseId);
		schoolCourse.setState(state);
		schoolCourseMapperExt.updateByPrimaryKeySelective(schoolCourse);
	}

	@Override
	@Transactional
	public List<SchoolCourseInfo> getEnableCourse(Long schoolId){
		List<TCourse> courses = courseMapperExt.getEnableCourse(schoolId);
		List<SchoolCourseInfo> schoolCourseInfoList = new ArrayList<>();
		for(TCourse x:courses){
			SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
			BeanUtils.copyProperties(x,schoolCourseInfo);
			schoolCourseInfo.setState((byte) 1);
			schoolCourseInfoList.add(schoolCourseInfo);

		}
		return schoolCourseInfoList;
	}

	@Override
	@Transactional
	public SchoolListResponse getSchoolList(String schoolCode, String schoolName, String adminName, Integer regionCode, Byte type, Integer pageNo, Integer pageSize){
		Pageinfo page = new Pageinfo();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<TSchool> list = schoolMapperExt.selectForListSchoolPage(page,schoolCode,schoolName,adminName,regionCode,type);
		SchoolListResponse schoolListResponse = new SchoolListResponse();
		schoolListResponse.setPageinfo(page);
		if(list.size()<=0){
			return  schoolListResponse;
		}

		List<SchoolInfo> schoolInfoList = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			SchoolInfo schoolInfo = new SchoolInfo();
			schoolInfo.setSchoolId(list.get(i).getId());
			schoolInfo.setTeacherCount(schoolMapperExt.fingTeacherCountBySchoolId(list.get(i).getId()));
			schoolInfo.setStudentCount(schoolMapperExt.fingStudentCountBySchoolId(list.get(i).getId()));
			schoolInfo.setClassCount(schoolMapperExt.fingClassCountBySchoolId(list.get(i).getId()));
			schoolInfo.setSchoolCode(list.get(i).getSchoolCode());
			schoolInfo.setSchoolName(list.get(i).getSchoolName());
			schoolInfo.setAdminName(schoolAdminMapperExt.findBySchoolId(list.get(i).getId()).getName());
			schoolInfo.setRegion(getAreaDetailById(list.get(i).getAreaCode()));
			List<TSchoolCourse> schoolCourses = schoolCourseMapperExt.findAllBySchoolId(list.get(i).getId());
			List<String> courses = new ArrayList<>();
			for(int j=0;j<schoolCourses.size();j++){
				courses.add(courseMapperExt.selectByPrimaryKey(schoolCourses.get(j).getCourseId()).getCourseName());
			}
			schoolInfo.setCourses(courses);
			schoolInfoList.add(schoolInfo);
		}
		schoolListResponse.setSchoolInfoList(schoolInfoList);

		return schoolListResponse;
	}

	@Override
	
	public Map<String,AreaResponse> getAreaDetailById(Long areaId){
		Map<String,AreaResponse> map = new HashMap<>();
		Long cpid = areaMapper.selectByPrimaryKey(areaId).getAreaPid();
		if(cpid!=100000){
			Long ppid = areaMapper.selectByPrimaryKey(cpid).getAreaPid();
			if(ppid!=100000){
				AreaResponse areaResponseD = new AreaResponse();
				areaResponseD.setAreaId(areaId);
				areaResponseD.setAreaName(areaMapper.selectByPrimaryKey(areaId).getAreaName());
				AreaResponse areaResponseC = new AreaResponse();
				areaResponseC.setAreaId(cpid);
				areaResponseC.setAreaName(areaMapper.selectByPrimaryKey(cpid).getAreaName());
				AreaResponse areaResponseP = new AreaResponse();
				areaResponseP.setAreaId(ppid);
				areaResponseP.setAreaName(areaMapper.selectByPrimaryKey(ppid).getAreaName());
				map.put("province",areaResponseP);
				map.put("city",areaResponseC);
				map.put("district",areaResponseD);
			}else {
				AreaResponse areaResponseC = new AreaResponse();
				areaResponseC.setAreaId(areaId);
				areaResponseC.setAreaName(areaMapper.selectByPrimaryKey(areaId).getAreaName());
				AreaResponse areaResponseP = new AreaResponse();
				areaResponseP.setAreaId(cpid);
				areaResponseP.setAreaName(areaMapper.selectByPrimaryKey(cpid).getAreaName());
				map.put("province",areaResponseP);
				map.put("city",areaResponseC);
				map.put("district",null);
			}
		}else{
			AreaResponse areaResponse = new AreaResponse();
			areaResponse.setAreaId(areaId);
			areaResponse.setAreaName(areaMapper.selectByPrimaryKey(areaId).getAreaName());
			map.put("province",areaResponse);
			map.put("city",null);
			map.put("district",null);
		}
		return map;
	}

	@Override
	public SchoolListResponse getSchoolListNew(String schoolCode, String schoolName, String adminName,
			Integer regionCode, Byte type, Integer pageNo, Integer pageSize) throws ServiceException {
		
		Pageinfo pageinfo=new Pageinfo(pageNo, pageSize);
		
		if(regionCode!=null) {			
			regionCode=Integer.valueOf(regionCode.toString().replaceAll("0+$", ""));
		}
		
		
		pageinfo.setTotalCount(
				schoolMapperExt.selectListSchoolNewCalPageTotal(pageinfo, schoolCode, schoolName, adminName, regionCode, type).intValue());
		
		
		List<Long> ids=schoolMapperExt.selectIdsForListSchool(pageinfo, schoolCode, schoolName, adminName, regionCode, type);
		
		List<QuerySchoolResult> querySchoolResults=schoolMapperExt.selectForListSchoolPageNew(ids,
				schoolCode, schoolName, 
				adminName, regionCode, type);
		
		
		SchoolListResponse listResponse=new SchoolListResponse();
		List<SchoolInfo> infos=new ArrayList<>();
	
		
		Map<Long, List<String>> map=new HashMap<>();
		List<String> courseNames=null;
		SchoolInfo info=null;
		for(QuerySchoolResult querySchoolResult:querySchoolResults) {
			
			Long id=querySchoolResult.getId();
			
			if(!map.containsKey(id)) {
				map.put(id, courseNames=new ArrayList<>());
				info=new SchoolInfo();
				info.setCourses(courseNames);
				courseNames.add(querySchoolResult.getCourseName());
			}else {
				
				map.get(id).add(querySchoolResult.getCourseName());
				continue;
			}
			

			info.setAdminName(querySchoolResult.getAdminname());
			info.setClassCount(querySchoolResult.getClassCount());
			info.setSchoolCode(querySchoolResult.getSchoolCode());
			info.setSchoolId(querySchoolResult.getId());
			info.setSchoolName(querySchoolResult.getSchoolName());
			info.setStudentCount(querySchoolResult.getStudentCount());
			info.setTeacherCount(querySchoolResult.getTeacherCount());
			info.setAreaName(querySchoolResult.getAreaName());
			
			
			infos.add(info);
		}
		
		listResponse.setPageinfo(pageinfo);
		listResponse.setSchoolInfoList(infos);
		return listResponse;
	}

}
