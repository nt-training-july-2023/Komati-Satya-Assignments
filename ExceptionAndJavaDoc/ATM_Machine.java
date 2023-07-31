package ExceptionAndJavaDoc;

import java.util.Scanner;

class InvalidInputException extends RuntimeException{
	public InvalidInputException(String str) {
		super(str);
	}
}
class WithDraw{
	
  int withDraw1(int balance,int amount) {
	 return balance-amount;
	
	}
}


public class ATM_Machine {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter a balance");
		int balance=sc.nextInt();
		if(balance<=0 )
		{
			throw new InvalidInputException("Enter a valid balance");
		}
		System.out.println("Enter a amount to withdraw");
		int withdraw=sc.nextInt();
		if(withdraw<=0 )
		{
			throw new InvalidInputException("Enter a valid balance");
		}
		WithDraw w=new WithDraw();
		System.out.println("The balance is: "+w.withDraw1(balance,withdraw));
		 
		
	}

}
