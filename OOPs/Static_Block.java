package OOPs;

//static block executed at the time of clss loading i.e before main method.


public class Static_Block {
	Static_Block(){
		System.out.println("Constructor");
	}
	static {
		System.out.println("Static block");
	}
    public static void main(String[] args)
    {
    	Static_Block b=new Static_Block();
    	System.out.println("Main method");
    	Static_Block b1=new Static_Block();
    }
	
}
