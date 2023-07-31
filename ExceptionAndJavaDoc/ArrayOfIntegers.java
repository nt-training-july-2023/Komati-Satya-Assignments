package ExceptionAndJavaDoc;

import java.util.Scanner;

public class ArrayOfIntegers {
  public static void main(String[] args)
  {
	  	Scanner sc=new Scanner(System.in);
	  	int[] arr= {1,2,3,4};
	  	System.out.println("Enter a index to get integer");
	  	int i=sc.nextInt();
	  	if(arr.length==0)
	  	{
	  		throw new NullPointerException("the array is empty...");
	  	}
	  	if(i<arr.length+1)
	  	{
	  		System.out.println("The element is: "+arr[i-1]);
	  	}
	  	else
	  	{
	  		throw new IndexOutOfBoundsException("The index is out of range...pleace enter a index less than or equal to length");
	  	}
	  	
	  	
  }
}
