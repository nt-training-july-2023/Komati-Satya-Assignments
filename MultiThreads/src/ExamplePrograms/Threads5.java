package ExamplePrograms;

public class Threads5 extends Thread{
	public void run() {
		System.out.println("Run method");
	}
	public static void main(String[] args)
	{
		Thread t=new Thread("Satya");
		Threads5 t5=new Threads5();
		t5.start();
		System.out.println(t.getName());
	}
 
}
