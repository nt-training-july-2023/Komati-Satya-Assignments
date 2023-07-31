package Strings;
import java.util.Scanner;
public class ReplaceChar {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String s=sc.next();
		System.out.println("Enter a character to replace");
		char c=sc.next().charAt(0);
		System.out.println("Enter a character ");
		char r=sc.next().charAt(0);
		System.out.println("new string is"+s.replace(c, r));
	}

}
