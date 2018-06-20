package task_2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private final static int ACCOUNT_COUNT = 5;
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        CreateAndWriteAccToFile create = new CreateAndWriteAccToFile();
        ReadAccToList listAcc = new ReadAccToList();

        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            create.writeAccListToFile(create.createAccount());
        }

        System.out.println("All balance before transaction = " + create.getAllBalance());

        ArrayList<Account> list = listAcc.createListAcc(listAcc.listPath());

//        new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1).start();
//        new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1).start();
//        new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1).start();

//       while (Transfer.getAtomicInteger().intValue() < 10) {
//            new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1).start();
//        }

        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1));
        }
//            es.awaitTermination(1, TimeUnit.SECONDS);
            es.shutdown();

        Thread.sleep(2000);

        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            create.writeAccListToFile(list.get(i));
        }

        System.out.println("All balance after transaction = " + create.getAllBalance() / 2 + "   " + Transfer.getAtomicInteger().intValue());
    }
}
