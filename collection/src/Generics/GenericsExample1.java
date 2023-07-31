package Generics;

import java.util.ArrayList;

public class GenericsExample1 {
public static void main(String[] args) {
	ArrayList<String> l=new ArrayList<>();
	l.add("NucleusTeq");
	l.add("Hello");
	m(l);
	System.out.println(l);
	System.out.println("Added");
}
public static void m(ArrayList l) {
	l.add(10);
	l.add(10.5);
	l.add(true);
	System.out.println("Added");
	System.out.println(l);
}
}
