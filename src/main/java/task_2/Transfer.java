package task_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Transfer extends Thread {

    private static Logger logger = LoggerFactory.getLogger(Transfer.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

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
        while (atomicInteger.intValue() < 100) {
            try {
                transfer(account1, account2, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void transfer(Account account1, Account account2, int amount) throws InterruptedException {

        if (account1.equals(account2)) {
            return;
        }

        if (account1.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient funds");

        logger.info("Trying to lock acc1 id = " + account1.getId() + " balance = " + account1.getBalance());
        if (account1.getLock().tryLock(300, TimeUnit.MILLISECONDS)) {
            try {
                logger.info("Trying to lock acc2 id = " + account2.getId() + " balance = " + account2.getBalance());
                if (account2.getLock().tryLock(300, TimeUnit.SECONDS)) {
                    try {
                        logger.info("locked both accounts, do the transfer");
                        account1.withdraw(amount);
                        account2.deposit(amount);
                        atomicInteger.incrementAndGet();
//                        Thread.sleep(300);
                    } finally {
                        account2.getLock().unlock();
                        logger.info("Unlocked acc2 id = " + account2.getId() + " balance = " + account2.getBalance());
                    }
                }
            } finally {
                account1.getLock().unlock();
                logger.info("Unlocked acc1 id = " + account1.getId() + " balance = " + account1.getBalance());
            }
        }
    }
    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
