package MapsTask;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Q4ContainsKeyValue {
	public static void main(String[] args) {
		  Scanner sc=new Scanner(System.in);
		  HashMap<Integer,String> m=new HashMap<>();
		  System.out.println("Enter number of elements to add:");
		  int n=sc.nextInt();
		  String str;
		  int x;
		  for(int i=0;i<n;i++)
		  {   
			  System.out.println("Enter a empid");
			  x=sc.nextInt();
			  System.out.println("Enter a Emp name");
			  str =sc.next();
			  m.put(x,str);
		  }
		  
		  System.out.println("Enter a key to search");
		  int key=sc.nextInt();
		  if(m.containsKey(key))
		       System.out.println("The map contains the key");
		  else
			   System.out.println("The map does not contain the key");
		  
		  
		  System.out.println("Enter a value to search");
		  String value=sc.next();
		  if(m.containsValue(value))
		       System.out.println("The map contains the value");
		  else
			  System.out.println("The map does not contain the value");
	  }
}
