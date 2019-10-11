package com.atguigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
 
    public static void main(String[] args) {
    	Ticket ticket = new Ticket();
    	
    	new Thread(() -> {for(int i=1;i<=40;i++) ticket.sale();},"A").start();
    	new Thread(() -> {for(int i=1;i<=40;i++) ticket.sale();},"B").start();
    	new Thread(() -> {for(int i=1;i<=40;i++) ticket.sale();},"C").start();
       
    }
}
 //高内聚，低耦合
//高内低耦的前提下  				线程		  操作  		 资源类
class Ticket {//资源类

	private  Integer tickNum=30;
	
	Lock lock=new ReentrantLock();
	
	public void sale() {
		 lock.lock();
		 try{
			 if(tickNum>0) {
				 System.out.println(Thread.currentThread().getName()+"\t卖出第"+(tickNum--)+"还剩"+tickNum+"张票");
			 }
		    }catch (Exception e){
		        e.printStackTrace();
		    }finally {
		      lock.unlock();
		    }
	}
}