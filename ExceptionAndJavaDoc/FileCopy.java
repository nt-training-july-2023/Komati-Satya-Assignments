package ExceptionAndJavaDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

class FileCopy2{
	void method(File f1,File f2) throws IOException {
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(f1);
	} catch (FileNotFoundException e) {
		System.out.println("File Not found..pleace enter valid source file");
	}
	FileOutputStream fos = null;
	try {
		fos = new FileOutputStream(f2);
	} catch (FileNotFoundException e) {
		System.out.println("File Not found..pleace enter valid destination file");
	}
	int i;
	try {
	while((i=fis.read())!=-1) {
		fos.write(i);
		}
	System.out.println("File copied");
	}
	catch(FileNotFoundException e) {
		System.out.println("Error occur");
	}
	finally {
		if(fis!=null)
			fis.close();
		if(fos!=null)
			fos.close();
	}
	}
}



public class FileCopy {
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a source file");
		String s1=sc.next();
		System.out.println("Enter a destination file");
		String s2=sc.next();
		File f1=new File(s1+".txt");
		File f2=new File(s2+".txt");
		FileCopy2 f=new FileCopy2();
		f.method(f1,f2);
		
		
	}

}
