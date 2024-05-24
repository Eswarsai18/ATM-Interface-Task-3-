import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void transfer(double amount, String recipientId) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred: $" + amount + " to " + recipientId);
            System.out.println("Transferred: $" + amount + " to " + recipientId);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

public class ATMInterface {
    private BankAccount account;

    public ATMInterface() {
        this.account = new BankAccount("user123", "pin123");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter user PIN: ");
        String userPin = scanner.nextLine();

        if (account.authenticate(userId, userPin)) {
            boolean quit = false;
            while (!quit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        account.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        account.withdraw(amount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        amount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        account.deposit(amount);
                        break;
                    case 4:
                        System.out.print("Enter amount to transfer: ");
                        amount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter recipient user ID: ");
                        String recipientId = scanner.nextLine();
                        account.transfer(amount, recipientId);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ATMInterface atm = new ATMInterface();
        atm.run();
    }
}