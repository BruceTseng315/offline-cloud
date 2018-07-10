package com.turingmaker.dao.mapper.school.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import com.turingmaker.service.operation.response.Pageinfo;

public class SchoolSqlProvider {

	public String selectForListSchool(Pageinfo pageinfo, @Param("param1") String schoolCode,
			@Param("param2") String schoolName, @Param("param3") String adminName, @Param("param4") Integer regionCode,
			@Param("param5") Byte type) {
		SQL sql = new SQL();
		sql.SELECT("t1.*");
		sql.FROM("T_SCHOOL t1,T_SCHOOL_ADMIN t2 ");

		// COURSE_CODE, COURSE_NAME, COURSE_DESCRIPTION, COURSE_AVATAR, COURSE_STATE
		if (!StringUtils.isEmpty(schoolCode)) {
			sql.WHERE("t1.SCHOOL_CODE like CONCAT('%',#{param1},'%')");
		}

		if (!StringUtils.isEmpty(schoolName)) {
			sql.WHERE("t1.SCHOOL_NAME like CONCAT('%',#{param2},'%')");
		}

		if (!StringUtils.isEmpty(adminName)) {
			sql.WHERE("t2.NAME like CONCAT('%',#{param3},'%')");
		}

		if (!StringUtils.isEmpty(regionCode)) {
			sql.WHERE("t1.AREA_CODE like CONCAT('%',#{param4},'%')");
		}

		if (!StringUtils.isEmpty(type)) {
			sql.WHERE("t1.SCHOOL_TYPE like CONCAT('%',#{param5},'%')");
		}

		sql.WHERE("t1.ID=t2.SCHOOL_ID");
		return sql.toString();
	}


	/**
	 * 查询学校新的
	 * @param
	 * @param schoolCode
	 * @param schoolName
	 * @param adminName
	 * @param regionCode
	 * @param type
	 * @return
	 */
	public String selectForListSchoolPageNew(List<Long> ids, @Param("param1") String schoolCode,
			@Param("param2") String schoolName, @Param("param3") String adminName, @Param("param4") Integer regionCode,
			@Param("param5") Byte type) {

		StringBuilder builder = new StringBuilder(
				"select tr1.*,tr2.id as TCID,tr2.COURSE_NAME,getAeraNameForCodeFunc(tr1.AREA_CODE,'-') as AREA_NAME  from  " + 
				"(" + 
				"select  ts.*,tsa.`NAME` ,tsa.`PHONE` , " + 
				"(select count(0) from `T_CLASS` tclass where tclass.`SCHOOL_ID` =ts.id) as CLASSCOUNT, " + 
				"(select count(0) from `T_TEACHER` tteacher where tteacher.`SCHOOL_ID` =ts.id) as TEACHTERCOUNT, " + 
				"(select COUNT(0)  from `T_CLASS_STUDENT` WHERE CLASS_ID IN (SELECT ID FROM `T_CLASS` WHERE `SCHOOL_ID` =ts.`ID` )) as STUDENTCOUNT " + 
				"from `T_SCHOOL` ts  LEFT JOIN `T_SCHOOL_ADMIN`  tsa on tsa.`SCHOOL_ID` =ts.`ID` " + 
				") tr1  " + 
				"LEFT JOIN " + 
				"(" + 
				"select tc.id,tc.`COURSE_NAME`,tsc.`SCHOOL_ID`   from `T_COURSE`  tc " +
				"" + 
				"left  join T_SCHOOL_COURSE tsc  on tc.id=tsc.`COURSE_ID` " + 
				") tr2 " + 
				"on tr2.SCHOOL_id=tr1.id");

		builder.append(" WHERE 1=1");
		
		if (!StringUtils.isEmpty(schoolCode)) { 
			builder.append(" AND tr1.SCHOOL_CODE like CONCAT('%',#{param1},'%')");
		}

		if (!StringUtils.isEmpty(schoolName)) {
			builder.append(" AND tr1.SCHOOL_NAME like CONCAT('%',#{param2},'%')");
		}

		if (!StringUtils.isEmpty(adminName)) {
			builder.append(" AND tr1.NAME like  CONCAT('%',#{param3},'%')");
		}

		if (!StringUtils.isEmpty(regionCode)) {
			builder.append(" AND tr1.AREA_CODE like  CONCAT(#{param4},'%') ");
		}

		if (!StringUtils.isEmpty(type)) {
			builder.append(" AND tr1.SCHOOL_TYPE like CONCAT('%',#{param5},'%')");
		}
		
		if(ids!=null&&!ids.isEmpty()) {
			
			builder.append(" AND tr1.ID in");
			builder.append("(");
			ids.forEach((id)->{
				builder.append("'"+id+"',");
				
			});
			builder.append(")");
		}

		builder.append(" ORDER BY tr1.id DESC");
		return builder.toString().replaceAll(",\\)", ")");

	}

	
	
