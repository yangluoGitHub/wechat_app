package com.weili.wechat.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PermissionCheckAroundAdvice implements MethodInterceptor { 

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
//		System.out.println("PermissionCheckAroundAdvice 拦截到啦!");
//		System.out.println("(被调用方法接口类名: " 	+ invocation.getMethod().getDeclaringClass().getName() + ")"); 
//		System.out.println("(被调用方法名：" + invocation.getMethod().getName()+ ")"); 
		String methodName = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName(); 
//		System.out.println("(被调用方法全名：" + methodName + ")");
		
		Object o[] = invocation.getArguments();
		for(int i=0;i<o.length;i++){
//			System.out.println("测试－－－－－－－－－－－－"+o[i]);	
		}  
//		System.out.println("PermissionCheckAroundAdvice 拦截到啦!-----就是不执行");
		return null;

	} 
	} 
