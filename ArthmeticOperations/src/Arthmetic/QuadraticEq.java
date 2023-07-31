package Arthmetic;
import java.util.*;
public class QuadraticEq {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a coefficient of x^2");
		int a=sc.nextInt();
		System.out.println("Enter a coefficient of x");
		int b=sc.nextInt();
		System.out.println("Enter a constant");
		int c=sc.nextInt();
		System.out.println("The equation is:"+a+"X^2+"+b+"X+"+c);
		double d=(float) Math.sqrt((b*b)-(4*a*c));
		if(d>0)
		{
		double r1=((-b)+d/(2.0*a));
		System.out.println("Root1: "+r1);
		}
		else
		{
	     System.out.println("The quadratic equation has imaginary roots");
		}
		}
		
		
		
	}


