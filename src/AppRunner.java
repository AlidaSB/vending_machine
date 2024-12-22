import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();
    private PaymentAcceptor paymentAcceptor;

    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        paymentAcceptor = new CoinAcceptor();
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("Select payment method:");
        print("1. Coins");
        print("2. Bank card");
        String choice = fromConsole();

        if ("1".equals(choice)) {
            paymentAcceptor = new CoinAcceptor();
        } else if ("2".equals(choice)) {
            paymentAcceptor = new CardAcceptor();
        } else {
            print("Wrong choice.");
            return;
        }

        while (!isExit) {
            simulateVendingMachine();
        }
    }

    private void simulateVendingMachine() {
        print("Available in the machine:");
        showProducts(products);

        print("Balance: " + paymentAcceptor.getBalance());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (paymentAcceptor.getBalance() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - Top up balance");
        showActions(products);
        print(" h - Exit");
        String action = fromConsole().substring(0, 1);
        if ("a".equalsIgnoreCase(action)) {
            inputAndAddFunds();
            return;
        }else if ("h".equalsIgnoreCase(action)) {
            print("Exiting...");
            isExit = true;
            return;
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    paymentAcceptor.deductFunds(products.get(i).getPrice());
                    print("You bought " + products.get(i).getName());
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
            } else {
                print("Invalid letter. Try again..");
                chooseAction(products);
            }
        }
    }

    private void inputAndAddFunds() {
        print("Enter the amount to top up your balance:");
        int amount = new Scanner(System.in).nextInt();  // Используем int для целочисленной суммы
        paymentAcceptor.addFunds(amount);
        print("You have topped up your balance by " + amount);
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}


