package ExceptionAndJavaDoc;

import java.util.Scanner;

class InvalidPasswordException extends RuntimeException{
	InvalidPasswordException(String s)
	{
		super(s);
	}
}

public class Password_Check {
    public static void main(String[] args)
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter a password");
    	String s=sc.next();
    	int c=0;
    	int d=0;
    	for(int i=0;i<s.length();i++)
    	{
    		if(Character.isAlphabetic(s.charAt(i)))
    			c++;
    		if(Character.isDigit(s.charAt(i)))
    			d++;
    	}
    	if(s.length()>8 && c>0 && d>0 && (c+d==s.length()))
    	{
    		System.out.println("Your entered a correct password");
    	}
    	else
    	{
    		throw new InvalidPasswordException("Your entered a invalid password...It must be atleast 8 characters and it contains letters and numbers");
    	}
    		
    	
    }
}
