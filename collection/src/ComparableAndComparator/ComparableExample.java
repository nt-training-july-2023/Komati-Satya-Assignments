package ComparableAndComparator;

import java.util.TreeSet;

//comparable(I) contain only one method compareTo().
//It follows default nature of sorting order.

public class ComparableExample {
public static void main(String[] args) {
	TreeSet<String> t=new TreeSet<>();
	t.add("A");            //First it add the A.
	t.add("K");            //"K".compateTo("A")==>it gives +ve ,so that it add after the A.
	t.add("Z");            //"Z".compateTo("K")==>it gives +ve ,so that it add after the K.
	t.add("D");            //"D".compateTo("Z")==>it gives -ve ,and again check "D".compateTo("K") ==>it gives -ve ,
	t.add("R");            //and again check "D".compateTo("A")==>It gives +ve so that it add after the A.
	System.out.println(t); //This process continue for all the elements.
}
}
