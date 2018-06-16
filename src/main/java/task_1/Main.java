package task_1;


import task_1.actions.CreateRequest;
import task_1.actions.GetRequest;

public class Main {
    public static void main(String[] args) {

        SynQueue synQueue = new SynQueue();

        new CreateRequest(synQueue).start();
        new CreateRequest(synQueue).start();
        new CreateRequest(synQueue).start();

        new GetRequest(synQueue).start();
        new GetRequest(synQueue).start();
        new GetRequest(synQueue).start();
        new GetRequest(synQueue).start();
        new GetRequest(synQueue).start();
        new GetRequest(synQueue).start();
    }
}
