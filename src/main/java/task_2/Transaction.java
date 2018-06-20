package task_2;

import java.util.ArrayList;
import java.util.Random;

public class Transaction extends Thread {

    private ArrayList<Account> accList;
    private Random random = new Random();
    private Transfer transfer = new Transfer();

    private final static int TRANSACTION_COUNT = 100;

    public Transaction(ArrayList<Account> accList) {
        this.accList = accList;
    }

    @Override
    public void run() {
        while (Transfer.getAtomicInteger().intValue() < TRANSACTION_COUNT) {
            try {
                transfer.transfer(accList.get(random.nextInt(accList.size())),
                        accList.get(random.nextInt(accList.size())), random.nextInt(200) + 1, TRANSACTION_COUNT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

