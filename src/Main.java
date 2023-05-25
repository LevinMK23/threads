import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {



    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Stack<Integer> stack = new Stack<>();

        new Thread(new ValueProducer(stack, lock)).start();
        new Thread(new ValueProducer(stack, lock)).start();
        new Thread(new Operator(lock, stack)).start();
    }
}