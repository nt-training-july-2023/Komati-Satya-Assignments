package Loops;
import java.util.*;
public class Polindrome {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a number to check polindrome");
	int n=sc.nextInt();
	int temp=n;
	int rev=0;
	while(n>0)
	{
		int r=n%10;
		rev=rev*10+r;
		n=n/10;
	}
	if(rev==temp) {
		System.out.println("The given number is polindrome");
	}
	else {
		System.out.println("The given number is not a polindrome");
	}
	
	
}
}
