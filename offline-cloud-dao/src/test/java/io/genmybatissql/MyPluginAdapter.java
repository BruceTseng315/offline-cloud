package io.genmybatissql;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * auth  tzj
 */
public class MyPluginAdapter extends PluginAdapter {

	/**
	 * mapper
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

//		createSearcMethodhForExample(interfaze, topLevelClass, introspectedTable);
//		createSearcMethodhForListWhere(interfaze, topLevelClass, introspectedTable);
//		
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
		createSearcCountMethodhForExample(interfaze, topLevelClass, introspectedTable);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		interfaze.addJavaDocLine("/**");
		interfaze.addJavaDocLine("@author tzj");
		interfaze.addJavaDocLine(""+dateFormat.format(new Date()));
		interfaze.addJavaDocLine("*/");
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	/**
	 * 生成mapper的 searchByPage 方法
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	public void createSearcMethodhForExample(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.type.JdbcType"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Result"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Results"));

		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.SelectProvider"));
		Method method = new Method("selectByPage");
		
		Parameter parameterParam=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		Parameter parameterPageNo=new Parameter(new FullyQualifiedJavaType("int"), "pageNo");
		Parameter parameterPageCount=new Parameter(new FullyQualifiedJavaType("int"), "pageCount");
		parameterParam.addAnnotation("@Param(\"param\")");
		parameterPageNo.addAnnotation("@Param(\"pageNo\")");
		parameterPageCount.addAnnotation("@Param(\"pageCount\")");
		
		method.addParameter(parameterParam);
		method.addParameter(parameterPageNo);
		method.addParameter(parameterPageCount);

		method.setReturnType(new FullyQualifiedJavaType(
				"java.util.List<" + introspectedTable.getRules().calculateAllFieldsClass() + ">"));

		method.addAnnotation("@SelectProvider(type=" + introspectedTable.getMyBatis3SqlProviderType()
				+ ".class,method=\"selectByPage\")");


		method.addAnnotation(createResultsAnn(introspectedTable));
		interfaze.addMethod(method);
	}
	
	/**
	 * 生产mapper的有查询count条数的方法
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	public void createSearcCountMethodhForExample(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		
		
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.SelectProvider"));
		Method method = new Method("selectForCount");
		Parameter parameterParam=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		parameterParam.addAnnotation("@Param(\"param\")");
		method.addParameter(parameterParam);


		method.setReturnType(new FullyQualifiedJavaType(
				"int"));

		method.addAnnotation("@SelectProvider(type=" + introspectedTable.getMyBatis3SqlProviderType()
				+ ".class,method=\"selectForCount\")");


		interfaze.addMethod(method);
	}
	
	
	

	/**
	 *根据where条件字符串  生成mapper的 searchByPage 方法
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	public void createSearcMethodhForListWhere(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.type.JdbcType"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Result"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Results"));

		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.SelectProvider"));
		Method method = new Method("selectByPageForListWhere");
		
		Parameter parameterParam=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		Parameter parameterList=new Parameter(new FullyQualifiedJavaType("String..."), "whereCause");
		Parameter parameterPageNo=new Parameter(new FullyQualifiedJavaType("int"), "pageNo");
		Parameter parameterPageCount=new Parameter(new FullyQualifiedJavaType("int"), "pageCount");
		parameterParam.addAnnotation("@Param(\"param\")");
		parameterList.addAnnotation("@Param(\"whereCause\")");
		parameterPageNo.addAnnotation("@Param(\"pageNo\")");
		parameterPageCount.addAnnotation("@Param(\"pageCount\")");
		
		method.addParameter(parameterParam);
		method.addParameter(parameterPageNo);
		method.addParameter(parameterPageCount);
		method.addParameter(parameterList);
		

		method.setReturnType(new FullyQualifiedJavaType(
				"java.util.List<" + introspectedTable.getRules().calculateAllFieldsClass() + ">"));

		method.addAnnotation("@SelectProvider(type=" + introspectedTable.getMyBatis3SqlProviderType()
				+ ".class,method=\"selectByPageForListWhere\")");


		method.addAnnotation(createResultsAnn(introspectedTable));
		interfaze.addMethod(method);
	}
	
	
	
	/**
	 * 生成 注解@Result的字符串
	 * @param introspectedTable
	 * @return
	 */
	String createResultsAnn(	IntrospectedTable introspectedTable) {
		
		StringBuilder builder = new StringBuilder("@Results({\n");
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		for (IntrospectedColumn column : columns) {
			String columnname = column.getActualColumnName();
			String javapropername = column.getJavaProperty();
			builder.append("@Result(column=\"" + columnname + "\", property=\"" + javapropername
					+ "\", jdbcType=JdbcType." + column.getJdbcTypeName() + "),");
			builder.append("\n");
		}
		builder.append(" })");
		
		return builder.toString().replaceAll(",\\s\\}", "}");
	}

	/**
	 * SQL提供类
	 */
	@Override
	public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");


