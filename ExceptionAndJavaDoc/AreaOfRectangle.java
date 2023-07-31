package ExceptionAndJavaDoc;

import java.util.Scanner;

class InvalidDimensionException extends Exception{
	InvalidDimensionException(String str){
		super(str);
	}
}
class LenBre{
	void abc(int l,int b){
		System.out.println("The area of rectangle is: "+(l*b));
	}
}
public class AreaOfRectangle {
	public static void main(String[] args) throws InvalidDimensionException 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a lenth of the rectangle");
		int length=sc.nextInt();
		if(length<=0)
		{
			throw new InvalidDimensionException("Enter a valid length");
		}
		System.out.println("Enter a breadth of the rectangle");
		int breadth=sc.nextInt();
		if(breadth<=0)
		{
			throw new InvalidDimensionException("Enter a valid breadth");
		}
		LenBre l=new LenBre();
		l.abc(length, breadth);
				
		
	}

}
