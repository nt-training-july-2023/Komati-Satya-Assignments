package OOPs;

class A{
	int a,b;
	A(int a,int b){
		this.a=a;
		this.b=b;
	}
	void sum() {
		System.out.println("the sum is"+(a+b));
	}
	
}
class B extends A{                //Single inheritance
    int c;
	B(int a, int b,int c) {
		super(a, b);
		this.c=c;
		
	}  
	void multiply()
	{
		System.out.println("the mutiplication is"+a*b*c);  
	}
	
}
class C extends B{                     //Multilevel inheritance

	C(int a, int b, int c) {
		super(a, b, c);
	}  
	void display() {
		System.out.println("The values of a="+a+"b="+b+"c="+c);
	}
	
}

public class Inheritance {
	public static void main(String[] args) {
		C c=new C(10,20,30);
		c.sum();
		c.multiply();
		c.display();
		
		
	}
	

}
