package lockInterfacePrograms;

import java.util.concurrent.TimeUnit;
//tryLock(long time,TIMEIUTUNIt) is used for if lock is available then the thread will gwt lock and continue it's execution.
//If the lock is not available then thread will wait until specified amount of time.
//Still if the lock is not available then thread can continue it's execution.

import java.util.concurrent.locks.ReentrantLock;

class TryLockDemo2 extends Thread{
	static ReentrantLock r=new ReentrantLock();
	TryLockDemo2(String name){
		super(name);
	}
	public void run() {
		do {
	try {
		if(r.tryLock(5000,TimeUnit.MILLISECONDS)) {
			System.out.println(Thread.currentThread().getName()+"...got lock");
			Thread.sleep(20000);
            r.unlock();
            System.out.println(Thread.currentThread().getName()+"...Releases lock");
            break;
            }
		
		else {
			System.out.println(Thread.currentThread().getName()+"...unable to get lock and will try again");
		}
		}
	catch(InterruptedException e) {
		
	}
		}while(true);
		
	}
}
public class TryLockExample2 {
public static void main(String[] args) {
	  TryLockDemo2 t1=new TryLockDemo2("First Thread");
	  TryLockDemo2 t2=new TryLockDemo2("Second Thread");
	  t1.start();
	  t2.start();
	  
}
}
