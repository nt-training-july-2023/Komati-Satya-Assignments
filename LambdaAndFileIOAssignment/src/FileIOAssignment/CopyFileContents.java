package FileIOAssignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

class FileCopy{
	void copy(File f1,File f2) throws IOException {
		FileInputStream fis=new FileInputStream(f1);
		FileOutputStream fos=new FileOutputStream(f2);
		int i;
		try {
		while((i=fis.read())!=-1) {
			fos.write(i);
		}
		System.out.println("File copied");
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found enter a valid file name");
		}
		finally {
			fis.close();
			fos.close();
			System.out.println("File closed");
		}
	}
}


public class CopyFileContents {
public static void main(String[] args) throws IOException {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a file name");
	String file1=sc.next();
	File f1=new File(file1+".txt");
	System.out.println("Enter a destination name");
	String file2=sc.next();
	File f2=new File(file2+".txt");
	FileCopy fc=new FileCopy();
	fc.copy(f1,f2);
	
	
}
}
