package FileIOAssignment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class FilterAssignment {
   public static void main(String[] args) throws Exception {
	   File f=new File("In.txt");
	   File f2=new File("Out.txt");
	   FileInputStream fis=new FileInputStream(f);
	   FilterInputStream fil=new BufferedInputStream(fis);
	   FileOutputStream fiis=new FileOutputStream(f2);
	   FilterOutputStream fos=new FilterOutputStream(fiis);
	   int i=0;
	   while((i=fil.read())!=-1) {
		   char c=(char)i;
	       char k=Character.toUpperCase(c);
		   fos.write(k);
	   }
	   System.out.println("updated");
   }
	
	
}
