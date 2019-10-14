package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *多线程之间按顺序调用，实现A -> B -> C

 *三个线程启动，要求如下
 *
 *
 *AA打印5次，BB打印10次，CC打印15次
 *接着
 *AA打印5次，BB打印10次，CC打印15次
 *来10轮
 	1		高内聚低耦合前提下     线程操作资源类
 * 	2		涉及到线程之间的通信或调度    --   判断/干活/通知
 * 	3 		小心，防止多线程的虚假唤醒，判断时候用while而不是if
 *			虚假唤醒就是obj.wait();被除obj.notify()或obj.notifyAll()之外的其他情况唤醒，而此时是不应被唤醒的
 *
 *	4  		一定注意标志位的更新
 * @author JAVA
 *
 */
/**
 * 多线程的并发控制  加锁
 * 多线程之间的协调调度和通知
 * @author JAVA
 *
 */
class ShareResource{
		
		private int flag=1;//1 A,2 B,3 C
		private Lock lock=new ReentrantLock();//可重复锁
		
		private Condition c1=lock.newCondition();
		private Condition c2=lock.newCondition();
		private Condition c3=lock.newCondition();
		
		public void print5(){
			lock.lock();
			try {
				
				//判断
				while(flag!=1) {//不是A  A就要停止
					c1.await();
				}
				//干活
				for(int i=1;i<=5;i++) {
					System.out.println(Thread.currentThread().getName()+"\t"+i);
				}
				//通知
				flag=2;
				c2.signal();
				
			}catch(Exception e) {
				
			}finally{
				lock.unlock();
			}
		}
		
		public void print10(){
			lock.lock();
			try {
				
				//判断
				while(flag!=2) {//不是A  A就要停止
					c2.await();
				}
				//干活
				for(int i=1;i<=10;i++) {
					System.out.println(Thread.currentThread().getName()+"\t"+i);
				}
				//通知
				flag=3;
				c3.signal();
				
			}catch(Exception e) {
				
			}finally{
				lock.unlock();
			}
		}
		
		public void print15(){
			lock.lock();
			try {
				
				//判断
				while(flag!=3) {//不是A  A就要停止
					c3.await();
				}
				//干活
				for(int i=1;i<=15;i++) {
					System.out.println(Thread.currentThread().getName()+"\t"+i);
				}
				//通知
				flag=1;
				c1.signal();
				
			}catch(Exception e) {
				
			}finally{
				lock.unlock();
			}
		}
	
	
}
public class ThreadOrderAccess {

	public static void main(String[] args)throws Exception{
		ShareResource shareResource = new ShareResource();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				shareResource.print5();
			}
		},"A").start();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				shareResource.print10();
			}
		},"B").start();
		new Thread(()->{
			for(int i=1;i<=10;i++) {
				shareResource.print15();
			}
		},"C").start();
	
	
	}
}
