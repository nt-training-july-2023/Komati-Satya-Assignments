package LambdaExpression;

import java.util.Scanner;

public class VowelsChange {
   public static void main(String[] args) {
	   Scanner sc=new Scanner(System.in);
	   System.out.println("Enter a string");
	   String s=sc.next();
	   
	   
  StringInterface st =(s1)->{
	  
	   for(int i=0;i<s1.length();i++) {
		   if(s1.charAt(i)=='a' ||s1.charAt(i)=='e' ||s1.charAt(i)=='i' || s1.charAt(i)=='o' ||s1.charAt(i)=='u' || s1.charAt(i)=='A' ||s1.charAt(i)=='E' ||
				   s1.charAt(i)=='I' ||s1.charAt(i)=='O' ||s1.charAt(i)=='U' ) {
			   s1=s1.replace(s1.charAt(i), '#');
		   }
	   }
	
	return s1;
	   
   };
	   
	   /*StringInterface st=(s1)->{
		   s1=s1.replaceAll("[aeiouAEIOU]", "#");
		   return s1;
	   };*/
   String temp=st.change(s);
   System.out.println("The new string is: "+temp);
   }

}
