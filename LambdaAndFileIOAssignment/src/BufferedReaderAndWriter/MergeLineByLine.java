package BufferedReaderAndWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
class MergeFiles{
	void merge(File f1,File f2,File f3) throws Exception {
		BufferedReader br1=new BufferedReader(new FileReader(f1));
		BufferedReader br2=new BufferedReader(new FileReader(f2));
		BufferedWriter bw=new BufferedWriter(new FileWriter(f3));
		String line1=br1.readLine();
		String line2=br2.readLine();
		while((line1!=null) || (line2!=null)) {
			if(line1!=null) {
				bw.write(line1);
				bw.newLine();
				line1=br1.readLine();
			}
			if(line2!=null) {
				bw.write(line2);
				bw.newLine();
				line2=br2.readLine();
			}
			}
		System.out.println("Files merged line by line");
		br1.close();
		br2.close();
		bw.close();
		
		
	}
}

public class MergeLineByLine {
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
		  MergeFiles m=new MergeFiles();
		  m.merge(f1,f2,f3);
	}  
}
