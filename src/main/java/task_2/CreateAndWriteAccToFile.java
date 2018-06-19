package task_2;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateAndWriteAccToFile {

    private static Random random = new Random();

    private int AllBalance = 0;

    public int getAllBalance() {
        return AllBalance;
    }
//    private AtomicInteger atomicInteger = new AtomicInteger(0);
//    private final static int ACCOUNTS_COUNT = 10;

//    @Override
//    public void run() {
//        while (!isInterrupted())
//        if (atomicInteger.intValue() < ACCOUNTS_COUNT) {
//            try {
//                writeAccListToFile(createAccount());
//                atomicInteger.incrementAndGet();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else interrupt();
//    }

//    @Override
//    public void run() {
//        while (!isInterrupted())
//            if (getAtomicInteger().intValue() < ACCOUNTS_COUNT) {
//                accToJson(createAccount());
//                atomicInteger.incrementAndGet();
//            } else interrupt();
//    }

    public Account createAccount() {
        return new Account(random.nextInt(100) + 1,
                random.nextInt(4000) + 1000);
    }

//    public void writeAccListToFile(Account account) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(
//                new FileWriter("src\\main\\resources\\accounts\\" + account.getId() + ".txt"))) {
//            writer.write(account.toString());
//        }
//    }

    public void accToJson(Account account) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("src\\main\\resources\\accounts\\" + account.getId() + ".txt")) {
            AllBalance += account.getBalance();
            gson.toJson(account, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public AtomicInteger getAtomicInteger() {
//        return atomicInteger;
//    }

}
