package InputAndOutputStreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataFromFile {
public static void main(String[] args) throws IOException {
	int i=0;
	try {
		FileInputStream fis=new FileInputStream("In.txt");
		while((i=fis.read())!=-1) {
			System.out.print((char)i);
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
}
}
