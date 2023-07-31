package OOPs;
//Inner classes without a name is called Annonymous Inner classes.
//We can declare this classes in 3 ways.

//1.By class 2.interface 3.as a argument
class Food{
	public void taste()
	{
		System.out.println("Salty");
	}
}
public class Annonymous_Inner_Classes {
	public static void main(String[] args) {
		Food f1=new Food()
			{
			    public void taste()
			    {
				   System.out.println("sweety");
			     }
			};
		f1.taste();
		Food f2=new Food();
		f2.taste();
		Food f3=new Food()
		{
		    public void taste()
		    {
			   System.out.println("spicy");
		     }
		};
	f3.taste();
		
	}
	
	

}
