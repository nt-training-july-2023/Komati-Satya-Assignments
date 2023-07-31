package FileIOAssignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InvertContent{
	void invert(File f1,File f2) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(f1));
		BufferedWriter bw=new BufferedWriter(new FileWriter(f2));
		List<String> arr=new ArrayList();
		
		String line=br.readLine();
		while(line!=null) {
			arr.add(line);
			line=br.readLine();
		}
		System.out.println(arr);
		String[] str=arr.toArray(new String[0]);
		
		for(int j=str.length-1;j>=0;j--) {
			bw.write(str[j]);
			bw.newLine();
		}
		System.out.println("File changed");
		br.close();
		bw.close();
	}
}



public class InvertFile {
public static void main(String[] args) throws IOException {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter a file name");
	String file1=sc.next();
	File f1=new File(file1+".txt");
	System.out.println("Enter a destination file");
	String file2=sc.next();
	File f2=new File(file2+".txt");
	InvertContent i=new InvertContent();
	i.invert(f1,f2);
	
	
	
	
}
}
