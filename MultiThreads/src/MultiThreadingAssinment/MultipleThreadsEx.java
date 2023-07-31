package MultiThreadingAssinment;

import java.util.Scanner;

 class BelowNumbers extends Thread{
	int n;
	BelowNumbers(int i){
		this.n=i;
	}
	public void run() {
		System.out.println("Number below numbers are:");
	for(int j=n-1;j>0;j--) {
		
		System.out.print(" "+j);
	}
	System.out.println();
	}
}
class Fibonacci extends Thread{
	int n;
	int n1=0;
	int n2=1;
	int fib=0;
	Fibonacci(int i){
		this.n=i;
	}
	public void run() {
		int i=2;
		System.out.println("Fibonacci series is: ");
		System.out.print(n1+" "+n2);
		while(i<n)
		{
			fib=n1+n2;
			System.out.print(" "+fib);
			n1=n2;
			n2=fib;
			i++;
			
		}
	}
}


public class MultipleThreadsEx {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number");
		int i=sc.nextInt();
		BelowNumbers b=new BelowNumbers(i);
		b.start();
		Fibonacci f=new Fibonacci(i);
		f.start();
		
		
	}

}
