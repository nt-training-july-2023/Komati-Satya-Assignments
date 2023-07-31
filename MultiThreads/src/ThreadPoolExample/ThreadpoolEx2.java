package ThreadPoolExample;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//In the case of runnable..thread wont't return anything after completing job.
//If the thread is required to return some result after execution then we should go for Callable.
//Callable(I) contain only one method call()
//If we submit a Callable object to executor then after completing job thread returns an object of type Future.

class MyCallable implements Callable{
	int num;
	MyCallable(int num){
		this.num=num;
	}
	public Object call() throws Exception{
		System.out.println(Thread.currentThread().getName()+" is responsible to find sum of first "+num+" numbers");
		int sum=0;
		for(int i=1;i<=num;i++) {
			sum=sum+i;
		}
		return sum;
		}
	}

public class ThreadpoolEx2 {
public static void main(String[] args) throws Exception {
	MyCallable[] cal= { new MyCallable(10),
			            new MyCallable(20),
		            	new MyCallable(30),
			            new MyCallable(40),
			            new MyCallable(50)};
	ExecutorService mc = Executors.newFixedThreadPool(3);
	  for(MyCallable n:cal) {
		 Future f= mc.submit(n);
		 System.out.println(f.get());
	  }
	  mc.shutdown();
	                                        
}
}
