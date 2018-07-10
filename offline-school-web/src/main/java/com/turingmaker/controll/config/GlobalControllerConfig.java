package com.turingmaker.controll.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HttpSessionStrategy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JasonC
 * @date 2018-1-15-0015
 */
@Configuration
@EnableRedisHttpSession
public class GlobalControllerConfig {
  @Bean
  public HttpSessionStrategy httpSessionStrategy(){
    return new MyHttpSessionStrategy();
  }

  /**
   * 使用json序列化方式
   * @return
   */
  @Bean
  @Qualifier("springSessionDefaultRedisSerializer")
  public RedisSerializer springSessionDefaultRedisSerializer(){

      Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
      ObjectMapper om = new ObjectMapper();
      om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      jackson2JsonRedisSerializer.setObjectMapper(om);

      return jackson2JsonRedisSerializer;
  }

  /**
   * 生成key的策略
   *
   * @return
   */
  @Bean
  public KeyGenerator keyGenerator() {
      return new KeyGenerator() {
          @Override
          public Object generate(Object target, Method method, Object... params) {
              StringBuilder sb = new StringBuilder();
              sb.append(target.getClass().getName());
              sb.append(method.getName());
              for (Object obj : params) {
                  sb.append(obj.toString());
              }
              return sb.toString();
          }
      };
  }

  /**
   * 管理缓存
   *
   * @param redisTemplate
   * @return
   */
  @SuppressWarnings("rawtypes")
  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
      RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
      Map<String,Long> map=new HashMap<String, Long>();
      rcm.setExpires(map);
      return rcm;
  }
  
  /**
   * 授权过滤器，访问接口的时候查看是否有足够权限
   * @return
   */
  @Bean
  public  FilterRegistrationBean filter() {
	  
	  FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new AuthFilter());//添加过滤器
      registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
      registration.setName("authfilter");//设置优先级
      return registration;
  }

}
