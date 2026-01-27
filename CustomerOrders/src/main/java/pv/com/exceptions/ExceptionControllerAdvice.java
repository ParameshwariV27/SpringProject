package pv.com.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception)
	{
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(environment.getProperty("General.EXEPTION_MESSAGE"));
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	@ExceptionHandler(CustomerOrderExceptions.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(CustomerOrderExceptions exception)
	{
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(environment.getProperty("Service.CUSTOMER_NOT_FOUND"));
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception)
	{
		ErrorInfo er = new ErrorInfo();
		er.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String msg = exception.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
		er.setErrorMessage(msg);
		er.setTimestamp(LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(ConstraintViolationException exception)
	{
		ErrorInfo er = new ErrorInfo();
		String msg = exception.getConstraintViolations().stream().map(x->x.getMessage()).collect(Collectors.joining(","));
		er.setErrorMessage(msg);
		er.setErrorCode(HttpStatus.BAD_REQUEST.value());
		er.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(er);
		
	}
}
