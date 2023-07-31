package SerializationExamples;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Details implements Serializable{
	//String name="NucleusTeq";
	 int n=1;
	 String j="NucleusTeq";
}
public class SerializationExample1 {
  public static void main(String[] args) throws Exception {
	  Details d=new Details();
	  
		FileOutputStream fos=new FileOutputStream("abc.ser");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(d);
		oos.flush();
		oos.close();
		System.out.println("Serialization completed");
		System.out.println(d.j+".."+d.n);
	}
}
