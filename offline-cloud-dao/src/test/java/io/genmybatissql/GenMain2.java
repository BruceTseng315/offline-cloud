package io.genmybatissql;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class GenMain2 {

	
	
	//@Test
	public void before() throws Exception {
		
	
		
		BufferedReader bufferedReader=
		 new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("table.teachter"),Charset.forName("utf-8")));
		
		Map<String, Object> map=new HashMap<>();
		List<String> strings=new ArrayList<>();
		String line=null;
		while((line=bufferedReader.readLine())!=null) {
			strings.add(line);
		}
		
		map.put("datas", strings);
		map.put("entityproject", "E:\\Users\\Administrator\\git\\offline-cloud-parent\\offline-cloud-entity");
		map.put("mapperproject", "E:\\Users\\Administrator\\git\\offline-cloud-parent\\offline-cloud-dao");
		
		Configuration configuration=new Configuration(Configuration.VERSION_2_3_0);
		Template template=new Template("a", 
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("mbgConfiguration.ftl"), Charset.forName("utf-8"))
				,configuration
				);
		
		template.process(map, new OutputStreamWriter(System.out));
		
	}
	
	/**
	 * 生成学校端代码
	 */
    @Test
    public  void testForGenCodeSchool(){
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "configschool.xml";
        File configFile = new File(GenMain2.class.getClassLoader().getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 生成运营端代码
     */
   // @Test
    public  void testForGenCodeconfigoperation(){
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "configoperation.xml";
        File configFile = new File(GenMain2.class.getClassLoader().getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
 // @Test
    public  void testForTmp(){
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "tmp.xml";
        File configFile = new File(GenMain2.class.getClassLoader().getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}