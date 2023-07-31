package MapsTask;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

class Crud{
	HashMap<Integer,String> m=new HashMap<>();
	Scanner sc=new Scanner(System.in);
	void add() {
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
	}
	void delete() {
		System.out.println("Enter key to delete");
		int y=sc.nextInt();
		if(m.containsKey(y)) {
			m.remove(y);
			System.out.println("Deleted");
		}
		else {
			System.out.println("The map does not contain key");
		}
	}
	void display() {
		System.out.println("The map elements are");
		for(Entry<Integer, String> entry:m.entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
			
		}
	}
	void update()
	{
		System.out.println("Enter key to update");
		int x=sc.nextInt();
		System.out.println("Enter a new value");
		String str=sc.next();
		if(m.containsKey(x)) {
			m.replace(x, str);
			System.out.println("Updated");
		}
		else {
			System.out.println("The map does not contain key");
		}
	}
}
public class Que2CrudOperations {
 public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     Crud c=new Crud();
	 do {
		 System.out.println("1.Add");
		 System.out.println("2.delete");
		 System.out.println("3.Update");
		 System.out.println("4.display");
		 System.out.println("5.Exit");
		 System.out.println("Enter choice");
		 int ch=sc.nextInt();
	 switch(ch)
	 {
	 case 1: c.add();
	         break;
	 case 2: c.delete();
	         break;
	 case 3: c.update();
	         break;
	 case 4: c.display();
	         break;
	 case 5:System.exit(0);
	     
	 default: System.out.println("Enter a correct choice");
	 }
	 }while(true);
 }
}
