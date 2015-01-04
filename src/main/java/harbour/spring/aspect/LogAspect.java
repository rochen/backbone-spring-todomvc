package harbour.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {
	final static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Before ("execution(* harbour.spring.web.*.*(..)) ")
	public void log(JoinPoint joinPoint) {
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("{}.{} called...", typeName, methodName);
	}
}
