package task_2.actions;

import task_2.entity.Account;

import java.util.ArrayList;
import java.util.Random;

public class Transaction extends Thread {

    private int TRANSACTION_AMOUNT;
    private ArrayList<Account> accList;
    private Random random = new Random();
    private Transfer transfer = new Transfer();

    public Transaction(ArrayList<Account> accList, int TRANSACTION_AMOUNT) {
        this.accList = accList;
        this.TRANSACTION_AMOUNT = TRANSACTION_AMOUNT;
    }

    @Override
    public void run() {
        while (Transfer.getAtomicInteger().intValue() < TRANSACTION_AMOUNT) {
            try {
                transfer.transfer(accList.get(random.nextInt(accList.size())),
                        accList.get(random.nextInt(accList.size())), random.nextInt(200) + 1, TRANSACTION_AMOUNT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

