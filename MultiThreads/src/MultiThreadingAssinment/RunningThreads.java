package MultiThreadingAssinment;


class Thread1 extends Thread{
	public void run() {
		System.out.println(Thread.currentThread() +" is running");
	}
}
class Thread2 extends Thread{
	public void run() {
		System.out.println(Thread.currentThread()+" is running");
	}
}
class Thread3 extends Thread{
	public void run() {
		System.out.println(Thread.currentThread()+" is running");
	}
}
public class RunningThreads {
	public static void main(String[] args) {		 
  Thread1 t1=new Thread1();
 t1.start();
 Thread2 t2=new Thread2();
 t2.start();
 Thread3 t3=new Thread3();
 t3.start();
//System.out.println(t1.getStackTrace());
//System.out.println(t2.getStackTrace());
//System.out.println(t3.getStackTrace());
 
	}
}
