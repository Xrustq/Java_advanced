package task_2;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class WriteAccToList {

    private static final String ACCOUNT_PATH = "src\\main\\resources\\accounts";

    public Account writeAccToList(String file) {
        Gson gson = new Gson();
        Account account = new Account();

        try (Reader reader = new FileReader(file)) {
            // Convert JSON to Java Object
            account = gson.fromJson(reader, Account.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    public ArrayList<String> listPath() {
        File folder = new File(ACCOUNT_PATH);
        File[] files = folder.listFiles();
        ArrayList<String> list = new ArrayList<>();
        for (File path : files) {
            list.add(path.toString());
        }
        return list;
    }

    public ArrayList<Account> createListAcc(ArrayList<String> listPath) {
        ArrayList<Account> listAcc = new ArrayList<>();
        for (String aListPath : listPath) {
            listAcc.add(writeAccToList(aListPath));
        }
        return listAcc;
    }
}
