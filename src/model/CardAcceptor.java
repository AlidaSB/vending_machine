package model;

import java.util.Scanner;

public class CardAcceptor implements PaymentAcceptor {
    private int balance;

    public CardAcceptor() {
        this.balance = 0;
    }

    @Override
    public void addFunds(int amount) {
        System.out.println("Введите номер карты:");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();
        System.out.println("Введите одноразовый пароль:");
        String password = scanner.nextLine();
        System.out.println("Оплата принята. Добавлено " + amount);
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

