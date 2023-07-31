package OOPs;

public class Method_Local_Inner_Classses {
	int a=30,b=40;
	void method(){
	    final int c = 10,d=20;
		class Local{
			void average() {
				int avg=(a+b+c+d)/2;
				System.out.println("the avg(inner class method): "+avg);
			}
		}
		Local l=new Local();
		l.average();
		
	}
	public static void main(String[] args) {
		Method_Local_Inner_Classses m=new Method_Local_Inner_Classses();
		m.method();
	}

}
