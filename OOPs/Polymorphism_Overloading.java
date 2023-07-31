package OOPs;

public class Polymorphism_Overloading {
	int addition(int a,int b) {
		return a+b;
	}
	double addition(double a,double b) {
		return a+b;
	}
	int addition(int a,int b,int c) {
		return a+b+c;
	}
	
	
	public static void main(String[] args) {
		Polymorphism_Overloading po=new Polymorphism_Overloading();
		System.out.println("The sum is(two int arg):"+po.addition(10, 20));
		System.out.println("The sum is(three int arg):"+po.addition(10, 20,30));
		System.out.println("The sum is(two double arg):"+po.addition(45.9, 20.0));
	}

}
