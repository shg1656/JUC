package com.atguigu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//runnable和Callable三个区别
/**
 * 重写的方法名不一样
 * Callable有返回值
 * Callable抛异常
 * @author JAVA
 *
 */
/**
 * Callable作用
 * 主要是--异步思想
 * @author JAVA
 *
 */

//底层是函数是接口
class MyThread2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"------- come in Call");
		return "java0615";
	}
	
}
public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String > futureTasknew = new FutureTask(()->{System.out.println(Thread.currentThread().getName()+"------- come in Call");
		return "java0615";});
		new Thread(futureTasknew,"A").start();
		
		
//		String result=futureTasknew.get();
	//	System.out.println(result);
		
		
		System.out.println(Thread.currentThread().getName()+"+++++  main");
		
		//get 方法一定要放在最后
		/**
		 * Callable自己的理解
		 * 		主要是异步思想
		 * 为什么get要放在最后
		 * 因为如果你放在前面，我去get你了，但是你还没有算完，那么就又进入到一个wait状态
		 * 我会做的题，我的主线程，也要被你卡主
		 * 所以get要放在最后，先把会做的题，主线程做了，再去get
		 * 最后结果合并----这就是Callable的异步思想,面试答这一点
		 */
		String result=futureTasknew.get();
		System.out.println(result);
	}
}
