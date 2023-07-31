package Arthmetic;
import java.util.*;
public class LeftRightShift {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to left shift");
		int a=sc.nextInt();
		System.out.println("Enter a number of left shifts  ");
		int l=sc.nextInt();
		int ls=a<<l;
		System.out.println("Left Shift of a number is:"+ls);
		System.out.println("Enter a number to right shift");
		int b=sc.nextInt();
		System.out.println("Enter a number of right shifts  ");
		int r=sc.nextInt();
		int rs=a>>l;
		System.out.println("Right Shift of a number is:"+rs);
		
	}

}
