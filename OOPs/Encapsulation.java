package OOPs;

import java.util.Set;

public class Encapsulation {
	public static void main(String[] args)
	{
		Bank b=new Bank();
		b.setName("Satya");
		b.setBalance(15000);
		b.setAccountNo(234567);
	    System.out.println("Name:"+b.getName());
	    System.out.println("Account Number:"+b.getAccountNo());
	    System.out.println("Balance:"+b.getBalance());
	    
	}

}
 class Bank{
	 private double balance;
	 private String name;
	 private long accountNo;
	 
	 
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
 }

	
