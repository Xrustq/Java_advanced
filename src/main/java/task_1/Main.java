package task_1;


public class Main {
    public static void main(String[] args) {
        SynQueue synQueue = new SynQueue();




        CreateReqests reqests1 = new CreateReqests(synQueue);
//        CreateReqests reqests2 = new CreateReqests(synQueue);
//        CreateReqests reqests3 = new CreateReqests(new SynQueue());

        GetReqest getReqest1 = new GetReqest(synQueue);
//        GetReqest getReqest2 = new GetReqest(synQueue);
//        GetReqest getReqest3 = new GetReqest(new SynQueue());

        reqests1.start();
//        reqests2.start();
//        reqests3.start();

        getReqest1.start();
//        getReqest2.start();
//        getReqest3.start();

    }
}
