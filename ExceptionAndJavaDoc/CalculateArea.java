package ExceptionAndJavaDoc;
/**
 * @author KomatiSatya
 * 
 */
abstract class AreaCal{
	abstract void area();
}
/**
 * RectangleArea class for calculating area of rectangle
 * length
 * breadth
 * @param l length
 * @param b breadth
 */

class RectangleArea extends AreaCal{
    int l,b;
    RectangleArea(int l,int b){
    	this.l=l;
    	this.b=b;
    }
	@Override
	void area() {
		System.out.println("The area of Rectangle is: "+(l*b));
		}
}
/**
 * CircleAreaArea class for calculating area of circle
 * @param r radius
 */
class CircleArea extends AreaCal{
    int r;
    CircleArea(int r){
    	this.r=r;
    	
    }
	@Override
	void area() {
		System.out.println("The area of circle is: "+(3.14*r*r));
		}
}
/**
 * TriangleArea class for calculating area of Triangle
 * @param h height
 * @param b base
 */
class TriangleArea extends AreaCal{
    int h,b;
    TriangleArea(int h,int b){
    	this.h=h;
    	this.b=b;
    }
	@Override
	void area() {
		System.out.println("The area of Triangle is: "+(0.5*h*b));
		}
}
/**
 * calculateArea class
 */
public class CalculateArea {
	/**
	 * main method
	 * @param args for main method
	 */
   public static void main(String[] args)
   {
	   RectangleArea r=new RectangleArea(6,4);
	   r.area();
	   TriangleArea t=new TriangleArea(6,8);
	   t.area();
	   CircleArea c=new CircleArea(6);
	   c.area();
   }
}
