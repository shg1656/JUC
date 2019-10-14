package com.atguigu.thread;

import java.util.concurrent.TimeUnit;

class Phone{	//phone.java -> phone.class -> load...JVM里面形成模板Class<phone> static synchronized锁的是模板 是大Class
	//高内聚
	/**
	 * synchronized 锁的是整个资源类，当前对象this
	 * @throws Exception
	 */
	public static synchronized void sendEmail() throws Exception{
		TimeUnit.SECONDS.sleep(4);
		System.out.println("----sendEmail");
	}
		
	public  synchronized void sendSMS() throws Exception{
		System.out.println("----sendSMS");
	}
	public void sayHello() {
		System.out.println("----sendHello");
	}
}
/**
 * 题目：多线程8锁
 * 1	标准访问 ，请问先打印邮件还是短信
 * 2    邮件方法新增暂停4秒的方法，请问先打印邮件还是短信
 * 3    新增的hello方法。请问先打印邮件还是hello
 * 4    有两部手机，请问先打印邮件还是短信
 * 5	两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6	两个静态同步方法，两部手机，请问先打印邮件还是短信
 * 7	1个静态同步方法,1个普通同步方法，同一部手机，请问先打印邮件还是短信
 * 8	1个静态同步方法,1个普通同步方法，两部手机，请问先打印邮件还是短信
 * 
 * 笔记
 * 	1,2锁-
 * 		一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中一个synchronized方法了，
 * 		其他的线程只能等待，换句话说，某一时刻内，只能有一个线程取访问这鞋synchronized方法
 * 		锁的是当前对象this，锁的是整个资源类，被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
 *
 *	3锁-
 *		加个普通方法后发现和同步锁无关
 *
 *	4锁-
 *		换成两个对象之后 ，不是同一把锁了，情况立刻变化

 *5,6锁-
 *		都换成静态同步方法后，情况又变化
 *	若是普通同步方法，new this，具体的一部部手机，所有的普通同步方法用的都是同一把锁，锁的是实例对象		
 *	若是静态同步方法，static Class，唯一的一个模板
 *	
 *	synchronized是实现同步的基础：java中每一个对象都可以作为锁
 *	具体表现为以下三种方式
 *	对于普通同步方法，锁是当前实例对象 等同于  对于同步方法块，锁是synchronized括号里配置的对象
 *	对于静态同步方法，锁是当前类的Class对象本身
 *
 *7,8锁-
 *		普通同步方法用的锁都是同一把锁-实例对象本身   new Phone();
 *		一个实例对象的普通同步方法得到锁后，该实例对象的其他同步方法必须等到获取锁的方法释放锁之后才能获取锁
 *		可是别的实例对象的普通同步方法和该实例对象的普通同步方法用的不是同一个锁，所以不用等待该实例对象以获取锁的普通同步方法释放锁就可以获取他们自己的锁
 *
 *
 *		所有的静态同步方法用的也是同一把锁--类对象本身，就是我们说的唯一模板类  Class<phone>
 *		具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞争关系的
 *		但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等到该静态同步方法释放锁之后才能获取锁
 */
public class Lock8 {

	public static void main(String[] args) throws Exception {
		//线程操作资源类
		Phone phone = new Phone();//this1
		Phone phone2 = new Phone();//this2
		new Thread(() ->{
			try {
				phone.sendEmail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "A").start();
		
		Thread.sleep(100);
		
		new Thread(() ->{
			try {
				phone2.sendSMS();
				//  phone.sayHello();
				//phone2.sayHello();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "B").start();
		
	}
}
