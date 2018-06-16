package task_1;

import task_1.entity.HotelAds;

import java.time.LocalDate;
import java.util.Random;

public class CreateReqests extends Thread{

    private SynQueue hotel = new SynQueue();
    private Random random = new Random();

    public CreateReqests(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            hotel.add(new HotelAds(i,LocalDate.of(2018, random.nextInt(12) + 1,
                    random.nextInt(28)+1), random.nextInt(i + 100)));
        }
        System.out.println("CreateReqest " + hotel + Thread.currentThread().getName() + "\n");
    }


}
