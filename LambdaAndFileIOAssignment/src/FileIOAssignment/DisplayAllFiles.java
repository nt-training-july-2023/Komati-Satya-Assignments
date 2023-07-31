package FileIOAssignment;

import java.io.File;
import java.util.Scanner;

/*class DisplayFiles{
	void printFiles(File[] f,int i,int j) {
		if(i==f.length) {
			return;
		}
		if(f[i].isFile())
		{
			System.out.println(f[i].getName());
		}
		else if(f[i].isDirectory()){
			System.out.println("["+f[i].getName()+"]");
			printFiles(f[i].listFiles(),0,j+1);
		}
		printFiles(f,i+1,j);
	}
}

public class DisplayAllFiles {
   public static void main(String[] args) {
	   String path="C://Program Files";
	   File f=new File(path);
	   DisplayFiles df=new DisplayFiles();
	   if(f.exists()&&f.isDirectory()) {
		   File a[]=f.listFiles();
		   System.out.println("Displaying all the files: "+f);
		   df.printFiles(a, 0, 0);
	   }
   }
}*/
public class DisplayAllFiles{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a file path");
		String file=sc.nextLine();
		File path=new File(file);
		String arr[]=path.list();
		for(String s:arr) {
			System.out.println(s);
			
		}
	}
}
