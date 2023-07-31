package lockInterfacePrograms;
import java.util.concurrent.locks.*;
//There are some disadvantages of using synchronized keyword.For this implement a Lock(I).
//ReentrantLock is a implementation class of Lock(I).
public class ReentrantLock1 {
    public static void main(String[] args) {
    	ReentrantLock r=new ReentrantLock();
    	r.lock();
    	r.lock();
    	System.out.println(r.isLocked());
    	System.out.println(r.isHeldByCurrentThread());
    	System.out.println(r.getQueueLength());
        r.unlock();
        System.out.println(r.getHoldCount());
        System.out.println(r.isLocked());
        r.unlock();
        System.out.println(r.isLocked());
        System.out.println(r.isFair());
    }
}
