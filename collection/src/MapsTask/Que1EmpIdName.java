package MapsTask;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Que1EmpIdName {
  public static void main(String[] args) {
	  Scanner sc=new Scanner(System.in);
	  HashMap<Integer,String> m=new HashMap<>();
	  System.out.println("Enter number of elements to add:");
	  int n=sc.nextInt();
	  int empId;
	  String empName;
	  
	  for(int i=0;i<n;i++)
	  {   
		  System.out.println("Enter a empid");
		  empId=sc.nextInt();
		  System.out.println("Enter a Emp name");
		  empName =sc.next();
		  m.put(empId, empName);
	  }
	  
	  for(Integer key: m.keySet()) {
		  System.out.println("EmpId: "+key);
	  }
	  for(Entry<Integer, String> entry :m.entrySet()) {
		  System.out.println("Emp Id is:"+entry.getKey());
		  System.out.println("Emp name is:"+entry.getValue());
	  }
	  
  }
}
