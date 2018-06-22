package task_1;


import task_1.actions.CreateRequest;
import task_1.actions.GetRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int REQUEST_CAPACITY = 15;

    public static void main(String[] args) {

        SynQueue synQueue = new SynQueue();

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new CreateRequest(synQueue, REQUEST_CAPACITY));
        }

        ExecutorService es2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            es2.execute(new GetRequest(synQueue, REQUEST_CAPACITY));
        }

        es.shutdown();
        es2.shutdown();
    }
}
