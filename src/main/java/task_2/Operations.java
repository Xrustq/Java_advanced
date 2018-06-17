package task_2;


public class Operations {

    public static void main(String[] args) throws InterruptedException {

        final Account account1 = new Account(1000);
        final Account account2 = new Account(2000);

        new Thread(() -> transfer(account1, account2, 500)).start();
        transfer(account2, account1, 300);
        Thread.sleep(1);
        System.out.println(account1.getBalance() + " " + account2.getBalance());

    }

    static void transfer(Account account1, Account account2, int amount) {
        if (account1.getBalance() < amount)
            throw new IllegalArgumentException("Недостаточно средств.");

        synchronized (account1) {
            synchronized (account2) {
                account1.withdraw(amount);
                account2.deposit(amount);
            }
        }
    }
}
