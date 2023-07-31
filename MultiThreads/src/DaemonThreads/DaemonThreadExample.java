package DaemonThreads;

//Whenever last non daemon thread terminates automatically all daemon threads will be terminated irrespective of their position.

//If we are commenting line 1 then both main and child threads are non daemon hence both the threads will be executed until there completion.
//If we are not commenting line 1 then main thread is non daemon and child thread is daemon.Hence whenever the main thread terminates automatically child thread terminated.


class MyThread extends Thread{
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Child Thread");
			try {
				Thread.sleep(2000); 
			}
			catch(InterruptedException e) {
				
			}
		}
	}
}

public class DaemonThreadExample {
  public static void main(String[] args) {
	  MyThread t=new MyThread();
	  t.setDaemon(true);  //  (1)
	  t.start();
	  System.out.println("End of main thread");
	  }
}
