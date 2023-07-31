package MultiThreadingAssinment;

import java.util.Scanner;

class Bank{
	static int balance=0;
	 static synchronized void withDraw(){
		 System.out.println("Enter balance to withdraw");
		 Scanner sc=new Scanner(System.in);
		  int amount=sc.nextInt();
		if(balance>amount) {
			System.out.println("The amount is withdraw");
			balance=balance-amount;
			System.out.println("The available balance is: "+balance);
			
			try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
			else {
				 System.out.println("You have less amount");
					}
		
		
	}
	 static synchronized void deposit(int amount) {
		 System.out.println("The amount is deposit");
		 balance=balance+amount;
			System.out.println("The available balance is: "+balance);
			
			try {
             Thread.sleep(1000);
         }
         catch (InterruptedException e) {
             e.printStackTrace();
         }
			
	 }
}
class Banking extends Thread{
	Bank b;
	int a;
	Banking(Bank b,int a){
		this.b=b;
		this.a=a;
	}
	public void run() {
		b.deposit(a);
	}
}
class Banking2 extends Thread{
	Bank b;
	//int a;
	Banking2(Bank b){
		this.b=b;
		//this.a=a;
	}
	public void run() {
		b.withDraw();
	}
}
public class BankingApplication {
  public static void main(String[] args) {
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter balance to credit");
	  int bal=sc.nextInt();
	  Bank b=new Bank();
	  Banking b1=new Banking(b,bal);
	  b1.start();
	  
	  //Bank ba=new Bank();
	  Banking2 b2=new Banking2(b);
	  
	  b2.start();
	  
  }
}
