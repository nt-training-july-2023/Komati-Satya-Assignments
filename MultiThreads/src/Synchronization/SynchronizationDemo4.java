package Synchronization;

class ThreadA extends Thread{
        int total=0;
        public void run() {
        	synchronized(this) {
        		System.out.println("Child thread starts calculation");
        		for(int i=0;i<=100;i++) {
        		 total = total+i;	
        		}
        		System.out.println("Child thread giving notification");
        	}
        }
}


public class SynchronizationDemo4 {
  public static void main(String[] args) throws InterruptedException {
	  ThreadA ta=new ThreadA();
	  ta.start();
	  synchronized(ta) {
		  System.out.println("Main thread calling wait method");
		  ta.wait();
		  System.out.println("Main thread got notification");
		  System.out.println(ta.total);
		  
	  }
	  
	  
  }
}
