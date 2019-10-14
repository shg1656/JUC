package com.atguigu.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {

	public static void main(String[] args) {
		//线程池都是自定义的
/*		 public ThreadPoolExecutor(int corePoolSize,
                 int maximumPoolSize,
                 long keepAliveTime,
                 TimeUnit unit,
                 BlockingQueue<Runnable> workQueue) {
						this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
						Executors.defaultThreadFactory(), defaultHandler);
		} */
		 
		/*	1corePoolSize：线程池中的常驻核心线程数
		 * 2 maximumPoolSize：线程池中能容纳同时执行的线程的最大数，辞职必须大于等于1
		 * 3 keepAliveTime：多余的空闲线程的空闲时间如果达到keepAliveTime时，将销毁到只剩corePoolSize个线程为止 
		 * 4 unit：keepAliveTime的单位
		 * 5 workQueue:任务队列，阻塞队列，被提交但是没被执行的任务，排队中的1
		 * 6 threadFactory：表示生成线程池中工作线程的线程工厂，用来创建线程，一般默认
		 * 7 handler：拒绝策略，当队列满了。并且工作线程等于做大线程数时，如何来拒绝runnable的策略
		 * 
		 * 
		 * 步骤1
		 * 		1常驻核心线程数
		 * 
		 * 		2进入阻塞队列
		 * 
		 * 		3进行扩容	corePoolSize->maximumPoolSize
		 * 
		 * 			(请求飙升)
		 * 		4-如果超过了线程链接的最大数，直接采用拒绝策略
		 * 		or	(请求下降)
		 * 		-如果达到了keepAliveTime，会销毁除核心线程数外的所以线程
		 * 
		 */
		 
		 
		//手写连接池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
				5,
				30,
				TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(5),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.DiscardPolicy());
		 
		 try {
			for(int i=1;i<=10;i++) {
				int f=i;
				threadPoolExecutor.submit(()->{
					System.out.println(Thread.currentThread().getName()+"\t 办理业务"+f+"客户的"+new Random().nextInt(10));
				});
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			threadPoolExecutor.shutdown();
		}
		 
		 
		 
//		 //连接池
//		 ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);//一池五线程
//		 ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();//一池一线程
//		 ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();//一池N线程 
//		 //模拟20个用户来银行办理业务，提交请求。customer
//		 try {
//			for(int i=1;i<=20;i++) {
//				int f=i;
//				 newFixedThreadPool.submit(()->{
//					 System.out.println(Thread.currentThread().getName()+"\t 办理业务"+f+"客户的"+new Random().nextInt(10));
//				 });
//			 }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			//记得要关闭线程池
//			newFixedThreadPool.shutdown();
//		}
	 
	 }
}
