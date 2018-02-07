package ru.kek.memehouse.security;

import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * gordeevnm@gmail.com
 * 29.12.17
 */
public class HeaderOrCookieSessionStrategy implements HttpSessionStrategy {
	private static final String HEADER_NAME = "X-Auth-Token";
	private static final String COOKIE_NAME = "SESSION";
	
	private final HeaderHttpSessionStrategy headerStrategy;
	private final CookieHttpSessionStrategy cookieStrategy;
	
	public HeaderOrCookieSessionStrategy() {
		
		this.headerStrategy = new HeaderHttpSessionStrategy();
		this.cookieStrategy = new CookieHttpSessionStrategy();
		
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setCookieName(COOKIE_NAME);
		
		headerStrategy.setHeaderName(HEADER_NAME);
		cookieStrategy.setCookieSerializer(cookieSerializer);
	}
	
	@Override
	public String getRequestedSessionId(HttpServletRequest request) {
		String sessionId = cookieStrategy.getRequestedSessionId(request);
		
		if (isNullOrEmpty(sessionId))
			sessionId = headerStrategy.getRequestedSessionId(request);
		
		return sessionId;
	}
	
	@Override
	public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
		headerStrategy.onNewSession(session, request, response);
		cookieStrategy.onNewSession(session, request, response);
	}
	
	@Override
	public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
		headerStrategy.onInvalidateSession(request, response);
		cookieStrategy.onInvalidateSession(request, response);
	}
}
