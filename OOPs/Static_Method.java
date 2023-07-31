package OOPs;

//For static method we have to call using a class name instead of object
//when we access a non static variable inside a static method we will get a compile time error 
//with in a static method we can access only a static members

public class Static_Method {
	static int x=10;
	//int z=20;
	static int y=30;
	static void sum()
	{
		System.out.println("Sum is: "+(x+y));
	}
	public static void main(String[] args) {
		Static_Method.sum();
	}
	

}
