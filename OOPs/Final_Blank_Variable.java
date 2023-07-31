package OOPs;

//The final variable which is left with initialization is called final blank variable.
//If we are not initialize a final variable at the time of declaration it gives error.
//For this we have to use a constructor for initialization.
//Static final blank variable is initialized in the static block only.
public class Final_Blank_Variable {
   final String name;
   final String village;
   static final int age;
   static {
	   age=20;
   }
   Final_Blank_Variable(){
	this.name = "Satya";
	this.village = "Rajahmundry";
	}
   void display()
   {
	   System.out.println("name: "+name +"  village: "+village +" age:"+age);
   }
   public static void main(String[] args)
   {
	   Final_Blank_Variable v=new Final_Blank_Variable();
	   v.display();
   }
}
