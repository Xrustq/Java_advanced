package task_1.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_1.SynQueue;


public class GetRequest extends Thread {

    private Logger logger = LoggerFactory.getLogger(GetRequest.class);
    private SynQueue hotel;

    public GetRequest(SynQueue hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (hotel.getCountAtomic().intValue() <= 14) {
            hotel.get();
//            logger.info("GetRequest " + hotel + "\n");
        }
        currentThread().interrupt();
        System.exit(1);
    }
}
