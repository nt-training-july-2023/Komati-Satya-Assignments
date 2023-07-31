package MultiThreadingAssinment;

import java.util.ArrayList;
import java.util.Scanner;


class Reverse extends Thread{
	
	ArrayList<Integer> l1=new ArrayList();
	ArrayList<Integer> l2=new ArrayList();
	Reverse(ArrayList<Integer> l){
		this.l1=l;
	}
	public void run() {
		
		for(int i=l1.size()-1;i>=0;i--)
		{
			l2.add(l1.get(i));
			//System.out.print()
		}
		System.out.println("Before reverse the list is: "+l1);
		System.out.println("After reverse the list is: "+l2);
		//System.out.println("Reverse");
		
	}
}
class SumList extends Thread{
	ArrayList<Integer> l3=new ArrayList();
	SumList(ArrayList<Integer> l){
		this.l3=l;
	}
	//l3.g
	int sum=0;
    public void run() {
	for(int i=0;i<l3.size();i++) {
		sum=sum+(l3.get(i));
	}
	System.out.println("Sum of all elements in a list is: "+sum);
	}
}
public class ReverseAndSum {
 public static void main(String[] args)
 {
	 Scanner sc=new Scanner(System.in);
	 ArrayList<Integer> l=new ArrayList();
	 System.out.print("Enter a length  ");
	 int n=sc.nextInt();
	 System.out.println("Enter numbers");
	 for(int i=0;i<n;i++)
	 {
		 int x=sc.nextInt();
		 l.add(x);
	 }
	 SumList s=new SumList(l);
	 Reverse r=new Reverse(l);
	 r.setPriority(7);
	 s.setPriority(3);
	 System.out.println(r.getPriority());
	 System.out.println(s.getPriority());
	 r.start();
	 s.start();
	 
 }
}
