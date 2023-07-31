package Loops;
import java.util.*;
public class MultiplicationTable {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a number which you want a multiplication table");
	int a=sc.nextInt();
	System.out.println("upto which number you want table");
	int t=sc.nextInt();
	System.out.println("Multiplication of a:"+a);
	for(int i=1;i<=t;i++)
	{
		System.out.println(a+"*"+i+"="+(a*i));
	}
	
}
}
