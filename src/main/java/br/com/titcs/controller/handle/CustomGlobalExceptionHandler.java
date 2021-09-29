package br.com.titcs.controller.handle;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.titcs.exceptions.UnauthorizedException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		body.put("error", status.name());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		body.put("message", errors);
		
		String path = ((ServletWebRequest)request).getDescription(true).replace("uri=", "").split(";")[0];
		body.put("path", path);

		return new ResponseEntity<>(body, headers, status);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<Object> runtimeExceptions(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST);
		body.put("message", ex.getMessage());
		
		String path = ((ServletWebRequest)request).getDescription(true).replace("uri=", "").split(";")[0];
		body.put("path", path);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseBody
	public ResponseEntity<Object> runtimeExceptions(EmptyResultDataAccessException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST);
		body.put("message", "Objeto não encontrado no banco de dados");
		
		String path = ((ServletWebRequest)request).getDescription(true).replace("uri=", "").split(";")[0];
		body.put("path", path);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UnexpectedRollbackException.class)
	public ResponseEntity<Object> runtimeExceptions(UnexpectedRollbackException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST);
		body.put("message", "Itens associados a este impedem a confirmação da execução.");
		
		String path = ((ServletWebRequest)request).getDescription(true).replace("uri=", "").split(";")[0];
		body.put("path", path);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public ResponseEntity<Object> unauthorizedException(UnauthorizedException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.UNAUTHORIZED.value());
		body.put("error", HttpStatus.UNAUTHORIZED);
		body.put("message", "Usuário não possui autorização para prosseguir com a solicitação.");
		
		String path = ((ServletWebRequest)request).getDescription(true).replace("uri=", "").split(";")[0];
		body.put("path", path);

		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
}
