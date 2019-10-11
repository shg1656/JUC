package com.atguigu.thread;


/**
 * 2  Lambda Express(前提是函数式接口，只有一个方法的情况下 )
 * 2.1 拷贝小括号，写死右箭头，落地大括号
 * 2.2 注解@FunctionalInterface
 * 2.3 default方法
 * 2.4 static
 * 
 * @author JAVA
 *
 */
@FunctionalInterface
interface Foo{
	public int add(int x,int y);
	
	//接口里可以写方法的实现
	default int div(int x,int y) {
		return x/y;
	}
	static int mul(int x,int y) {
		return x*y;
	}
	//public void sayHello();
}
public class LambdaExpressDemo {

	public static void main(String[] args) {
		
//		Foo foo = new Foo() {
//
//			@Override
//			public void sayHello() {
//				System.out.println("+++++++++++say hello");
//			}
//			
////			@Override
////			public int add(int x, int y) {
////				// TODO Auto-generated method stub
////				return 0;
////			}
//		};
		/**
		 * 接口是可以new的  以往的方式是匿名内部类
		 * 且接口里也是可以写方法实现的
		 */
	//	foo.sayHello();
		
		Foo foo=(int x,int y) ->{
			System.out.println("********come in add method");
			return x+y;
		};
		System.out.println(foo.add(5, 2));
		System.out.println(foo.div(10, 5));
		System.out.println(Foo.mul(12,23));
		
	}
	
}
