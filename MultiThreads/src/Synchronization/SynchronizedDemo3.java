package Synchronization;

class MultiplicationTable{
	void printTable(int n){
		synchronized(this) {
			System.out.println(n+" Table");
			for(int i=1;i<11;i++) {
				
				System.out.println(n+" * "+i+" = "+ n*i);
			}
			try {
				Thread.sleep(2000);
			}
			catch(InterruptedException e) {
				System.out.println("Exception occur");
			}
		}
	}
}

class Table1 extends Thread{
	MultiplicationTable t;
	int i;
	Table1(MultiplicationTable t,int i){
		this.t=t;
		this.i=i;
	}
	public void run() {
		t.printTable(i);
	}
	
}
class Table2 extends Thread{
	MultiplicationTable t;
	int i;
	Table2(MultiplicationTable t,int i){
		this.t=t;
		this.i=i;
	}
	public void run() {
		t.printTable(i);
	}
	
}


public class SynchronizedDemo3 {
	public static void main(String[] args) {
		MultiplicationTable t=new MultiplicationTable();
		Table1 tb1=new Table1(t,5);
		Table1 tb2=new Table1(t,10);
		
		tb1.start();
		tb2.start();
	}

}
