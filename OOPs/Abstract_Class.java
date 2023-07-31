package OOPs;


abstract class Area1{
	abstract void area();
}
class Triangle extends Area1{
	int h,b;
	Triangle(int h,int b){
		this.h=h;
		this.b=b;
	}
	@Override
    void area() {
		  System.out.println("area of triangle is: "+(0.5*b*h));
		}
	
}
public class Abstract_Class {
	public static void main(String[] args) {
  Area1 a=new Triangle(12,4);
  a.area();
	}
}
