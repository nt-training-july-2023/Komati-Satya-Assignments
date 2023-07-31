package ComparableAndComparator;

import java.util.Comparator;
import java.util.TreeSet;

//For the predefined comparable classes Comparable(I) follows default nature of sorting order.
//For our own classes we have to implement sorting order.
class Employee implements Comparable{
   String name;
   int id;
   Employee(String name,int id){
	 this.name=name;
	 this.id=id;
	}
   public String toString() {
	   return name+".."+id;
   }
   
	@Override
	public int compareTo(Object o) {
		
	    int id1=this.id;
	    Employee e=(Employee)o;
	    int id2=e.id;
	    if(id1>id2) {
	    	return +1;
	    }
	    else if(id1<id2) {
	    	return -1;
	    }
	    else {
		return 0;
	    }
	}
	
}
class Compar implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
		
		Employee e1=(Employee)o1;
		Employee e2=(Employee)o2;
		String s1=e1.name;
		String s2=e2.name;
		return s1.compareTo(s2);
	}
	
}



public class Example {
  public static void main(String[] args) {
	  Employee e1=new Employee("Satya",1);
	  Employee e2=new Employee("pavi",2);
	  Employee e3=new Employee("Siddu",4);
	  Employee e4=new Employee("Adi",3);
	  Employee e5=new Employee("vishal",5);
	  TreeSet t1=new TreeSet();
	  t1.add(e1);
	  t1.add(e2);
	  t1.add(e3);
	  t1.add(e4);
	  t1.add(e5);
	  System.out.println("Using Comparable Interface");
	  System.out.println(t1);
	  TreeSet t2=new TreeSet(new Compar());
	  t2.add(e1);
	  t2.add(e2);
	  t2.add(e3);
	  t2.add(e4);
	  t2.add(e5);
	  System.out.println("Using Comparator Interface");
	  System.out.println(t2);
	 }
  
}
 