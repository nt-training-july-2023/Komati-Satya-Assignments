package ThreadGroupExamples;

//Every thread group in java is the child group of system group either directly or indirectly.
//Every thread in java belongs to same group. Main thread belongs to main group.

public class ThreadGroup1 {
  public static void main(String[] args) {
	  System.out.println(Thread.currentThread().getThreadGroup());
	  System.out.println(Thread.currentThread().getThreadGroup().getName());
	  System.out.println(Thread.currentThread().getThreadGroup().getParent());
	  System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());
	  
  }
}
