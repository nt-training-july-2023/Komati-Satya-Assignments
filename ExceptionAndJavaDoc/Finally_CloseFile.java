package ExceptionAndJavaDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class FileClose{
	void m(File f) throws IOException {
	FileInputStream fis=new FileInputStream(f);
	try {
	while(fis.read()!=-1)
	{
		System.out.println(fis.read());
	}
	}
	catch(Exception e) {
		System.out.println("Error occured");
	}
	finally {
		if(fis!=null)
		{
			fis.close();
			System.out.println("File closed");
	}
	}
	}
	}


public class Finally_CloseFile {
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a file name");
		String s=sc.next();
		File f=new File(s+".txt");
		FileClose ff=new FileClose();
		ff.m(f);
		
		
	}

}
