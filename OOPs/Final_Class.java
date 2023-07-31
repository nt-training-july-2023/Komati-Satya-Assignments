package OOPs;
//If we declare class as final we can't inherited it.

//we can't declare a class as a final then the all the child classes may be uses a username and password.It leads to security issues.
//For this highly recommended to use class as a final.

public final class Final_Class {
	String username="Satya";
	String password="1919";
	void display() {
		System.out.println("this is final class");
		System.out.println("username: "+username +" password: "+password);
		
	}
	public static void main(String[] args)
	{
		Final_Class c=new Final_Class();
		c.display();
	}

}
  //class demo extends Final_Class{.....} ==>It will gives error.
	  
  
