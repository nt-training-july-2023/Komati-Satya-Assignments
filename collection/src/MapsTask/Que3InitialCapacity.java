package MapsTask;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
class Add{
	HashMap<Integer,String> m;
	int c;
    Scanner sc=new Scanner(System.in);
	Add(HashMap<Integer,String> h,int c ){
		this.m=h;
		this.c=c;
	}

	   void addd() {
	  
	  System.out.println("Enter number of elements to add:");
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
		  
		  if(m.size()<=c)
		  {
			  System.out.println(m);
		  }
		  else
		  {
			  m.clear();
			  System.out.println("clear");
			  System.out.println(m);
		  }
		 
	   }  
	   void show() {
		   for(Entry<Integer, String> entry:m.entrySet()) {
				System.out.println(entry.getKey()+" : "+entry.getValue());
	   }
	   }
}
public class Que3InitialCapacity {
  public static void main(String[] args) {
	  
	  Scanner sc=new Scanner(System.in);
		 System.out.println("Enter initial capacity");
		 int c=sc.nextInt();
		  HashMap<Integer,String> m=new HashMap<>(c);
	      Add a=new Add(m,c);
	       a.addd();
	       a.show();
	 
  }
}
