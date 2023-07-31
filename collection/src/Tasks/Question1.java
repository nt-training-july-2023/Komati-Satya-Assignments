package Tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Question1 {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	ArrayList<Integer> l =new ArrayList<>();
    System.out.println("Enter numbers");
    int x;
    for(int i=0;i<5;i++) {
    	x=sc.nextInt();
    	l.add(x);
    }
    System.out.print(l);
    System.out.println();
    Iterator itr=l.iterator();
    System.out.println("Elements less than 60:");
    while(itr.hasNext()) {
    	Integer i=(Integer)itr.next();
    	if(i<60)
    		System.out.print(" "+i);
    }
    System.out.println();
    int k;
    ListIterator itr1=l.listIterator();
    System.out.println("Upation of list elements by 5:");
    while(itr1.hasNext()) {
    	Integer i=(Integer)itr1.next();
    	if(i>50) {
    		k=l.indexOf(i);
    		System.out.println(i+" "+k);
    		itr1.set(5);
    		
       	}
    		
    	}
    System.out.print(l);
}
}
