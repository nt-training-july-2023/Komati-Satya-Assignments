package MapsTask;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Que6Remove {
public static void main(String[] args) {
	HashMap<Integer,String> m=new HashMap<>();
	System.out.println("Enter number of elements to add:");
	Scanner sc=new Scanner(System.in);
	  int n=sc.nextInt();
	  int x;
	  String str;
	  
	  for(int i=0;i<n;i++)
	  {   
		  System.out.println("Enter a key");
		  x=sc.nextInt();
		  System.out.println("Enter a value");
		  str =sc.next();
		  m.put(x,str);
	  }
	  System.out.println(m);
	  System.out.println("Enter key to delete");
	  int k=sc.nextInt();
	  System.out.println("Enter value to delete");
	  String s=sc.next();
	  String st=m.get(k);

	  if(s.equals(st))
		  m.remove(k);
	  else
		  System.out.println("Key and value mismatch...so not deleted");
	  
	  for(Entry<Integer, String> entry:m.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
	  }
	  
}
}
