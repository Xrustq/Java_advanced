package task_1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SynQueue {

    private ArrayList<Object> synQueue = new ArrayList<>();
    private AtomicInteger countAtomic = new AtomicInteger(0);

    public synchronized void add(Object object) {
        try {
            while (synQueue.size() == 5) {
                wait();
            }

            synQueue.add(object);

            countAtomic.incrementAndGet();

            Thread.sleep(500);

            System.out.println("QueueAdd" + synQueue + " " + Thread.currentThread().getName() + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            notifyAll();
        }
    }

    public synchronized void get() {
        try {
            while (synQueue.size() == 0) {
                this.wait();
            }

            synQueue.remove(synQueue.size() - 1);

//            wait(1000);

            Thread.sleep(500);

            countAtomic.incrementAndGet();

            System.out.println("QueueGet" + synQueue + " " + Thread.currentThread().getName() + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            notifyAll();
        }
    }

    @Override
    public String toString() {
        return "synQueue=" + synQueue;
    }

    public AtomicInteger getCountAtomic() {
        return countAtomic;
    }

    public void setCountAtomic(AtomicInteger countAtomic) {
        this.countAtomic = countAtomic;
    }
}
