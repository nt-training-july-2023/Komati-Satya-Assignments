package ExceptionAndJavaDoc;

import java.util.Scanner;

class NotEvenNumberException extends Exception
{
	NotEvenNumberException(String s)
	{
		super(s);
	}
}


public class EvenNumber {
	public static void main(String[] args) throws NotEvenNumberException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a number");
		int n=sc.nextInt();
		if(n%2==0)
		    System.out.println("the given number is even");
		else
			throw new NotEvenNumberException("The number is not an even number...pleace give an even number");
	}

}
