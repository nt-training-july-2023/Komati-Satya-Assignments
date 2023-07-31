package OOPs;

//We have to declare only inner classes as static.There is no chance of declaring outer class as static.
//If we declare a inner class as static then static nested class is not strongly associated with outer class object

public class Static_Inner_Class {

	static int x=10;
    static int y=80;
	  static class Inner_class{
		public void sum1() {
			System.out.println(x);
			System.out.println("x+y"+(x+y));
			}
	  }
	  public static void main(String[] args) {
		 Static_Inner_Class.Inner_class s=new Static_Inner_Class.Inner_class();
		  s.sum1();
	  }
}
