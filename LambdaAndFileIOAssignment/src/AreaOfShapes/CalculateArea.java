package AreaOfShapes;

import java.util.Scanner;

public class CalculateArea {
   public static void main(String[] args) {
	   final float pi= 3.14f;
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter a side of square");
	   int s=sc.nextInt();
	   Shape s1=()->{
		  System.out.println("Area of square is: "+s*s);
	   };
	   s1.area();
	   System.out.println("Enter a length of rectangle");
	   int l=sc.nextInt();
	   System.out.println("Enter breadth of rectangle");
	   int b=sc.nextInt();
	   Shape s2=()->{
		  System.out.println("Area of Rectangle is: "+l*b);
	   };
	   s2.area();
	   System.out.println("Enter a radius of circle");
	   int r=sc.nextInt();
	   Shape s3=()->{
		  System.out.println("Area of circle is: "+ (pi*r*r));
	   };
	   s3.area();
	   System.out.println("Enter a side of cube");
	   int c=sc.nextInt();
	   Shape s4=()->{
		  System.out.println("Area of cube is: "+6*c*c);
	   };
	   s4.area();
	   System.out.println("Enter a radius of sphere");
	   int r1=sc.nextInt();
	   Shape s5=()->{
		  System.out.println("Area of sphere is: "+4*pi*r1*r1);
	   };
	   s5.area();
	   System.out.println("Enter a radius of cylinder");
	   int r2=sc.nextInt();
	   System.out.println("Enter a height of cylinder");
	   int h=sc.nextInt();
	   Shape s6=()->{
		  System.out.println("Area of cylinder is: "+((2*pi*r2*h)+(2*pi*r2*r2)));
	   };
	   s6.area();
   }
}
