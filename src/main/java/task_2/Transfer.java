package task_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Transfer extends Thread {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final int WAIT_SEC = 2;

    private Account account1;
    private Account account2;
    private int amount;

    public Transfer(Account account1, Account account2, int amount) {
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            transfer(account1, account2, amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void transfer(Account account1, Account account2, int amount) throws InterruptedException {

        if (account1.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient funds");

        logger.info("Trying to lock acc1 balance=" + account1.getBalance());
        if (account1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
            try {
                logger.info("Trying to lock acc2 balance=" + account2.getBalance());
                if (account2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                    try {
                        logger.info("locked both accounts, do the transfer");
                        account1.withdraw(amount);
                        account2.deposit(amount);
                    } finally {
                        account2.getLock().unlock();
                        logger.info("Unlocked acc2 balance=" + account1.getBalance());
                    }
                }
            } finally {
                account1.getLock().unlock();
                logger.info("Unlocked acc1 balance=" + account2.getBalance());
            }
        }
    }
}
