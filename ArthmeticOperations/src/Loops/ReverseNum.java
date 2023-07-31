package Loops;
import java.util.*;
public class ReverseNum {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a number to reverse");
	int n=sc.nextInt();
	int rev=0;
	while(n>0)
	{
		int r=n%10;
		rev=rev*10+r;
		n=n/10;
	}
	System.out.println("Reverse of a number is:"+rev);
	
}
}
