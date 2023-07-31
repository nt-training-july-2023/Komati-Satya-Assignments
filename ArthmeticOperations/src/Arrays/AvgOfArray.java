package Arrays;
import java.util.Scanner;
public class AvgOfArray {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a size of an array");
		int n=sc.nextInt();
		int[] arr=new int[n];
		float sum=0f;
		System.out.println("Enter an array");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++)
		{
			sum=sum+arr[i];
		}
		System.out.println("Average of an array elements is:"+(sum)/n);
	}

}
