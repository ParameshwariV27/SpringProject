package pv.com.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pv.com.exceptions.CustomerOrderExceptions;

@Component
@Aspect
public class LoggingAspect {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* pv.com.service.*Impl.*(..))", throwing = "exception")
	private void afterThrowing(CustomerOrderExceptions exception)
	{
		logger.error(exception.getMessage(),exception);
	}

}
