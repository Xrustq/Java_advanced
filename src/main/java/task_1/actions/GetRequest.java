package task_1.actions;

import task_1.SynQueue;


public class GetRequest extends Thread {

    private SynQueue hotel;

    public GetRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
            while (hotel.getGetCount().intValue() < 15) {
                hotel.get();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
