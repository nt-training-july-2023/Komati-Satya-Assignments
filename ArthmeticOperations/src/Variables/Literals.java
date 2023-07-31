package Variables;

//There are some rules for literals.
//For integer,byte,short,long we have to use only integer types.
//For floating we have to use integer and float values,
//For double we have to use float ,double values and character also.
//For character we have to use integer,float,double and character values.
//For boolean we have to use only true and false.

public class Literals {
	public static void main(String[] args)
	{
		int x=10;
		//int y='c'  ==>gives an error.
		float f=56;
		float v=67.9f;
		//float c=true;  ==>gives an error
		char c='S';
		char c1=76;
		char c2=(char) 23.9; //if we give float values then we have to cast the type.
		double d=23;
		double d1=34.8;
		double d2='u';
		long l=345;
		//byte=34.9;  ==>gives an error
		System.out.println(d2);
	}
  
  
}
