package task_2.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_2.entity.Account;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Transfer {

    private static Logger logger = LoggerFactory.getLogger(Transfer.class);
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public void transfer(Account account1, Account account2, int amount , int TRANSACTION_COUNT) throws InterruptedException {

        if (account1.equals(account2)) {
            return;
        }

        if (account1.getBalance() < amount) {
//            throw new IllegalArgumentException("Insufficient funds");
            logger.info("Insufficient funds");
            return;
        }

        logger.info("Trying to lock acc1 id = " + account1.getId() + " balance = " + account1.getBalance());
        if (account1.getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
            try {
                logger.info("Trying to lock acc2 id = " + account2.getId() + " balance = " + account2.getBalance());
                if (account2.getLock().tryLock(30, TimeUnit.MILLISECONDS)) {
                    try {
                        if (getAtomicInteger().intValue() < TRANSACTION_COUNT) {
                            atomicInteger.incrementAndGet();
                            logger.info("Locked both accounts, do the transfer");
                            account1.withdraw(amount);
                            account2.deposit(amount);

                        }
                        return;
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
