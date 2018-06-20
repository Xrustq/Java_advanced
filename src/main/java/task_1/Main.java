package task_1;


import task_1.actions.CreateRequest;
import task_1.actions.GetRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        SynQueue synQueue = new SynQueue();

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new CreateRequest(synQueue));
        }

        ExecutorService es2 = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            es2.execute(new GetRequest(synQueue));
        }

        es.shutdown();
        es2.shutdown();
    }
}
