package ExamplePrograms;

class T extends Thread{
	 public void run()
		{
			for(int i=0;i<10;i++)
			{
				System.out.println("Run method: "+i);
			}
		}
}
public class Threads2{
	
		public static void main(String[] args)
		{
			T t2=new T();
			t2.start();
			for(int i=0;i<10;i++)
			{
				System.out.println("Main method: "+i);
			}
		}
	

}
