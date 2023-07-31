package ThreadGroupExamples;

class Mythread extends Thread{
	Mythread(ThreadGroup g,String name){
		super(g,name);
	}
	public void run() {
		System.out.println("child thread");
		try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			System.out.println("Exception occur");
		}
	}
}
public class ThreadGroup2 {
@SuppressWarnings("removal")
public static void main(String[] args) throws InterruptedException {
	ThreadGroup pg=new ThreadGroup("parent group");
	ThreadGroup cg=new ThreadGroup(pg,"child group");
	Mythread t1=new Mythread(pg,"child thread1");
	Mythread t2=new Mythread(pg,"child thread2");
	t1.start();
	t2.start();
	System.out.println(pg.activeCount());
	System.out.println(pg.activeGroupCount());
	pg.list();
	Thread.sleep(3000);
	System.out.println(pg.activeCount());
	System.out.println(pg.activeGroupCount());
	pg.list();
	System.out.println(pg.getMaxPriority());
	
	}
}
