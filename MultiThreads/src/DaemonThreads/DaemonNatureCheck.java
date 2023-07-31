package DaemonThreads;


//The threads which are executing in the background are called Daemon threads.
//Example: Garbage collector.
//By default main thread is always non daemon.
//By default threads daemon nature will be inherited from the parent to child.
//If we want to change the nature then we have to use setDaemon() method.
//But we can't change the daemon nature of main method. Because it started by JVM.


class Demo extends Thread{
	public void run() {
		System.out.println("Daemon thread example");
	}
}
public class DaemonNatureCheck {
  public static void main(String[] args) {
	  System.out.println(Thread.currentThread().isDaemon());
	  Demo d=new Demo();
	  //d.start();  
	  System.out.println(d.isDaemon());
	  d.setDaemon(true);
	 System.out.println(d.isDaemon());
	  
  }
}
