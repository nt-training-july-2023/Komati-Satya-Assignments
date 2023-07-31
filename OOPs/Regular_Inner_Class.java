package OOPs;
//In a regular inner classes we can access static and non static members of a outer class directly
public class Regular_Inner_Class {
	
      int x=10;
      static int y=20;
	  class Inner_class{
		public void sum() {
			System.out.println(x);
			System.out.println("x+y"+(x+y));
			}
	  }
	  public static void main(String[] args) {
		  Regular_Inner_Class o=new Regular_Inner_Class();
		  Regular_Inner_Class.Inner_class i=o.new Inner_class();
		  i.sum();
	  }
	
}
