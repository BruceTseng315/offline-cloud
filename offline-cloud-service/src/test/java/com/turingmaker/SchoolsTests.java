package com.turingmaker;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.operation.request.SaveSchoolRequest;
import com.turingmaker.service.school.api.SchoolService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CloudApplication.class)
public class SchoolsTests {

	
	@Autowired
	SchoolService schoolService;


	@Test
	public void  test1() {

		


//		SaveSchoolRequest request= new SaveSchoolRequest();
//		request.setAreaCode(820000l);
//		request.setChannelName("s");
//		schoolService.saveSchool(request);
		System.out.println(
		schoolService.getSchoolListNew(null,null,null,null,null,1,60));

	}
	
//	@Autowired
//	AuthService authService;
//	
//	@Autowired
//	UserMapperExt userMapperEXT;
//	
//	@Test
//	public void test2() {
//		
//	Map<String, Object> map=new HashMap<>();
//	map.put("accounttype", 1);
//	userMapperEXT.generateAccount(map);
//	System.out.println(map);
//	//	authService.userLoginByPassword("root", "root123456", "ROLE_OPERATION");
//	
//	//System.out.println(	authService.checkUserHasRole("root", "ROLE_OPERATION"));	
//	}
}
