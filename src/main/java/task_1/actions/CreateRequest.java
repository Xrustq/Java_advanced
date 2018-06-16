package task_1.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_1.SynQueue;
import task_1.entity.HotelAds;

import java.time.LocalDate;
import java.util.Random;

public class CreateRequest extends Thread {

    private Logger logger = LoggerFactory.getLogger(CreateRequest.class);
    private SynQueue hotel;
    private Random random = new Random();

    public CreateRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (hotel.getCountAtomic().intValue() <= 14) {
            hotel.add(new HotelAds(random.nextInt(100) + 1, LocalDate.of(2018, random.nextInt(12) + 1,
                    random.nextInt(28) + 1), random.nextInt(100) + 1));

//            logger.info("CreateRequest " + hotel + "\n");
        }
        currentThread().interrupt();
        System.exit(1);
    }
}
