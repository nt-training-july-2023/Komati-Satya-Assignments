package OOPs;

//If we declare variable as static then it will be same for all the objects.It reduces the memory usage.

public class Static_Variable {
	static String clg="AU";
	String name;
	int no;
	
    Static_Variable(String name,int no){
    	this.name=name;
    	this.no=no;
    	
    }
	void display()
	{
		System.out.println("name: "+name +" no: "+no +" clg: "+clg);
	}

	public static void main(String[] args)
	{
		Static_Variable s=new Static_Variable("Satya",26);
		s.display();
	}

}
