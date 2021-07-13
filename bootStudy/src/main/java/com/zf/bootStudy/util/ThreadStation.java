package com.zf.bootStudy.util;
import java.util.ArrayList;
import java.util.List;
public class ThreadStation {
	
	//定义售票线程类（也就是窗口）
	public static class Station extends Thread{
		//构造方法给线程名字赋值
		public Station(String name) {
			super(name); 
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
						System.out.printf("%s卖出了第%d张票 \n",getName(),tick);
						list.add(tick);
					    tick--;
					}else {
						System.out.printf("%s：票已售空 \n",getName());
						
					}
				}
				try {
					sleep((int)(Math.random()*3000)+1); //随机休息1-3000ms
				}catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
			System.out.printf("%s 销售情况： %s \n",getName(),list.toString());
		}
	}
 
	public static void main(String[] args) {
        //实例化站台对象，并为每一个站台取名字(8个线程窗口一起卖5张票）
		for (int i=1; i<=8; i++) {
			String sName="窗口" + String.valueOf(i);
  		    Station  Station = new Station(sName);
		    Station.start();
		}
 
	}
 


}
