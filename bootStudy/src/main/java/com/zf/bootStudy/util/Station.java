package com.zf.bootStudy.util;
import java.util.ArrayList;
import java.util.List;

//定义售票线程类（也就是窗口）
	public  class Station implements Runnable{
		//构造方法给线程名字赋值
		public Station() {
			super(); 
		}
		//票数要静态定义
		static int tick=5;
		//静态钥匙
		static Object ob ="key"; //值是任意的
		//重写run方法，实现售票操作
		@Override
		public void run() {
			List<Integer>  list = new ArrayList<>();
			while (tick>0) {
				synchronized(ob) { //必须使用一个同步锁，进去的人会把钥匙拿在手上，出来后才能交出钥匙
					if (tick>0) {
						System.out.printf("卖出了第%d张票 \n",tick);
						list.add(tick);
					    tick--;
					}else {
						System.out.printf("：票已售空 \n");
						
					}
				}
			}
			System.out.printf("销售情况： %s \n",list.toString());
		}
	}