package task_2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private final static int ACCOUNTS_COUNT = 5;
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, IOException {

//
//        Account account1 = new Account(1,1000);
//        Account account2 = new Account(2,2000);

//        Transfer transfer1 = new Transfer(account1, account2, 500);
//        Transfer transfer2 = new Transfer(account2, account1, 300);
//        Transfer transfer3 = new Transfer(account1, account2, 300);
//        Transfer transfer4 = new Transfer(account2, account1, 500);

//        transfer1.start();
//        transfer2.start();
//        transfer3.start();
//        transfer4.start();

        CreateAndWriteAccToFile create = new CreateAndWriteAccToFile();
        WriteAccToList listAcc = new WriteAccToList();

        for (int i = 0; i < ACCOUNTS_COUNT; i++) {
            create.accToJson(create.createAccount());
        }

        System.out.println("All balance before transaction = " + create.getAllBalance());


//        Thread.sleep(1000);

        ArrayList<Account> list = listAcc.createListAcc(listAcc.listPath());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (int i = 0; i < 100; i++) {
            new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), random.nextInt(300) + 1).start();
        }


//        Transfer transfer1 = new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), 500);
//        Transfer transfer2 = new Transfer(list.get(random.nextInt(list.size())), list.get(random.nextInt(list.size())), 300);
//
//        transfer1.start();
//        transfer2.start();

        Thread.sleep(5000);

        int count = 0;

        for (int i = 0; i < ACCOUNTS_COUNT; i++) {
            create.accToJson(list.get(i));
            count += list.get(i).getBalance();
        }

        System.out.println("All balance after transaction = " + count);
    }
}
