package com.atguigu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotSafe {

	/**
	 * 本类测试ArrayList线程安全问题  
	 * 我们知道因为ArrayList是线程不安全的  所以面试官问我们可以这么回答
	 * ArrayList是线程不安全的 解决方式有两种 Vector CopyOnWriteArrayList
	 * 其中Vector技术很老 面试官可能不想听   我们可以说CopyOnWriteArrayList 这或许是是他想要的答案
	 * 
	 * 关于线程不安全常见错误 
	 * 	1   java.util.ConcurrentModificationException 这个要记住
	 * 
	 * 延伸一下 ArrayList的扩容问题
	 * ArrayList的扩容方式   
	 * 1 默认容量为10，扩容是原值的一半 所以第二次是15  第三次是7.5+15=22.5  省略小数点取整数  所以是22
	 */
	public static void main(String[] args) {
		  List<String> list = new CopyOnWriteArrayList();
		  //List<String> list = new ArrayList();
		  for(int i=1;i<=30;i++) {
			  new Thread(()->{
				  list.add(UUID.randomUUID().toString().substring(0, 6));
				  System.out.println(list);
			  },String.valueOf(i)).start(); 
		  }
	}
	
}
