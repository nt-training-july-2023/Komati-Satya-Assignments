package ComparableAndComparator;

import java.util.Comparator;
import java.util.TreeSet;
//If we not implementing the comparator(I) then it follows default nature of sorting order

public class ComparatorExample {
   public static void main(String[] args) {
	   TreeSet<Integer> t=new TreeSet<>(new MyComparator());
	   t.add(0);
	   t.add(10);
	   t.add(5);
	   t.add(15);
	   t.add(20);
	   System.out.println(t);
	   
   }
}
class MyComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Integer i1=(Integer)o1;
		Integer i2=(Integer)o2;
		if(i1>i2) {
			return -1;
		}
		if(i1<i2) {
			return +1;
		}
		else {
		return 0;
		}
	}
	
}
