package SerializationExamples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeSerilizationExample {
	public static void main(String[] args) throws Exception {
		  
		  
			FileInputStream fos=new FileInputStream("abc.ser");
			ObjectInputStream oos=new ObjectInputStream(fos);

			Details d=(Details)oos.readObject();
			
			System.out.println("DeSerialization completed");
			System.out.println(d.j+".."+d.n);
			
		}
}
