package com.turingmaker.dao.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

/**
 * 
 * @author 自定义的sessionfactory。方便切换session打开模式
 *
 */
public class MyDefaultSqlSessionFactory extends DefaultSqlSessionFactory{

	
	ThreadLocal<Boolean> batchMode=new ThreadLocal<>();
	
	
	public MyDefaultSqlSessionFactory(Configuration configuration) {
		super(configuration);
		
	}

	@Override
	public SqlSession openSession(ExecutorType execType) {
		
//		if(batchMode.get()) {
//			return super.openSession(ExecutorType.BATCH);
//		}
		return super.openSession(execType);
	}

	

	
	
}
