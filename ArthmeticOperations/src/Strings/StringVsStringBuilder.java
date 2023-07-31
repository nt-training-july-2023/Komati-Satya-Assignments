package Strings;

//Strings are immutable, means we can't change the value of the string.
//StringBuffer is mutable, we can change the value of the stringBuffer.
//StringBuilder is also mutable.
//The main difference between stringBuffer and stringBuilder is the methods present in stringBuffer is not
// synchronized but in the stringBuilder synchronized.
//StringBuffer is thread safe but not stringBuilder.
/* =>If content is fixed and won't change frequently then we should go for string.
   =>If content is not fixed and keep on changing and thread safe is required then we should go for stringBuffer.
   =>If content is not fixed and keep on changing and thread safe is not required then we should go for stringBuilder.
   */
public class StringVsStringBuilder {
public static void main(String[] args)
{
	String s=new String("Satya");
	s.concat("Komati");
	System.out.println(s);
	StringBuffer sb=new StringBuffer("Satya");
	sb.append("Komati");
	System.out.println(sb);
	StringBuilder sb1=new StringBuilder("Satya");
	sb1.append("Komati").append("Adi").append("Siddu").reverse();//method chaining
	System.out.println(sb1);
	
}
}
