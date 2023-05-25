import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.function.BinaryOperator;

public class Operator implements Runnable {

    private final Lock lock;
    private final Stack<Integer> stack;

    private boolean running;
    private final Map<String, BinaryOperator<Integer>> operations;

    private final String[] ops = new String[] {"+", "-"};

    private final Random rnd;


    public Operator(Lock lock, Stack<Integer> stack) {
        this.lock = lock;
        this.stack = stack;
        this.rnd = new Random();
        this.running = true;
        operations = Map.of(
                "+", Integer::sum,
                "-", (x, y) -> x - y
        );
    }


    @Override
    public void run() {
        try {
            while (running) {
                lock.lock();
                String op = ops[rnd.nextInt(2)];
                if (stack.size() >= 2) {
                    int x = stack.pop();
                    int y = stack.pop();
                    int res = operations.get(op).apply(x, y);
                    System.out.println("Calculated: " + res);
                    stack.push(res);
                }
                lock.unlock();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.err.println("OLOLO LOL");
        }
    }
}
