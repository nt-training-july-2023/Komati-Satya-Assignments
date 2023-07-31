package ExamplePrograms;
class Th implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<5;i++)
		{
			System.out.println("Runnable interface Run method" +i);
		}
	}
	
}
public class Threads4 {
	public static void main(String[] args)
	{
   Th t=new Th();
   Thread t1=new Thread(t);
   t1.start();
   for(int i=0;i<5;i++)
   {
	   System.out.println("mains method" +i);
   }
	}
	
}
