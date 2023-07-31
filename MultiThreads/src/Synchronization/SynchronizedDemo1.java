package Synchronization;

class Display{
	public synchronized void wish(String name) {
		for(int i=0;i<3;i++) {
			System.out.println("Gud mrng: ");
			try {
				Thread.sleep(2000);
			}
			catch(InterruptedException e){}
			System.out.print(name);
		}
	}
}
class MyThread extends Thread{
	Display d;
	String name;
	MyThread(Display d,String name){
		this.d=d;
		this.name=name;
	}
	public void run() {
		d.wish(name);
	}
}

public class SynchronizedDemo1 {
   public static void main(String[] args) {
	   Display d=new Display();
	   MyThread t1=new MyThread(d,"satya");
	   MyThread t2=new MyThread(d,"komati");
	   t1.start();
	   t2.start();
	   
   }
}
