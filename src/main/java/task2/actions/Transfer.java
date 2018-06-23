package task2.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task2.entity.Account;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Transfer {

    private static Logger logger = LoggerFactory.getLogger(Transfer.class);
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public void transfer(Account account1, Account account2, int amount, int TRANSACTION_COUNT) throws InterruptedException {

        if (account1.equals(account2)) {
            return;
        }

        Account acc1 = more(account1, account2);
        Account acc2 = less(account1, account2);

        logger.info("Trying to lock acc1 id = " + acc1.getId() + " balance = " + acc1.getBalance());
        if (acc1.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                logger.info("Trying to lock acc2 id = " + acc2.getId() + " balance = " + acc2.getBalance());
                acc2.getLock().lock();
                try {
                    if (account1.getBalance() < amount) {
//                                throw new IllegalArgumentException("Insufficient funds");
                        logger.info("Insufficient funds");
                        return;
                    }
                    if (getAtomicInteger().intValue() < TRANSACTION_COUNT) {
                        atomicInteger.incrementAndGet();
                        logger.info("Locked both accounts, do the transfer");
                        account1.withdraw(amount);
                        account2.deposit(amount);
                    } else return;
                } finally {
                    acc2.getLock().unlock();
                    logger.info("Unlocked acc2 id = " + account2.getId() + " balance = " + account2.getBalance());
                }
            } finally {
                acc1.getLock().unlock();
                logger.info("Unlocked acc1 id = " + account1.getId() + " balance = " + account1.getBalance());
            }
        }
    }

    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    private Account more(Account account1, Account account2) {
        return account1.getId() > account2.getId() ? account1 : account2;
    }

    private Account less(Account account1, Account account2) {
        return account1.getId() > account2.getId() ? account2 : account1;
    }
}
