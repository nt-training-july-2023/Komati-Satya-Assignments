package Tasks;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Question2 {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	SortedSet<String> ss=new TreeSet<>();
	String s;
	System.out.println("Enter strings:");
	for(int i=0;i<20;i++) {
		s=sc.next();
		ss.add(s);
	}
	System.out.println(ss); //It follows natural sorting order.
	System.out.println("First element: "+ss.first());
	System.out.println("Last element: "+ss.last());
	System.out.println(ss.headSet("satya"));
	System.out.println(ss.tailSet("satya"));
	
	
}
}
