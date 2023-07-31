package ExamplePrograms;


class T1 extends Thread{
	 public void run() {
		System.out.println("Join method example");
	}
}
public class JoinExample {
 public static void main(String[] args) throws InterruptedException {
	 T1 t=new T1();
	 t.start();
	 t.join();
	 System.out.println("Main method");
	 System.out.println(t.getPriority());
	 System.out.println(t.getId());
 }
}
