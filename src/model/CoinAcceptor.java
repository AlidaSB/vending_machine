package model;

public class CoinAcceptor implements PaymentAcceptor {
    private int balance;

    public CoinAcceptor() {
        this.balance = 0;
    }

    @Override
    public void addFunds(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        balance += amount;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void deductFunds(int amount) throws IllegalArgumentException {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }
}

