package OOPs;

class Wheel_Class{
	void wheel() {
		System.out.println("This is the wheel class");
	}
}
class Car extends Wheel_Class{
	void wheel() {
		System.out.println("Car has 4 wheels");
	}
}
class Auto extends Wheel_Class{
	void wheel() {
		System.out.println("Auto has 3 wheels");
	}
}


public class Polymorphism_Overriding {
   public static void main(String[] args) {
	   Wheel_Class w=new Wheel_Class();
	   w.wheel();
	   Car c=new Car();
	   c.wheel();
	   Wheel_Class a=new Auto();
	   a.wheel();
	   }
	
}
