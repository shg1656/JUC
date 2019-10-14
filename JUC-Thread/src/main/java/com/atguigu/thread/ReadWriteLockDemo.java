package com.atguigu.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源可以同时进行
 *但是
 *如果有一个线程相对资源进行写操作，就不应该让别的线程可以对他进行读写 
 *
 *总结
 *		读-读可以共存
 *		读-写不能共存
 *		写-写更不能共存
 *
 *
 */
/**
 * 自己的理解：
 * 		所谓读写锁 ，无非是因为普通的lock虽然会让写操作保持完整性，但同时也会让读操作保持完整性，对读的锁就没有必要了
 * 因为 不能只让一个人读共享资源，而其他人要等他读完才能读，这无疑增加了系统的负担
 * 
 * 读写锁的作用就是 -写操作时只允许一个线程进行操作，其他线程等着
 * 			   -读操作时可以让读操作一个访问共享资源
 * 
 * 
 * 可能会有疑问？
 * 我不加读锁行不行-
 * 		个人理解-因为读-写不能共存 所以写的时候时不可以读的
 * 				写就是写，读就是读
 * 
 * 				不能你在写，我在读 或者
 * 				我在写，你在读   	
 * 试验后---
 * 如果人家正在写操作-人家的数据是被保护起来的  如果我依然去读，那么我读出来的值为null
 * 
 *  * @author JAVA
 *
 */
class MyCache{
	private volatile Map<String,String> map=new HashMap<>();
	
	//读写锁
	private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock(); 
	public void put(String key,String value) {
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t 写入结束");
		}finally {
			readWriteLock.writeLock().unlock();
		}
	}
	public void get(String key) {
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 读取开始");
			String result = map.get(key);
			System.out.println(Thread.currentThread().getName()+"\t 读取结束result:"+result);
		}finally {
			readWriteLock.readLock().unlock();
		}
	}
	
	
	
	
	
//	private Lock lock=new ReentrantLock();
//	public void put(String key,String value) {
//		lock.lock();
//		try {
//			System.out.println(Thread.currentThread().getName()+"\t 写入开始");
//			map.put(key, value);
//			System.out.println(Thread.currentThread().getName()+"\t 写入结束");
//		}finally {
//			lock.unlock();
//		}
//	}
//	public void get(String key) {
//		lock.lock();
//		try {
//			String result = null;
//			System.out.println(Thread.currentThread().getName()+"\t 读取开始");
//			result=map.get(key);
//			System.out.println(Thread.currentThread().getName()+"\t 读取结束result"+result);
//		}finally {
//			lock.unlock();
//		}
//	}

}



public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyCache myCache = new MyCache();
//		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);// 一池子五线程
//		for (int i = 1; i <= 40; i++) {
//			newFixedThreadPool.submit(() -> {
//				ticket.sale();
//			});
//		}
//		newFixedThreadPool.shutdown();
//		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
//		for(int i = 1; i <= 10; i++) {
//			int finalI = i;
//			newFixedThreadPool.submit(()->{
//				myCache.put(finalI+"", finalI+"");
//			},String.valueOf(i));
//		}
//		for(int i = 1; i <= 10; i++) {
//			int finalI = i;
//			newFixedThreadPool.submit(()->{
//				myCache.get(finalI+"", finalI+"");
//			},String.valueOf(i));
//		}
		 for (int i = 1; i <=10; i++) {
	            int finalI = i;
	            new Thread(() -> {
	                myCache.put(finalI+"",finalI+"");
	            },String.valueOf(i)).start();
	        }

	        for (int i = 1; i <=10; i++) {
	            int finalI = i;
	            new Thread(() -> {
	                myCache.get(finalI+"");
	            },String.valueOf(i)).start();
	        }
	}
	
}
