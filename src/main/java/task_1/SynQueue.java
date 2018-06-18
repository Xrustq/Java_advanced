package task_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SynQueue {

    private Logger logger = LoggerFactory.getLogger(SynQueue.class);

    private static final int QUEUE_CAPACITY = 5;
    private static final int REQUEST_CAPACITY = 15;

    private ArrayList<Object> synQueue = new ArrayList<>();
    private AtomicInteger countAtomic = new AtomicInteger(0);

    public void add(Object object) {
        synchronized (this) {
            if (countAtomic.intValue() < REQUEST_CAPACITY) {
                try {
                    while (synQueue.size() == QUEUE_CAPACITY) {
                        wait();
                    }
                    synQueue.add(object);
                    logger.info("Add: " + object);
                    countAtomic.incrementAndGet();

//                    Thread.sleep(200);

//            System.out.println("Add: "+ object);
                    logger.info("List: " + synQueue + " request number: " + countAtomic + "\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.notifyAll();
                }
            } else return;
        }
    }

    public void get() {
        synchronized (this) {
            try {
                while (synQueue.size() == 0) {
                    this.wait();
                }
                logger.info("QueueGet: " + synQueue.get(0) + "\n");
                synQueue.remove(0);

                Thread.sleep(5000);
                logger.info("List: " + synQueue + " request number: " + countAtomic + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.notifyAll();
            }
        }
    }

    public AtomicInteger getCountAtomic() {
        return countAtomic;
    }
}
