package Arthmetic;
import java.util.*;
public class IncDec {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a number");
		int n=sc.nextInt();
		
		
		n++;
		System.out.println("Post Increment:"+n);
		n--;
		System.out.println("Post decrement:"+n);
		++n;
		System.out.println("Pre Increment:"+n);
		--n;
		System.out.println("Pre Decrement:"+n);
		
	}

}
