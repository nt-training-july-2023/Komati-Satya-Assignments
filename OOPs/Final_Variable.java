package OOPs;

//if we declare variable as final then we can't modify it.
public class Final_Variable {
   final int x=10;
   int y=8;
   void change()
   {
	   y=20;
	  // x=28 ==>It gives a Compile time error.
	   System.out.println("x: "+x);
	   System.out.println("y: "+y);
	   
   }
   public static void main(String[] args)
   {
	   Final_Variable f=new Final_Variable();
	   f.change();
	  // System.out.println("x: "+x" y: "+y);
   }
}
