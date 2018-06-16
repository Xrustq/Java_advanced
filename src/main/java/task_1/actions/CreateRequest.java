package task_1.actions;

import task_1.SynQueue;
import task_1.entity.HotelAds;

import java.time.LocalDate;
import java.util.Random;

public class CreateRequest extends Thread {

    private SynQueue hotel;
    private Random random = new Random();

    public CreateRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (hotel.getCountAtomic().intValue() < 15) {
            hotel.add(new HotelAds(
                    random.nextInt(100) + 1, LocalDate.of(2018, random.nextInt(12) + 1,
                    random.nextInt(28) + 1), random.nextInt(100) + 1));

        }
        currentThread().interrupt();
        System.exit(1);
    }
}
