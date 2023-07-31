package ComparableAndComparator;

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorExample2 {
public static void main(String[] args) {
	TreeSet<String> t=new TreeSet<String>(new MyComparat());
	t.add("NucleusTeq");
	t.add("Hello");
	t.add("Hi");
	t.add("People");
	System.out.println(t);
	
}
}
class MyComparat implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
     String s1=(String)o1;
     String s2=(String)o2;
    return -s1.compareTo(s2);     //Both statements are true.Both save the reverse of alphabetical order.
     //return s2.compareTo(s1);
		
	}
	
}
