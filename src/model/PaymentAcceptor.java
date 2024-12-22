package model;

public interface PaymentAcceptor {
    void addFunds(int amount);
    int getBalance();
    void deductFunds(int amount) throws IllegalArgumentException;
}
