package Strings;
import java.util.Scanner;
public class String_Methods {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a string");
	String s=sc.next();
	System.out.println("Enter a character to check the whether string ends with the character");
	String c=sc.next();
	if(s.endsWith(c))
		System.out.println("yes..ends with "+c);
	else
		System.out.println("no..ends with "+c);
	System.out.println("Enter a string to check the both strings are equal or not");
	String d=sc.next();
	
	
	System.out.println("with the case sensitive(not ignoring case)");
	if(s.contentEquals(d))
		System.out.println("equal ");
	else
		System.out.println("not equal ");
	
	
	System.out.println("ignoring case");
	if(s.equalsIgnoreCase(d))
		System.out.println("equal ");
	else
		System.out.println("not equal ");
		
	System.out.println("Enter a sequence of character to check whether the string contains the characters or not");
	String e=sc.next();
	if(s.contains(e))
		System.out.println("yes..it contains "+e);
	else
		System.out.println("no..it not contain "+e);
	
	

	System.out.println("enter a character to get a lastindex ");
	char f=sc.next().charAt(0);
	System.out.println("last index is: "+s.lastIndexOf(f));
	
}
}
