package lx.edu.levelup.feature.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lx.edu.levelup.feature.aop.domain.RequestLogVO;

@Aspect
@Component
@EnableAspectJAutoProxy
public class RequestLogAdvice {
	
	@Autowired
	RequestLogRepository dao;
	
	@Before("execution(* lx.edu.levelup.feature..*Controller.*(..))")
	public void beforeLog(JoinPoint joinPoint) throws Exception {
		System.out.println("LogAdvice.beforeLog()");
		// 어떤 클래스의 어떤 메서드가 실행되었는지 알 수 있음.
		String targetClass = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		System.out.printf("class=%s, method=%s/n", targetClass.getClass().getName(), methodName);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
		
		RequestLogVO log = new RequestLogVO();
	    log.setReqClass(targetClass);
	    log.setReqMethod(methodName);
	    log.setReqTime(str);
	    
//	    dao.insertLogDB(log);

	}
	
}
