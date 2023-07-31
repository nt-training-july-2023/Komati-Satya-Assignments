package MultiThreadingAssinment;


class Threads1 extends Thread {
	
	public void run() {
		//System.out.println(t1.getState());
		System.out.println("Threads1 class Run method");
	}
	
}
class Threads2 extends Thread{
	public void run()
	{
		System.out.println("Threads2 class run method");
	}
}
public class EachStateOfThread {


public static void main(String[] args) throws InterruptedException {
	Threads1 t1=new Threads1();
	Threads2 t2=new Threads2();
	/*t2.join();
	System.out.println(t.getState());
	t.start();
	System.out.println(t.getState());
	t.sleep(1000);
	System.out.println(t.getState());
	//t.wait();
	System.out.println(t2.getState());
	//t.notify();
	t2.start();
	System.out.println(t2.getState());
	System.out.println(t.getState());
    t2.suspend();
    System.out.println(t.getState());
    t2.resume();
    System.out.println(t.getState());*/
	t2.join();
	 System.out.println(t1.getState());
	 System.out.println(t2.getState());
	t1.start();
	 System.out.println(t1.getState());
	t2.start();
	 System.out.println(t1.getState());
	 System.out.println(t2.getState());
	Thread.sleep(1000);
	 System.out.println(t1.getState());
	 
}
}
