package ru.kek.memehouse.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", WebSecurityConfig.SESSION_HEADER_NAME + ",Content-Type");
		chain.doFilter(req, res);
	}
	
	public void init(FilterConfig filterConfig) {
	}
	
	public void destroy() {
	}
	
}