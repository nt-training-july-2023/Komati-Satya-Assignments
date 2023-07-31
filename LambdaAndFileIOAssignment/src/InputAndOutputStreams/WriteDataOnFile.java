package InputAndOutputStreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class WriteDataOnFile {
 public static void main(String[] args)  {
	 try {
	 FileOutputStream fos=new FileOutputStream("Input.txt");
	 String s="Welcome to nucleusTeq";
	 byte[] b=s.getBytes();
	 fos.write(b);
	 System.out.print("written the data");
	 }
	 catch(Exception e) {
		 System.out.println(e);
	 }
	 
 }
}
