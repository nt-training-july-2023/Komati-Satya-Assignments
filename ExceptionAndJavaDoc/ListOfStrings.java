package ExceptionAndJavaDoc;

import java.util.ArrayList;
import java.util.Scanner;

public class ListOfStrings {
  public static void main(String[] args)
  {
	  Scanner sc=new Scanner(System.in);
	  ArrayList<String> l=new ArrayList();
	  System.out.println("Enter a number of strings");
	  int n=sc.nextInt();
	  System.out.println("Enter a strings");
	  for(int i=0;i<n;i++)
	  {
		  String s=sc.next();
		  l.add(s);
	  }
	  System.out.println("Enter a index to get the string");
	  int i=sc.nextInt();
	  if(l.isEmpty())
	  {
		  throw new NullPointerException("The list is empty...");
	  }
	  if(i<=n)
	  {
		  System.out.println("The string is: "+l.get(i-1));
	  }
	  else
	  {
		  throw new IndexOutOfBoundsException("The index is out of length...pleace enter the index less than or equal to length");
	  }
  }
	
}
