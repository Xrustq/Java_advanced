package task_1.actions;

import task_1.SynQueue;


public class GetRequest extends Thread {

    private SynQueue hotel;

    public GetRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (!isInterrupted()) {
            if (hotel.getCountAtomic().intValue() < 12) {
                hotel.get();
            } else interrupt();
        }
    }
}
