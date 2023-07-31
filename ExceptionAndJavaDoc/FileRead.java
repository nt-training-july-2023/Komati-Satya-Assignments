package ExceptionAndJavaDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

class FileRead2{
	void method(File f1) throws IOException {
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(f1);
	} catch (FileNotFoundException e) {
		System.out.println("File Not found..pleace enter valid file");
	}
	
	int i;
	try {
	while((i=fis.read())!=-1) {
		System.out.println("Th data is:"+i);
		}
	System.out.println("File Read");
	}
	catch(FileNotFoundException e) {
		System.out.println("Error occur,,pleace enter valid file name");
	}
	finally {
		if(fis!=null) {
			fis.close();
		    System.out.println("File closed");
		}
	}
	}
}



public class FileRead {
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a file");
		String s1=sc.next();
		
		File f1=new File(s1+".txt");
		
		FileRead2 f=new FileRead2();
		f.method(f1);
		
		
	}

}
