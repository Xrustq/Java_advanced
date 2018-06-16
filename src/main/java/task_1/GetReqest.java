package task_1;

public class GetReqest extends Thread {

    private SynQueue hotel = new SynQueue();

    public GetReqest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run(){
        hotel.get();
        System.out.println("GetReqest " + hotel + " "+ Thread.currentThread().getName() + "\n");
    }
}
