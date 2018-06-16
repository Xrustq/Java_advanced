package task_1.actions;

import task_1.SynQueue;


public class GetRequest extends Thread {

    private SynQueue hotel;

    public GetRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (hotel.getCountAtomic().intValue() < 15) {
            hotel.get();
        }
        currentThread().interrupt();
        System.exit(1);
    }
}
