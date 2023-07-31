package Arthmetic;
import java.util.*;
public class TriangleArea {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter base of a triangle");
		float base=sc.nextFloat();
		System.out.println("Enter height of a triangle");
		float height=sc.nextFloat();
		float a= 0.5f *base*height;
		System.out.println("Area of triangle is:"+a);
		
		
	}

}