		topLevelClass.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine("@author tzj");
		topLevelClass.addJavaDocLine(""+dateFormat.format(new Date()));
		topLevelClass.addJavaDocLine("*/");
//		createProviderSearch(topLevelClass, introspectedTable);
//		createProviderSearchForListWhere(topLevelClass, introspectedTable);
		createProviderSearchCount(topLevelClass, introspectedTable);
		createSqlAppendMethod(topLevelClass,introspectedTable);
		return super.providerGenerated(topLevelClass, introspectedTable);
	}

	/**
	 * 生成provider的searchByPage方法
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	void createProviderSearch(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Method method = new Method("selectByPage");

		 Parameter parameterparam=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		 parameterparam.addAnnotation("@Param(\"param\")");
		method.addParameter(parameterparam);

		Parameter parameterPageNo=new Parameter(new FullyQualifiedJavaType("int"), "pageNo");
		parameterPageNo.addAnnotation("@Param(\"pageNo\")");
		method.addParameter(parameterPageNo);


		Parameter parameterCount=new Parameter(new FullyQualifiedJavaType("int"), "pageCount");
		parameterCount.addAnnotation("@Param(\"pageCount\")");
		method.addParameter(parameterCount);

		method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));

		/**
		 * SQL sql = new SQL(); sql.INSERT_INTO("trc_query_order");
		 */

		method.setVisibility(JavaVisibility.PUBLIC);
		method.addBodyLine("SQL sql = new SQL();");
		method.addBodyLine("sql.SELECT(\"*\");");
		method.addBodyLine("sql.FROM(\"" + introspectedTable.getFullyQualifiedTable() + "\");");

		method.addBodyLine("appendSql(param,sql);");

		method.addBodyLine("StringBuilder builder=new StringBuilder();");

		method.addBodyLine("builder.append(sql.toString());");

		method.addBodyLine("if(pageNo>0&&pageCount>0) {");
		method.addBodyLine(" builder.append(\" limit \"+(pageNo-1)*pageCount+\",\"+pageCount);");// limit (2-1)*10,100
		method.addBodyLine(" }");

		method.addBodyLine(" return  builder.toString(); ");

		topLevelClass.addMethod(method);
	}
	
	
	
	
	/**
	 * 生成provider的searchByPage方法
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	void createProviderSearchCount(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Method method = new Method("selectForCount");

		Parameter parameter=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		parameter.addAnnotation("@Param(\"param\")");
		method.addParameter(parameter);


		method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));

		/**
		 * SQL sql = new SQL(); sql.INSERT_INTO("trc_query_order");
		 */

		method.setVisibility(JavaVisibility.PUBLIC);
		method.addBodyLine("SQL sql = new SQL();");
		method.addBodyLine("sql.SELECT(\"count(0)\");");
		method.addBodyLine("sql.FROM(\"" + introspectedTable.getFullyQualifiedTable() + "\");");

		method.addBodyLine("appendSql(param,sql);");

		method.addBodyLine("StringBuilder builder=new StringBuilder();");

		method.addBodyLine("builder.append(sql.toString());");


		method.addBodyLine(" return  builder.toString(); ");

		topLevelClass.addMethod(method);
	}
	
	
	
	
	
	
	/**根据where条件
	 * 生成provider的searchByPage方法
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	void createProviderSearchForListWhere(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		Method method = new Method("selectByPageForListWhere");

		Parameter parameterparam=new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param");
		parameterparam.addAnnotation("@Param(\"param\")");
		method.addParameter(parameterparam);

		Parameter parameterPageNo=new Parameter(new FullyQualifiedJavaType("int"), "pageNo");
		parameterPageNo.addAnnotation("@Param(\"pageNo\")");
		method.addParameter(parameterPageNo);


		Parameter parameterCount=new Parameter(new FullyQualifiedJavaType("int"), "pageCount");
		parameterCount.addAnnotation("@Param(\"pageCount\")");
		method.addParameter(parameterCount);


		method.addParameter(new Parameter(new FullyQualifiedJavaType("String..."), "whereCause"));
		
		
		method.setReturnType(new FullyQualifiedJavaType("java.lang.String"));

		/**
		 * SQL sql = new SQL(); sql.INSERT_INTO("trc_query_order");
		 */

		
		
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addBodyLine("SQL sql = new SQL();");
		method.addBodyLine("sql.SELECT(\"*\");");
		method.addBodyLine("sql.FROM(\"" + introspectedTable.getFullyQualifiedTable() + "\");");

		
		/**
		 * searchBy example 条件添加
		 */
		method.addBodyLine("appendSql(param,sql);");
		

		method.addBodyLine("if(whereCause!=null){");
		method.addBodyLine("for(String where:whereCause){");
		method.addBodyLine("sql.WHERE(where);");
		method.addBodyLine("}");
		
		method.addBodyLine("}");

		method.addBodyLine("StringBuilder builder=new StringBuilder();");

		method.addBodyLine("builder.append(sql.toString());");

		method.addBodyLine("if(pageNo>0&&pageCount>0) {");
		method.addBodyLine(" builder.append(\" limit \"+(pageNo-1)*pageCount+\",\"+pageCount);");// limit (2-1)*10,100
		method.addBodyLine(" }");

		method.addBodyLine(" return  builder.toString(); ");

		topLevelClass.addMethod(method);
	}


	/**根据where条件
	 * 生成provider的searchByPage方法
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	void createSqlAppendMethod(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {


		/**
		 *
		 *  if(param!=null){
		 *             if (param.getId() != null) {
		 *                 sql.WHERE("`id`=#{param.id}");
		 *             }
		 *             if (param.getIdentifier() != null) {
		 *                 sql.WHERE("`identifier`=#{param.identifier}");
		 *             }
		 *             if (param.getBindStatus() != null) {
		 *                 sql.WHERE("`bind_status`=#{param.bindStatus}");
		 *             }
		 *             if (param.getUid() != null) {
		 *                 sql.WHERE("`uid`=#{param.uid}");
		 *             }
		 *             if (param.getSelf() != null) {
		 *                 sql.WHERE("`self`=#{param.self}");
		 *             }
		 *             if (param.getType() != null) {
		 *                 sql.WHERE("`type`=#{param.type}");
		 *             }
		 *             if (param.getCreateTime() != null) {
		 *                 sql.WHERE("`create_time`=#{param.createTime}");
		 *             }
		 *             if (param.getUpdateTime() != null) {
		 *                 sql.WHERE("`update_time`=#{param.updateTime}");
		 *             }
		 *         }
		 *
		 */

		Method method = new Method("appendSql");

		method.addParameter(new Parameter(introspectedTable.getRules().calculateAllFieldsClass(), "param"));

		method.addParameter(new Parameter(new FullyQualifiedJavaType("org.apache.ibatis.jdbc.SQL"), "sql"));

		method.setReturnType(new FullyQualifiedJavaType("org.apache.ibatis.jdbc.SQL"));

		method.setVisibility(JavaVisibility.PUBLIC);



		/**
		 * searchBy example 条件添加
		 */


		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();

		method.addBodyLine("if(param!=null){");
		for (IntrospectedColumn column : columns) {

			String javapropername = column.getJavaProperty();
			String getPname = javapropername;


			if(getPname.length()>1) {
				if(!Character.isUpperCase(getPname.charAt(1))) {
					getPname = Character.toUpperCase(getPname.charAt(0)) + getPname.substring(1);
				}
			}
			method.addBodyLine("if (param.get" + getPname + "() != null) {");
			method.addBodyLine(" sql.WHERE(\"`"+column.getActualColumnName() + "`=#{param." + javapropername + "}\");");
			method.addBodyLine("}");

		}
		method.addBodyLine("}");

		method.addBodyLine(" return  sql;");

		topLevelClass.addMethod(method);
	}


	/**
	 * 生产toString方法
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		/**
		 *
		 * @Override
		 * 	public String toString() {
		 * 		return "SUser [uid=" + uid + ", studentId=" + studentId + ", password=" + password + ", nickName=" + nickName
		 * 				+ ", realName=" + realName + ", userRole=" + userRole + ", avatar=" + avatar + ", gender=" + gender
		 * 				+ ", email=" + email + ", phone=" + phone + ", birthday=" + birthday + ", school=" + school
		 * 				+ ", originClass=" + originClass + ", active=" + active + ", createTime=" + createTime + ", updateTime="
		 * 				+ updateTime + "]";
		 * 	}
		 *
		 */

		Method method = new Method("toString");
		method.addAnnotation("@Override");
		method.setReturnType(new FullyQualifiedJavaType("String"));
		method.setVisibility(JavaVisibility.PUBLIC);
		/**
		 * searchBy example 条件添加
		 */

		/**
		 *
		 *@Override
		 *     public String toString() {
		 *         return "SWechatUser{" +
		 *                 "id=" + id +
		 *                 ", unionId='" + unionId + '\'' +
		 *                 ", openId='" + openId + '\'' +
		 *                 ", type=" + type +
		 *                 ", createTime=" + createTime +
		 *                 ", updateTime=" + updateTime +
		 *                 '}';
		 *     }
		 *
		 *
		 *
		 */

		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();

		StringBuilder builder=new StringBuilder("\""+topLevelClass.getType().getShortName()+"{\"+");
		for (IntrospectedColumn column : columns) {

			/**
			 *
			 *      return  "SWechatUser{"+"id="+id+
			 * 				"unionId="unionId+
			 * 				"openId="openId+
			 * 				"type="type+
			 * 				"createTime="createTime+
			 * 				"updateTime="updateTime+
			 * "}";
			 *
			 */
			builder.append("\n");
			String javapropername = column.getJavaProperty();
			for(int i=0;i<4;i++){
				builder.append("\t");
			}
			String getPname = javapropername;


			if(getPname.length()>1) {
				if(!Character.isUpperCase(getPname.charAt(1))) {
					getPname = Character.toUpperCase(getPname.charAt(0)) + getPname.substring(1);
				}
			}
			
			builder.append('"'+javapropername+'='+"\"+this.get"+getPname+"()"+'+');



		}
		builder.append("\"}\"");
		builder.append(';');
		method.addBodyLine(" return  "+builder.toString());
		topLevelClass.addMethod(method);


		return super.modelBaseRecordClassGenerated(topLevelClass,  introspectedTable);

	}



	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

}
