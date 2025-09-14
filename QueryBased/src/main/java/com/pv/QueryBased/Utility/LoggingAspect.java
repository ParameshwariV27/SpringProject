package com.pv.QueryBased.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private final Logger LOGGER = LogManager.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* com.pv.QueryBased.Service.*Impl.*(..))", throwing = "execution")
	public void afterThrowing(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
	}

}
