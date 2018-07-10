package com.turingmaker.dao.mapper.operation.ext;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import com.turingmaker.dao.mapper.operation.YUserMapper;
import com.turingmaker.entity.operation.YUser;

/**
 * 
 * @author tzj
 *
 */
public interface UserMapperExt extends YUserMapper{

	
	/**
	 * 检查改用户是否有足够权限
	 * @param username
	 * @param rolename
	 * @return
	 */
	 @Select({
		 	"select COUNT(0)  from `Y_USER` T_U " + 
		 	"where `ID` IN " + 
		 	"(" + 
		 	"select T_RU.`USER_ID`  from `Y_ROLE_USER` T_RU  where ROLE_ID =" + 
		 	"(select T_R.`ID`   from `Y_ROLE` T_R where `ROLE_NAME`=#{arg1}  LIMIT 1)" + 
		 	") " +  
		 	"AND `ACCOUNT` =#{arg0}"
	    })
	  @ResultType(Boolean.class)
	    Boolean checkUserHasRole(String username,String rolename);
	 
	 
	 
	 /**
	  * 根据用户名称查询用户信息
	  * @param username 用户名
	  * @return
	  */
	 @Select({
	        "select",
	        "ID, ACCOUNT, PASSWORD, CREATE_TIME",
	        "from Y_USER",
	        "where ACCOUNT = #{arg0,jdbcType=BIGINT}"
	    })
	 @Results({
	        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
	        @Result(column="ACCOUNT", property="account", jdbcType=JdbcType.VARCHAR),
	        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
	        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
	    })
	 public YUser findUserByUserName(String username);
	 
	 
	 /**
	  * 生成账号  依赖存储过程
	  * @param paramMap
	  */
	 @Select(value=" call generateAccount(" + 
	 		"  #{param.accounttype,mode=IN,jdbcType=INTEGER}," + 
	 		"  #{param.accountname,mode=OUT,jdbcType=VARCHAR});")
	 @Options(statementType=StatementType.CALLABLE)
	void generateAccount(@Param("param") Map<String, Object> paramMap);
}
