package com.atguigu.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 没减满就一直等
 * @author JAVA
 *
 */
public class CountDownLatchDemo {

	public static void main (String[] args)throws Exception{
		//ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);// 一池子五线程
		
		CountDownLatch countDownLatch=new CountDownLatch(6);
		
		for(int i=1;i<=6;i++) {
			new Thread(()->{
				 
				System.out.println(Thread.currentThread().getName()+"\t 离开教室");
				countDownLatch.countDown();
			},String.valueOf(i)).start();
		}
		//countDownLatch次数减为零，取消await()状态
		countDownLatch.await(2l, TimeUnit.SECONDS);
		System.out.println(Thread.currentThread().getName()+"\t 关门离开");
	}
}
