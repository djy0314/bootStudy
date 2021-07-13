package com.zf.bootStudy.util;
public class ThreadStation2 {
	
	public static void main(String[] args) {
        //实例化站台对象，并为每一个站台取名字(8个线程窗口一起卖5张票）
		for (int i=1; i<=8; i++) {
			System.out.println(i);
  		    Station  station = new Station();
  		    Thread t1=new Thread(station);
  		    t1.start();
  		  }
 
	}
	
	 /*public static void main(String args[])

	    {

		 Station mt=new Station();

	        Thread t1=new Thread(mt);

	        Thread t2=new Thread(mt);

	        Thread t3=new Thread(mt);

	        t1.start();

	        t2.start();

	        t3.start();

	    } */
 


}
