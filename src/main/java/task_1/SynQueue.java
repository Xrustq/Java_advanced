package task_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SynQueue {

    private Logger logger = LoggerFactory.getLogger(SynQueue.class);

    private static final int QUEUE_CAPACITY = 5;

    private ArrayList<Object> synQueue = new ArrayList<>();
    private AtomicInteger addCount = new AtomicInteger(0);
    private AtomicInteger getCount = new AtomicInteger(0);

    public void add(Object object, int REQUEST_CAPACITY) {
        synchronized (this) {

            try {
                while (synQueue.size() == QUEUE_CAPACITY) {
                    wait();
                }
                if (addCount.intValue() < REQUEST_CAPACITY) {
                    addCount.incrementAndGet();
                    synQueue.add(object);
                    logger.info("Add: " + object);
                } else return;
                logger.info("List: " + synQueue + " request number: " + addCount + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.notifyAll();
            }

        }
    }

    public void get(int REQUEST_CAPACITY) {
        synchronized (this) {
            if (getCount.intValue() < REQUEST_CAPACITY) {
                try {
                    while (synQueue.size() == 0) {
                        this.wait();
                    }
                    logger.info("QueueGet: " + synQueue.get(0) + "\n");
                    synQueue.remove(0);
                    getCount.incrementAndGet();
                    logger.info("List: " + synQueue + " request number: " + addCount + "\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.notifyAll();
                }
            } else return;
        }
    }

    public AtomicInteger getAddCount() {
        return addCount;
    }

    public AtomicInteger getGetCount() {
        return getCount;
    }
}
