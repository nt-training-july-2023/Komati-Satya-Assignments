package OOPs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Properties_Class {
	public static void main(String[] args) throws IOException
	{
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("abc.properties");
		p.load(fis);
		System.out.println(p);
		
		System.out.println("the username is: "+p.getProperty("username"));
		System.out.println("the password is: "+p.getProperty("password"));
		
		p.setProperty("Siddu","1234");
		FileOutputStream fos=new FileOutputStream("abc.properties");
		p.store(fos,"another username and password added");
		
		System.out.println(p);
		
		Enumeration e=p.propertyNames();
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
		
		
		
		
	}

}
