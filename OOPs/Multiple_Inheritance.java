package OOPs;

interface Area{
	void area();
}
interface Perimeter{
	void perimeter();
}
class Square implements Area,Perimeter{  //multiple inheritance
	int side;
	Square(int s){
		this.side=s;
	}
	@Override
	public void area() {
		System.out.println("Area of square is: "+(side*side));
	}
	@Override
	public void perimeter(){
		System.out.println("Perimeter of square is: "+(4*side));
	}

	
}
class Rectangle implements Area,Perimeter{
	int l,b;
	Rectangle(int l,int b){
		this.l=l;
		this.b=b;
	}
	@Override
	public void area() {
		System.out.println("Area of Reactangle is: "+(l*b));
	}
	@Override
	public void perimeter(){
		System.out.println("Perimeter of Rectangle is: "+(2*(l+b)));
	}
}
public class Multiple_Inheritance {
  public static void main(String[] args){
	  Square s=new Square(5);
	  s.area();
	  s.perimeter();
	  Rectangle r=new Rectangle(4,3);
	  r.area();
	  r.perimeter();
  }
}
