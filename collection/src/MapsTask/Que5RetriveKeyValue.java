package MapsTask;

import java.util.HashMap;
import java.util.Scanner;

public class Que5RetriveKeyValue {
	 public static void main(String[] args) {
		  Scanner sc=new Scanner(System.in);
		  HashMap<Integer,String> m=new HashMap<>();
		  System.out.println("Enter number of elements to add:");
		  int n=sc.nextInt();
		  int x;
		  String str;
		  
		  for(int i=0;i<n;i++)
		  {   
			  System.out.println("Enter a empid");
			  x=sc.nextInt();
			  System.out.println("Enter a Emp name");
			  str =sc.next();
			  m.put(x,str);
		  }
		  
		  for(Integer key: m.keySet()) {
			  System.out.println("Keys are: "+key);
		  }
		  for(String value:m.values()) {
			  System.out.println("Values are: "+value);
		  }
	 }
}
