package Arrays;
import java.util.Scanner;
public class ArrLargestNo {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a size of an array");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.println("enter an array");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		int max=arr[0];
		for(int i=1;i<n;i++)
		{
			if(max<arr[i])
			{
				max=arr[i];
			}
		}
		System.out.println("maxmium element is:"+max);
	}

}