	/**
	 * 专门查询分页的
	 * @param pageinfo
	 * @param schoolCode
	 * @param schoolName
	 * @param adminName
	 * @param regionCode
	 * @param type
	 * @return
	 */
	public String selectListSchoolNewCalPageTotal(Pageinfo pageinfo, @Param("param1") String schoolCode,
			@Param("param2") String schoolName, @Param("param3") String adminName, @Param("param4") Integer regionCode,
			@Param("param5") Byte type) {

	
		
		StringBuilder builder=new StringBuilder("select count(0) from");
		builder.append("(");
		builder.append(createSelectIdSQL(pageinfo, schoolCode, schoolName, adminName, regionCode, type));
		builder.append(")");
		builder.append(" t");

		return builder.toString();

	}

  
	
	/**
	 * 专门查询分页的
	 * @param pageinfo
	 * @param schoolCode
	 * @param schoolName
	 * @param adminName
	 * @param regionCode
	 * @param type
	 * @return
	 */
	public String selectIdsForListSchool(Pageinfo pageinfo, @Param("param1") String schoolCode,
			@Param("param2") String schoolName, @Param("param3") String adminName, @Param("param4") Integer regionCode,
			@Param("param5") Byte type) {

		StringBuilder  builder=new StringBuilder();
		
		builder.append(createSelectIdSQL(pageinfo, schoolCode, schoolName, adminName, regionCode, type));
		builder.append(" ORDER BY tr1.ID DESC ");
		if(pageinfo!=null) {
			if(pageinfo.getPageNo()>0) { 
				builder.append(" LIMIT ");
				builder.append((pageinfo.getPageNo() - 1) * pageinfo.getPageSize());
				builder.append(",");
				builder.append(pageinfo.getPageSize());
			}
			
		}
		return builder.toString();

	}

	
	/**
	 * 构造一个查询排重id的
	 * @param pageinfo
	 * @param schoolCode
	 * @param schoolName
	 * @param adminName
	 * @param regionCode
	 * @param type
	 * @return
	 */
	String createSelectIdSQL(Pageinfo pageinfo, @Param("param1") String schoolCode,
			@Param("param2") String schoolName, @Param("param3") String adminName, @Param("param4") Integer regionCode,
			@Param("param5") Byte type) {
		
		
		
		
		StringBuilder builderQuery=new StringBuilder(""
				+ "select DISTINCT tr1.ID  from  " + 
				"(" + 
				"select  ts.*,tsa.`NAME` ,tsa.`PHONE` , " + 
				"(select count(0) from `T_CLASS` tclass where tclass.`SCHOOL_ID` =ts.id) as CLASSCOUNT, " + 
				"(select count(0) from `T_TEACHER` tteacher where tteacher.`SCHOOL_ID` =ts.id) as TEACHTERCOUNT, " + 
				"(select COUNT(0)  from `T_CLASS_STUDENT` WHERE CLASS_ID IN (SELECT ID FROM `T_CLASS` WHERE `SCHOOL_ID` =ts.`ID` )) as STUDENTCOUNT " + 
				"from `T_SCHOOL` ts  LEFT JOIN `T_SCHOOL_ADMIN`  tsa on tsa.`SCHOOL_ID` =ts.`ID` " + 
				") tr1  " + 
				"LEFT JOIN " + 
				"(" + 
				"select tc.id,tc.`COURSE_NAME`,tsc.`SCHOOL_ID`   from `T_COURSE`  tc " +
				"" + 
				"left  join T_SCHOOL_COURSE tsc  on tc.id=tsc.`COURSE_ID` " + 
				") tr2 " + 
				"on tr2.SCHOOL_id=tr1.id");
		
		builderQuery.append(" WHERE 1=1");
		
		if (!StringUtils.isEmpty(schoolCode)) { 
			builderQuery.append(" AND tr1.SCHOOL_CODE like CONCAT('%',#{param1},'%')");
		}

		if (!StringUtils.isEmpty(schoolName)) {
			builderQuery.append(" AND tr1.SCHOOL_NAME like CONCAT('%',#{param2},'%')");
		}

		if (!StringUtils.isEmpty(adminName)) {
			builderQuery.append(" AND tr1.NAME like  CONCAT('%',#{param3},'%')");
		}

		if (!StringUtils.isEmpty(regionCode)) {
			builderQuery.append(" AND tr1.AREA_CODE like  CONCAT(#{param4},'%')");
		}

		if (!StringUtils.isEmpty(type)) {
			builderQuery.append(" AND tr1.SCHOOL_TYPE like CONCAT('%',#{param5},'%')");
		}
		
		
	

		return builderQuery.toString();
	}
}
