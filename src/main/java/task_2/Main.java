package task_2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_2.actions.CreateAndWriteAccToFile;
import task_2.actions.ReadAccToList;
import task_2.actions.Transaction;
import task_2.actions.Transfer;
import task_2.entity.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private final static int ACCOUNT_QUANTITY = 5;
    private final static int THREADS = 5;
    private final static int TRANSACTION_AMOUNT = 1000;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        CreateAndWriteAccToFile create = new CreateAndWriteAccToFile();
        ReadAccToList listAcc = new ReadAccToList();
        create.deleteFiles();

        for (int i = 0; i < ACCOUNT_QUANTITY; i++) {
            create.writeAccListToFile(create.createAccount());
        }

        logger.info("All balance before transaction = " + create.getAllBalance());

        ArrayList<Account> list = listAcc.createListAcc(listAcc.listPath());

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS; i++) {
            es.execute(new Transaction(list, TRANSACTION_AMOUNT));
        }
        es.awaitTermination(5, TimeUnit.SECONDS);
        es.shutdown();

        for (int i = 0; i < list.size(); i++) {
            create.writeAccListToFile(list.get(i));
        }

        logger.info("All balance after transaction = " + create.getAllBalance() / 2 + " transaction amount = " + Transfer.getAtomicInteger().intValue());
    }
}
