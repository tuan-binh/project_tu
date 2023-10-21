package backend.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
		logger.error("Un Permission", accessDeniedException.getMessage());
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Un Permission", HttpStatus.FORBIDDEN);
		response.setStatus(responseEntity.getStatusCodeValue());
		response.getWriter().write(Objects.requireNonNull(responseEntity.getBody()));
	}
}
