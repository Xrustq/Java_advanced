package task1.actions;

import task1.SynQueue;


public class GetRequest extends Thread {

    private SynQueue hotel;
    private int REQUEST_CAPACITY;

    public GetRequest(SynQueue hotel, int REQUEST_CAPACITY) {
        this.hotel = hotel;
        this.REQUEST_CAPACITY = REQUEST_CAPACITY;
    }

    public void run() {
            while (hotel.getGetCount().intValue() < REQUEST_CAPACITY) {
                hotel.get(REQUEST_CAPACITY);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
