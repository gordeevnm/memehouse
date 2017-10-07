package ru.kek.memehouse.configuration.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kek.memehouse.configuration.security.exceptions.AuthException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher("/api/**"));
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (request.getRequestURI().matches("/api/auth/login.*"))
			return false;
		else
			return super.requiresAuthentication(request, response);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		String tokenValue = request.getHeader("X-Auth-Token");

		if (isNullOrEmpty(tokenValue)) {
			throw new AuthException("Authentication token is not found");
		} else {
			return getAuthenticationManager().authenticate(new AuthenticationToken(tokenValue));
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		response.getWriter().write("{message: \"" + failed.getMessage() + "\"}");
	}

	private static boolean isNullOrEmpty(String value) {
		return value == null || value.equals("");
	}
}
