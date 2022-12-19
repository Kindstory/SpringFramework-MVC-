package com.itwillbs.aop;

public class Calculator {
	// 계산기 객체 - 주기능( + - * / )
	
	public void add(int x, int y) {
		System.out.println(" result : "+(x+y));
	}
	public void sub(int x, int y) {
		System.out.println(" result : "+(x-y));
	}
	public void mul(int x, int y) {
		System.out.println(" result : "+(x*y));
	}
	public void div(int x, int y) {
		System.out.println(" result : "+(x/y));
	}
	
	

}
