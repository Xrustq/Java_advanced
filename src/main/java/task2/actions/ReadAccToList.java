package task2.actions;


import task2.entity.Account;

import java.io.*;
import java.util.ArrayList;

public class ReadAccToList {

    private static final String ACCOUNT_PATH = "src\\main\\resources\\accounts";

    public ArrayList<String> listPath() {
        File folder = new File(ACCOUNT_PATH);
        File[] files = folder.listFiles();
        ArrayList<String> list = new ArrayList<>();
        for (File path : files) {
            list.add(path.toString());
        }
        return list;
    }

    public ArrayList<Account> createListAcc(ArrayList<String> listPath) throws IOException, ClassNotFoundException {
        ArrayList<Account> listAcc = new ArrayList<>();
        for (String aListPath : listPath) {
            listAcc.add(writeAccToList(aListPath));
        }
        return listAcc;
    }

    public Account writeAccToList(String file) throws IOException, ClassNotFoundException {

        Account account;

        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)));
        account = (Account) in.readObject();

        return account;
    }
}
