package Arrays;
import java.util.*;
public class MatrixAdd {
  public static void main(String[] args)
  {
	  Scanner sc=new Scanner(System.in);
	  System.out.println("enter a dimensions of a matrix rowXColumn");
	  int r=sc.nextInt();
	  int c=sc.nextInt();
	  int[][] m1=new int[r][c];
	  int[][] m2=new int[r][c];
	  int[][] m3=new int[r][c];
 	  System.out.println("enter a first matrix");
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			 m1[i][j]=sc.nextInt();
		  }
	  }
	  System.out.println("enter a second matrix");
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			 m2[i][j]=sc.nextInt();
		  }
	  }
	  System.out.println("First matrix is:");
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			 System.out.print(m1[i][j]+" ");
		  }
		  System.out.println();
	  }
	  System.out.println("Second matrix is:");
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			  System.out.print(m2[i][j]+" ");
		  }
		  System.out.println();
	  }
	  
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			 m3[i][j]=m1[i][j]+m2[i][j];
		  }
	  }
	  System.out.print("aum of two matrices is:");
	  for(int i=0;i<r;i++)
	  {
		  for(int j=0;j<c;j++)
		  {
			  System.out.print(m3[i][j]+" ");
		  }
		  System.out.println();
	  }
  }
}
