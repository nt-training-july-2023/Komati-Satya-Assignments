package SerializationExamples;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

//In serialization everything takes care by JVM and programmer doesn't have any control.
//Serialization is always possible to save total object.
//If we want to save a part of object then we go to Externalization concept.

 class Ex implements Externalizable{
	String s;
	int i;
	int j;
	 public Ex() {
		System.out.println("Public no arg constructor");
	}
	 public Ex(String s,int i,int j) {
		 this.s=s;
		 this.i=i;
		 this.j=j;
	 }
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(s);
		out.writeObject(i);
		}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		s=(String)in.readObject();
		i=in.readInt();
		
	}
}
 public class ExternalizationEx{
	public static void main(String[] args)  {
		Ex t=new Ex("Satya",1,2);
		try {
		FileOutputStream fos=new FileOutputStream("abcde.ser");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(t);
		System.out.println("Serialization completed");
		
		FileInputStream fis=new FileInputStream("abcde.ser");
		ObjectInputStream ot=new ObjectInputStream(fis);
		
		t=(Ex)ot.readObject(); 
		System.out.println(t.s+".."+t.i+".."+t.j);
		oos.close();
		oos.close();
		fis.close();
		ot.close();
	
		}
		catch(Exception e) {
			System.out.println("Exception occur");
		}
	}		
	
}



