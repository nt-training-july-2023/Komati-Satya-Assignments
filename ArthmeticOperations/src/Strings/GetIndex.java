package Strings;
import java.util.*;
public class GetIndex {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String s=sc.next();
		System.out.println("Enter a character");
		char c=sc.next().charAt(0);
		for(int i=0;i<s.length();i++)
		{
			if(c==s.charAt(i))
			{
				System.out.println("The index of a character is:"+i);
			}
		}
	}

}
