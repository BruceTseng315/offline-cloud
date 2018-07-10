package com.turingmaker.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  tzj
 */
public class BeanUtil {

	static Map<Class<?>, List<FieldAndReadMethod>> classMapReadMethods = new HashMap<>();

	static Map<Class<?>, List<FieldAndWriteMethod>> classMapWriteMethods = new HashMap<>();

	static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

	
	
	
	/**
	 * 获取所有的get 方法
	 * @param class1
	 * @return
	 */
	public static List<FieldAndReadMethod> getForAllReadMethod(Class<?> class1) {

		Field[] fields = class1.getDeclaredFields();
		List<FieldAndReadMethod> readMethods =classMapReadMethods.get(class1);
		
		if(readMethods!=null&&!readMethods.isEmpty())
			return readMethods;
		
		readMethods=new ArrayList<>();
		
		for (int i = 0; i < fields.length; i++) {

			int modify = fields[i].getModifiers();
			if (Modifier.isStatic(modify) || Modifier.isVolatile(modify) || Modifier.isTransient(modify))
				continue;

			String proname = fields[i].getName();

			StringBuilder methodName = new StringBuilder();

			if (fields[i].getType() == boolean.class)
				methodName.append("is");
			else {
				methodName.append("get");
			}

			methodName.append(Character.toUpperCase(proname.charAt(0)));
			methodName.append(proname.substring(1));
			try {
				readMethods.add(new FieldAndReadMethod(proname, class1.getDeclaredMethod(methodName.toString())));
			} catch (Exception e) {
				logger.warn("获取get方法异常" + proname,e);
			}

		}
		
		String packageName=class1.getSuperclass().getPackage().getName();
		if(!(packageName.startsWith("java")||packageName.startsWith("sun"))){
			readMethods.addAll(getForAllReadMethod(class1.getSuperclass()));
		}

		classMapReadMethods.put(class1, readMethods);
		return readMethods;
	}

	/**
	 * 获取所有的set 方法
	 * @param class1
	 * @return
	 */
	public static List<FieldAndWriteMethod> getForAllWriteMethod(Class<?> class1) {

		List<FieldAndWriteMethod> writeMethods = classMapWriteMethods.get(class1);
		if(writeMethods!=null&&!writeMethods.isEmpty()) {
			return writeMethods;
		}
		
		writeMethods=new ArrayList<>();
		Field[] fields = class1.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {

			int modify = fields[i].getModifiers();
			if (Modifier.isStatic(modify) || Modifier.isVolatile(modify) || Modifier.isTransient(modify))
				continue;

			String proname = fields[i].getName();
			StringBuilder methodName = new StringBuilder();
			methodName.append("set");
			methodName.append(Character.toUpperCase(proname.charAt(0)));
			methodName.append(proname.substring(1));
			try {
				writeMethods.add(new FieldAndWriteMethod(
						fields[i].getType(),
						proname, 
						class1.getDeclaredMethod(methodName.toString(),fields[i].getType()
								)));
			} catch (Exception e) {
				logger.warn("获取set方法异常" + proname,e);
			}

		}
		classMapWriteMethods.put(class1, writeMethods);
		return writeMethods;

	}

	public static Object copyPropertiesForNotNull(Object src, Object dest) {

		
		
		if (src == null || dest == null)
			throw new NullPointerException("dest 和 src 都不能为空");

		List<FieldAndReadMethod> andReadMethods = getForAllReadMethod(src.getClass());

		List<FieldAndWriteMethod> andWriteMethods = getForAllWriteMethod(dest.getClass());

		if (andReadMethods == null || andReadMethods.isEmpty() || andWriteMethods == null || andWriteMethods.isEmpty())
			return dest;

		final Map<String, Object> fieldvalues = new HashMap<>();
		andReadMethods.forEach((r) -> {
			
			Object val=null;

			try {
				val=r.readMethod.invoke(src);
			} catch (ReflectiveOperationException e) {
				logger.warn("调用get方法异常" + r.propertyname,e);
			}
			
			//空值不处理
			if(val!=null) {
				if(val instanceof Collection) {
					Collection<?> collection=(Collection<?>)val;
					if(!collection.isEmpty()) {
						fieldvalues.put(r.propertyname, val);
					}
				}else {
					fieldvalues.put(r.propertyname, val);
				}
			}

		});

		andWriteMethods.forEach((w) -> {
			if (fieldvalues.containsKey(w.propertyname)) {
				
				Object val=fieldvalues.get(w.propertyname);
				
				try {
					w.writeMethod.invoke(dest, val);
				} catch (ReflectiveOperationException e) {
					logger.warn("调用set方法异常" + w.propertyname,e);
				}
			}

		});

		return dest;
	}
	


	static class FieldAndReadMethod {

		String propertyname;

		Method readMethod;

		public FieldAndReadMethod(String propertyname, Method readMethod) {
			super();
			this.propertyname = propertyname;
			this.readMethod = readMethod;
		}

	}

	static class FieldAndWriteMethod {

		String propertyname;

		Method writeMethod;
		
		Class<?> paramType;

		public FieldAndWriteMethod(Class<?> paramType,String propertyname, Method writeMethod) {
			super();
			this.propertyname = propertyname;
			this.writeMethod = writeMethod;
			this.paramType=paramType;
		}

	}
	
	

}
