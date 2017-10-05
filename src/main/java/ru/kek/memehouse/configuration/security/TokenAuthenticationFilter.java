package ru.kek.memehouse.configuration.security;

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

	private AuthenticationManager authenticationManager;

	TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher("/api/**"));
		this.authenticationManager = authenticationManager;
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
			return authenticationManager.authenticate(new AuthenticationToken(tokenValue));
		}
	}

	private static boolean isNullOrEmpty(String value) {
		return value == null || value.equals("");
	}
}
