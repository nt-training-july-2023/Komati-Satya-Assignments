package SerializationExamples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Account implements Serializable{
	String username="Satya";  
	transient String password="1919";
	private void writeObject(ObjectOutputStream os) throws Exception {
		os.defaultWriteObject(); //writes the non-static and non-transient fields of the current class to the stream. 
		String epwd="123"+password;
		os.writeObject(epwd);
		
	}
	private void readObject(ObjectInputStream is) throws Exception {
		is.defaultReadObject();
		String epwd=(String)is.readObject();
		password=epwd.substring(3);
		}
	
}

public class CustomizedSerialization {
public static void main(String[] args) throws Exception {
	Account a1=new Account();
	System.out.println(a1.username+".."+a1.password);
	FileOutputStream fos=new FileOutputStream("abc.ser");
	ObjectOutputStream oos =new ObjectOutputStream(fos);
	oos.writeObject(a1);
	FileInputStream fis=new FileInputStream("abc.ser");
	ObjectInputStream ois =new ObjectInputStream(fis);
	Account a2=(Account)ois.readObject();
	System.out.println(a2.username+".."+a2.password);
}


}
