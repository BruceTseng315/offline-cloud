package com.turingmaker.controll.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的 session策略
 * 
 * @author JasonC
 * @date 2017-8-22-0022
 */
public class MyHttpSessionStrategy implements HttpSessionStrategy {
	private final Logger logger = LoggerFactory.getLogger(MyHttpSessionStrategy.class);

	private int maxAgeOfCookieInSeconds = 60 * 60 * 24 * 30 * 12; // 1年

	
	/**
	 * 设置cookiename
	 */
	private final String cookieName = "offline-turing-token-student";
	
	/**
	 * 设置域名
	 */
	private final String domain = "turingmaker.com";

	@Override
	public String getRequestedSessionId(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		String sessionId = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					sessionId = cookie.getValue();
					break;
				}
			}
		}
		if(StringUtils.isEmpty(sessionId))
			return null;
		return sessionId;
	}

	
	@Override
	public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {

		String sessionid =session.getId();
		Cookie cookie = new Cookie(cookieName, sessionid);
		//cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeOfCookieInSeconds);
		response.addCookie(cookie);
		if(logger.isDebugEnabled()) {
			logger.debug("新的会话设置 会话id" + sessionid + "url:" + request.getRequestURI());
		}
	}

	@Override
	public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {

		Cookie cookie = new Cookie(cookieName, null);
		//cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}


}
