package com.weili.wechat.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PermissionCheckAroundAdvice implements MethodInterceptor { 

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
//		System.out.println("PermissionCheckAroundAdvice ���ص���!");
//		System.out.println("(�����÷����ӿ�����: " 	+ invocation.getMethod().getDeclaringClass().getName() + ")"); 
//		System.out.println("(�����÷�������" + invocation.getMethod().getName()+ ")"); 
		String methodName = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName(); 
//		System.out.println("(�����÷���ȫ����" + methodName + ")");
		
		Object o[] = invocation.getArguments();
		for(int i=0;i<o.length;i++){
//			System.out.println("���ԣ�����������������������"+o[i]);	
		}  
//		System.out.println("PermissionCheckAroundAdvice ���ص���!-----���ǲ�ִ��");
		return null;

	} 
	} 
