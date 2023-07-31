package Arrays;
import java.util.Scanner;
public class ArrayRotate {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a size of an array");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.println("Enter an array");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
	    int l=arr[n-1];
	    for(int j=0;j<2;j++)
	    {
	    	l=arr[n-1];
	    for(int i=n-2;i>=0;i--)
	    {
	    	
	    	arr[i+1]=arr[i];
	    	
	       }
	    arr[0]=l;
	    }
		
		System.out.println("Array after rotation");
		for(int i=0;i<n;i++)
		{
			System.out.println(arr[i]);
		}
		
	}

}
