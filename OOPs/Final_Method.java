package OOPs;

//If we declare method as a final then we can't override it.
//If in a class some classes wants to override and some other can't override in such cases we move to this final method.
//If all the methods need not be override then we move to final class.

class Abc{
	public final void user() {
		System.out.println("final method");
	}
	void show()
	{
		System.out.println("Non final method");
	}
}
public class  Final_Method extends Abc{
	public static void main(String[] args) {
	Final_Method abc=new Final_Method();
	abc.show();
	abc.user();
	
	/*final void user() {
		System.out.println("final method override");  ==> we can't ovverride
	}*/
	
	
	}
}

	

