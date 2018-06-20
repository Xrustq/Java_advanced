package task_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;

public class CreateAndWriteAccToFile {

    private static Logger logger = LoggerFactory.getLogger(CreateAndWriteAccToFile.class);
    private static Random random = new Random();

    private int AllBalance = 0;

    public int getAllBalance() {
        return AllBalance;
    }

    public Account createAccount() {
        return new Account(random.nextInt(100) + 1,
                5000);
    }

    public void writeAccListToFile(Account account) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream("src\\main\\resources\\accounts\\" + account.getId() + ".bin")));
        AllBalance += account.getBalance();
        out.writeObject(account);
        out.flush();
        out.close();
        logger.info("Account id = " + account.getId() + " balance = " + account.getBalance());
    }

}
