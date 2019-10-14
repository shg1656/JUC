package com.atguigu.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

	public static void main(String[] main) {
		Semaphore semaphore = new Semaphore(3);//模拟三个停车位
		
		
		for(int i=1;i<=6;i++) {
			new Thread(()->{
				boolean flag=false;
				try {
					//抢夺 等着抢，等死为止
					semaphore.acquire();
					//规定时间内抢不到就撤退
					//semaphore.tryAcquire(10, TimeUnit.SECONDS);
					flag=true;
					System.out.println(Thread.currentThread().getName()+"\t 抢占到停车位");
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					System.out.println(Thread.currentThread().getName()+"\t ---离开停车位");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//增加一个停车位
					if(flag) {
					semaphore.release();
					}
				}
				
			}).start();
		}
	}
}
