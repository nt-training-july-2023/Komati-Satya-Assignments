package InputAndOutputStreams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedOutputAndInputStream {
	  
	public static void main(String args[])throws Exception{    
	     FileOutputStream fos=new FileOutputStream("File.txt");    
	     BufferedOutputStream bos=new BufferedOutputStream(fos);    
	     String s="Welcome to nucleusteq";    
	     byte b[]=s.getBytes();    
	     bos.write(b);    
	     bos.flush();    
	     bos.close();    
	     fos.close(); 
	     try{    
	    	    FileInputStream fis=new FileInputStream("In.txt");    
	    	    BufferedInputStream bis=new BufferedInputStream(fis);    
	    	    int i;    
	    	    while((i=bis.read())!=-1){    
	    	     System.out.print((char)i);    
	    	    }    
	    	    bis.close();    
	    	    fis.close();    
	    	  }catch(Exception e){System.out.println(e);}    
}
}
