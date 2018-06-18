package task_2;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Account account1 = new Account(1000);
        Account account2 = new Account(2000);

        Transfer transfer1 = new Transfer(account1, account2, 500);
        Transfer transfer2 = new Transfer(account2, account1, 300);
        Transfer transfer3 = new Transfer(account1, account2, 300);
        Transfer transfer4 = new Transfer(account2, account1, 500);

        transfer1.start();
        transfer2.start();
        transfer3.start();
        transfer4.start();
    }
}
