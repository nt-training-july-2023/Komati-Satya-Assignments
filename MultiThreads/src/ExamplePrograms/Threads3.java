package ExamplePrograms;

class Threads3 implements Runnable{
	public void run() {
		System.out.println("Runnable interface Run method");
	}
	public static void main(String[] args)
	{
		Threads3 t=new Threads3();
		Thread r=new Thread(t);
		r.start();
		System.out.println("main method");
	}
}
//public class Threads3 {

//}
