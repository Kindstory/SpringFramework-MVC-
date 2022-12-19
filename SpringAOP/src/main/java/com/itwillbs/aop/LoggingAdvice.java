package com.itwillbs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(" 로깅 - 보조기능 시작 ");
		System.out.println("[ "+invocation.getMethod()+"메서드 호출 전 ] - LoggingAdvice_invoke() 호출");
		
		Object obj =invocation.proceed(); // 기존의 주기능 메서드 실행
		
		System.out.println("[ "+invocation.getMethod()+" 메서드 호출 후 ] - LoggingAdvice_invoke() 호출");
		System.out.println(" 로깅 - 보조기능 끝 ");
		
		return obj;
	}
	

}
