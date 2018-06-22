package task_1.actions;

import task_1.SynQueue;
import task_1.entity.HotelAds;

import java.time.LocalDate;
import java.util.Random;

public class CreateRequest extends Thread {

    private SynQueue hotel;
    private Random random = new Random();
    private int REQUEST_CAPACITY;

    public CreateRequest(SynQueue hotel, int REQUEST_CAPACITY) {
        this.hotel = hotel;
        this.REQUEST_CAPACITY = REQUEST_CAPACITY;
    }

    public void run() {
            while (hotel.getAddCount().intValue() < REQUEST_CAPACITY) {
                hotel.add(new HotelAds(
                        random.nextInt(100) + 1, LocalDate.of(2018, random.nextInt(12) + 1,
                        random.nextInt(28) + 1), random.nextInt(100) + 1), REQUEST_CAPACITY);
        }
    }
}
