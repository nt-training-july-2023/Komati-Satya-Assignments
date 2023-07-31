package ThreadPoolExample;

//In this 3 threads are responsible to execute 5 print names.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintName implements Runnable{
	String name;
	PrintName(String name){
		this.name=name;
	}
	@Override
	public void run() {
	System.out.println(name+"..printed by Thread "+Thread.currentThread().getName());
	try {
		Thread.sleep(2000);
	}
	catch(InterruptedException e) {
		
	}
	System.out.println(name+"..completed by Thread "+Thread.currentThread().getName());	
	}
	
}


public class ThreadPoolEx1 {
  public static void main(String[] args) {
	  PrintName[] names= {new PrintName("Apple"),
			             new PrintName("HP"),
			             new PrintName("Dell"),
			             new PrintName("Asus"),
			             new PrintName("Lenovo")};
	  ExecutorService pl = Executors.newFixedThreadPool(3);
	  for(PrintName name:names) {
		  pl.execute(name);
	  }
	  pl.shutdown();
	  }
  }

