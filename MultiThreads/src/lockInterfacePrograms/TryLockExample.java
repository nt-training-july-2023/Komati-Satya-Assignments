package lockInterfacePrograms;
import java.util.concurrent.locks.*;
//tryLock() is used for acquire a lock without waiting.

class TryLockDemo extends Thread{
	static ReentrantLock r=new ReentrantLock();
	TryLockDemo(String name){
		super(name);
	}
	public void run() {
		if(r.tryLock()) {
			System.out.println(Thread.currentThread().getName()+"...got lock and performing safe operaions");
			try {
				Thread.sleep(8000);
			}
			catch(InterruptedException e) {
				
			}
			r.unlock();
		}
		else {
			System.out.println(Thread.currentThread().getName()+"...unable to get lock and performing alternative operaions");
		}
		}
	}
public class TryLockExample {
  public static void main(String[] args) {
	  TryLockDemo t1=new TryLockDemo("First Thread");
	  TryLockDemo t2=new TryLockDemo("Second Thread");
	  t1.start();
	  t2.start();
	  
  }
}
