package model;

import java.util.Scanner;

public class CardAcceptor implements PaymentAcceptor {
    private int balance;

    public CardAcceptor() {
        this.balance = 0;
    }

    @Override
    public void addFunds(int amount) {
        System.out.println("Enter card number:");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Payment accepted. Added " + amount);
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

