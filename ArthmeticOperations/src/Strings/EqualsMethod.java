package Strings;

//In general == operator used for reference comparison and .equals() method used for content comparison.  

public class EqualsMethod {
public static void main(String[] args)
{
	String s1=new String("satya");
	String s2=new String("satya");
	String s3=s1;
	System.out.println(s1==s2);
	System.out.println(s1==s3);
	System.out.println(s1.equals(s2));
	System.out.println(s1.equals(s3));
	
}
}
