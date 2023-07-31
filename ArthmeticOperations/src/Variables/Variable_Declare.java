package Variables;

public class Variable_Declare {
	
    int a=10;
    float f=26.9f;
    long l=56l;
    double d=23.9;
    byte b=45;
    char c='S';
    boolean e=true;
    void display()
    {
    	System.out.println("Int: "+a);
    	System.out.println("Float: "+f);
    	System.out.println("Long: "+l);
    	System.out.println("Double: "+d);
    	System.out.println("Byte: "+b);
    	System.out.println("Char: "+c);
    	System.out.println("Boolean: "+e);
    	
    }
    void change()
    {
    	a=15;
    	c='D';
    	System.out.println("After changing the int and char value");
    	System.out.println("Int: "+a);
    	System.out.println("Char: "+c);
    }
    public static void main(String[] args) {
    
    Variable_Declare v=new Variable_Declare();
    v.display();
    v.change();
    
}
}
