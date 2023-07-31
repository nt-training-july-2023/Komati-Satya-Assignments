package BufferedReaderAndWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Merge{
	void mergefiles(File f1,File f2,File f3) throws Exception {
		PrintWriter pw =new PrintWriter(f3);
		BufferedReader br=new BufferedReader(new FileReader(f1));
		String line=br.readLine();
		while(line!=null) {
			pw.println(line);
			line=br.readLine();
			
		}
		br=new BufferedReader(new FileReader(f2));
		line=br.readLine();
		while(line!=null) {
			pw.println(line);
			line=br.readLine();
			
		}
		System.out.println("Files merged");
		pw.flush();
		br.close();
		pw.close();
	}
}

public class MergeTwoFiles {
  public static void main(String[] atgs) throws Exception {
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter a file");
	  String file1=sc.next();
	  File f1=new File(file1+".txt");
	  System.out.println("Enter a file");
	  String file2=sc.next();
	  File f2=new File(file2+".txt");
	  System.out.println("Enter destination file");
	  String file3=sc.next();
	  File f3=new File(file3+".txt");
	  Merge m=new Merge();
	  m.mergefiles(f1,f2,f3);
	  
  }
}
