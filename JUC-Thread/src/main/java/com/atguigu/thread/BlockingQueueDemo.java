package com.atguigu.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue blockingQueuenew =new ArrayBlockingQueue(3);
//		System.out.println(blockingQueuenew.add("a"));
//		System.out.println(blockingQueuenew.add("b"));
//		System.out.println(blockingQueuenew.add("c"));
//		
//		System.out.println(blockingQueuenew.element());
//		
	//	System.out.println(blockingQueuenew.add("x"));
//		System.out.println(blockingQueuenew.remove());
//		System.out.println(blockingQueuenew.remove());
//		System.out.println(blockingQueuenew.remove());
		//java.util.NoSuchElementException
		//System.out.println(blockingQueuenew.remove());
		
//		
//		System.out.println(blockingQueuenew.offer("a"));
//		System.out.println(blockingQueuenew.offer("b"));
//		System.out.println(blockingQueuenew.offer("c"));
		
//		System.out.println(blockingQueuenew.poll());
//		System.out.println(blockingQueuenew.poll());
//		System.out.println(blockingQueuenew.poll());
		
		
		System.out.println(blockingQueuenew.offer("a",10000,TimeUnit.SECONDS));
		System.out.println(blockingQueuenew.offer("b"));
		System.out.println(blockingQueuenew.offer("c"));
		
		System.out.println(blockingQueuenew.poll(1000,TimeUnit.SECONDS));
//		System.out.println(blockingQueuenew.poll());
//		System.out.println(blockingQueuenew.poll());
		
		//不会报错   结果是null
		//System.out.println(blockingQueuenew.poll());
		//System.out.println(blockingQueuenew.peek());
		
//		blockingQueuenew.put("a");
//		blockingQueuenew.put("b");
//		blockingQueuenew.put("c");
//		
//		System.out.println(blockingQueuenew.take());
//		System.out.println(blockingQueuenew.take());
//		System.out.println(blockingQueuenew.take());
	
	
	}
}
