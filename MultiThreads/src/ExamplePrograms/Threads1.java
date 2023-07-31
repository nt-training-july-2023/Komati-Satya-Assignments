package ExamplePrograms;

public class Threads1 extends Thread{
   public void run() {
	   System.out.println("Run method");
   }
   public static void main(String[] args)
   {
	   Threads1 t=new Threads1();
	   t.start();
	   System.out.println("Main method");
   }
}
