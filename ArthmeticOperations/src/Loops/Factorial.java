package Loops;
import java.util.*;
public class Factorial {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("enter a number ");
	int n=sc.nextInt();
	int fact=1;
	for(int i=2;i<=n;i++)
	{
		fact=fact*i;
	}
	System.out.println("factorial of  number is:"+fact);
	
	
}
}
