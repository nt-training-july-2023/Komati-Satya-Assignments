package Strings;
import java.util.Scanner;
public class StartWithChar {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a String");
		String s=sc.next();
		System.out.println("Enter a character to check whether string starts with character");
		String s1=sc.next();
		if(s.startsWith(s1))
			System.out.println("String starts with "+s1); 
		else
			System.out.println("String not starts with "+s1);
		}
	
	

}
