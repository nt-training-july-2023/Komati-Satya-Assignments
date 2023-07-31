package lockInterfacePrograms;

import java.util.concurrent.locks.ReentrantLock;

//If we comment line (1) and (2) then the threads will executed simultaneously and we will get a irregular output.
//If we are not comment line (1) and (2) then the threads will be executed one by one and we will get regular output. 

class DisplayName{
	ReentrantLock l=new ReentrantLock();
	public void wish(String name) {
		l.lock(); //----(1)
		for(int i=0;i<6;i++) {
			System.out.println("Hello ");
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.out.println("Exception occur");
			}
			System.out.println(name);
			}
		l.unlock(); //----(2)
	}
}
class LockDemo extends Thread{
	DisplayName d;
	String name;
	LockDemo(DisplayName d,String name){
		this.d=d;
		this.name=name;
	}
	public void run() {
		d.wish(name);
	}
}
public class ReentrantLock2 {
public static void main(String[] args) {
	DisplayName d=new DisplayName();
	LockDemo ld1=new LockDemo(d,"NucleusTeq");
	LockDemo ld2=new LockDemo(d,"people");
	ld1.start();
	ld2.start();
}
}
