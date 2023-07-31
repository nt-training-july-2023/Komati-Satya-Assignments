package MultiThreadingAssinment;

import java.util.Scanner;

class PriorityThread1 extends Thread{
	int i;
	PriorityThread1(int i){
		this.i=i;
	}
	public void run() {
		if(i%2==0) {
			System.out.println("Even Number");
		}
		else{
			System.out.println("Odd Number");
		}
	}
}

class PriorityThread2 extends Thread{
	int i;
	PriorityThread2(int i){
		this.i=i;
	}
	public void run() {
		int j,flag=0;
		for(j=2;j<i/2;j++) {
		if(i%j==0) {
			System.out.println("Not a primenumber");
			flag=1;
			break;
		}
		}
		if(flag!=1) {
			System.out.println("Prime Number");
		}
		
	}
}

public class PriorityThread {
  public static void main(String[] args) {
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter a Number");
	  int n=sc.nextInt();
	  PriorityThread1 p1=new PriorityThread1(n);
	  PriorityThread2 p2=new PriorityThread2(n);
	  p1.setPriority(3);
	  p2.setPriority(6);
	  p1.start();
	  p2.start();
  }
}
