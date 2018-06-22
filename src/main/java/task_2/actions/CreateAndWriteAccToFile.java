package task_2.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_2.entity.Account;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class CreateAndWriteAccToFile {

    private static Logger logger = LoggerFactory.getLogger(CreateAndWriteAccToFile.class);
    private static Random random = new Random();
    private static final String ACCOUNT_PATH = "src\\main\\resources\\accounts";

    private int AllBalance = 0;

    public int getAllBalance() {
        return AllBalance;
    }

    public Account createAccount() {
        return new Account(random.nextInt(100) + 1,
                random.nextInt(5000) + 3000);
    }

    public void writeAccListToFile(Account account) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(ACCOUNT_PATH + "\\" + account.getId() + ".bin")));
        AllBalance += account.getBalance();
        out.writeObject(account);
        out.flush();
        out.close();
        logger.info("Account id = " + account.getId() + " balance = " + account.getBalance());
    }

    public void deleteFiles() throws IOException {
        logger.info("Called deleteFiles");
        Files.walk(Paths.get(ACCOUNT_PATH))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }

}
