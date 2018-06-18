package task_2;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private int balance;

    private ReentrantLock lock = new ReentrantLock();

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
    
    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

}
