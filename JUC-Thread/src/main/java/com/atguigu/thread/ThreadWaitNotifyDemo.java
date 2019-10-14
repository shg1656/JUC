package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量+1，一个线程对该变量减一
 * 实现交替，来10轮，变量初始值为零
 * 
 * 	1		高内聚低耦合前提下     线程操作资源类
 * 	2		涉及到线程之间的通信或调度    --   判断/干活/通知
 * 	3 		小心，防止多线程的虚假唤醒，判断时候用while而不是if
 *			虚假唤醒就是obj.wait();被除obj.notify()或obj.notifyAll()之外的其他情况唤醒，而此时是不应被唤醒的
 * 
 * @author JAVA
 *
 */


//资源类
class AirConditioner{
	private int number=0;
	private Lock lock=new ReentrantLock();
	private Condition codition=lock.newCondition();
	
	/**
	 * wait()和notifyAll(),notify()   是跟synchronized混的
	 * await(),signalAll(),signal()   是跟lock.newCondition();混的
	 * @throws Exception
	 */
	public void increment()throws Exception{
		lock.lock();
		
		try {
			//判断
			while(number!=0) {//如果有值  本方法等待
				codition.await();//this.wait();
			}
			number++; 
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//通知
			//notifyAll();通知其他线程退出wait();的状态
			codition.signalAll();//this.notifyAll();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public synchronized void decrement()throws Exception{
		lock.lock();
		
		try {
			//判断
			while(number==0) {//如果有值  本方法等待
				codition.await();//this.wait();
			}
			number--; 
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//通知
			//notifyAll();通知其他线程退出wait();的状态
			codition.signalAll();//this.notifyAll();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
	
	
	
	
//	public synchronized void increment()throws Exception{
//		//判断
//		while(number!=0) {//如果有值  本方法等待
//			this.wait();
//		}
//		//干活
//		number++;
//		System.out.println(Thread.currentThread().getName()+"\t"+number);
//		//通知
//		//notifyAll();通知其他线程退出wait();的状态
//		this.notifyAll();
//	}
//	public synchronized void decrement()throws Exception{
//		//判断
//		while(number==0) {//如果有值  本方法等待
//			this.wait();
//		}
//		//干活
//		number--;
//		System.out.println(Thread.currentThread().getName()+"\t"+number);
//		//通知
//		this.notifyAll();
//	}
	
	
	
	
	
}
public class ThreadWaitNotifyDemo {

	public static void main(String[] args)throws Exception{
		//一言不合先new资源类
		AirConditioner airConditioner = new AirConditioner();
		
		new Thread(()-> {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(100);
					airConditioner.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"A").start();
		new Thread(()-> {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(200);
					airConditioner.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
		new Thread(()-> {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(300);
					airConditioner.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		},"C").start();
		new Thread(()-> {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(400);
					airConditioner.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"D").start();
	}
}
