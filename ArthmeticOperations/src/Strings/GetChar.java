package Strings;
import java.util.*;
public class GetChar {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String s=sc.next();
		System.out.println("Enter a index to get a character");
		int i=sc.nextInt();
		System.out.println("The character t given position is:"+s.charAt(i));
		
		
	}

}
