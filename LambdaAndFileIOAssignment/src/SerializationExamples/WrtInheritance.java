package SerializationExamples;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Even through child class doesn't implement serializable, we can serialize child class
//object if parent class implements serializable Interface.
//serializable nature is inheriting from parent to child. 

 class Parent implements Serializable{
	 int i=10;
 }
 class Child extends Parent{
	 int j=20;
 }
public class WrtInheritance {
  public static void main(String[] args) throws Exception {
	  Child c=new Child();
	  FileOutputStream fos= new FileOutputStream("In.txt");
	  ObjectOutputStream oos=new ObjectOutputStream(fos);
	  oos.writeObject(c);
	  System.out.println("Serialization completed");
	  System.out.println(c.i+".."+c.j);
  }
}
