package internaltest.springsecuritylogin.auth.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private boolean postOnly = true;
	private HashMap<String, String> jsonRequest;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("POST 요청으로만 인증해주시기 바랍니다");
		}

		/*if (request.getHeader("Content-Type").contains("application/json")) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonRequest = (HashMap<String, String>) mapper.readValue(
						request.getReader().lines().collect(Collectors.joining()),
						new TypeReference<Map<String, String>>() {
						});
			} catch (IOException e) {
				e.printStackTrace();
				throw new AuthenticationServiceException("JSON 요청 parsing에 실패하였습니다");
			}
		}*/

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		setDetails(request, authRequest);

		return super.attemptAuthentication(request, response);
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String passwordParameter = super.getPasswordParameter();
		if (request.getHeader("Content-Type").contains("application/json")) {
			return jsonRequest.get(passwordParameter);
		}
		return request.getParameter(passwordParameter);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String usernameParameter = super.getUsernameParameter();
		if (request.getHeader("Content-Type").contains("application/json")) {
			return jsonRequest.get(usernameParameter);
		}
		return request.getParameter(usernameParameter);
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

}
