package InputAndOutputStreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.SequenceInputStream;

//It is used to read the data from multiple streams.

public class SequenceInputStreamExample {
   public static void main(String[] args) throws Exception {
	   FileInputStream fis1=new FileInputStream("File.txt");
	   FileInputStream fis2=new FileInputStream("In.txt");
	   SequenceInputStream sis=new SequenceInputStream(fis1,fis2);
	   int i=0;
	   while((i=sis.read())!=-1)
	   {
		   System.out.print((char)i);
	   }
	   sis.close();
	   fis1.close();
	   fis2.close();
   }
}
