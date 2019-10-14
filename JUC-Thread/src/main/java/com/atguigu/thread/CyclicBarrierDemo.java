package com.atguigu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * 没加满就一直await();
 * @author JAVA
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		//CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {System.out.println("集齐七颗龙珠");});
		CyclicBarrier cyclicBarrier =new CyclicBarrier(7,() ->{System.out.println("集齐七颗龙珠");});
		for (int i = 1; i <= 7; i++) {
			int finali = i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "\t 收集到第" + finali);
					//没加满就等着 
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
