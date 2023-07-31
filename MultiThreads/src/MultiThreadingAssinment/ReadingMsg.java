package MultiThreadingAssinment;



class Message1 extends Thread{
	String str;
	Message1(String s){
		this.str=s;
	}
	public void run() {
		System.out.println(Message1.currentThread());
		System.out.println("The message1 is read "+str);
	}
}
class Message2 extends Thread{
	String str;
	Message2(String s){
		this.str=s;
	}
	public void run() {
		System.out.println(Message1.currentThread());
		System.out.println("The message2 is read "+str);
	}
}
class Message3 extends Thread{
	String str;
	Message3(String s){
		this.str=s;
	}
	public void run() {
		System.out.println(Message3.currentThread());
		System.out.println("The message2 is read "+str);
	}
}
public class ReadingMsg {
 public static void main(String[] args) {
	 Message1 m1=new Message1("Satya");
	 m1.setName("Message1 Thread");
	 m1.start();
	 Message2 m2=new Message2("Komati");
	 m2.setName("Message2 Thread");
	 m2.start();
	 Message3 m3=new Message3("NucleusTeq");
	 m3.setName("Message3 Thread");
	 m3.start();
	 
 }
 
}
