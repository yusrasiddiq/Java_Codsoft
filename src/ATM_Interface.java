import java.util.Scanner;

// BankAccount Class: Represents the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw an amount
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true; // Successful withdrawal
        } else {
            return false; // Insufficient funds
        }
    }
}

// ATM Class: Represents the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to link the ATM with a specific bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the menu
    public void displayMenu() {
        System.out.println("\n****** Welcome to the ATM ******");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to process user transactions
    public void processTransaction() {
        Scanner sc = new Scanner(System.in);
        int choice;
        double amount;

        while (true) {
            displayMenu(); // Display the ATM menu
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: // Check balance
                    System.out.println("Your current balance is: Rs. " + account.getBalance());
                    break;

                case 2: // Deposit money
                    System.out.print("Enter the deposit amount (Rs.): ");
                    amount = sc.nextDouble();
                    if (amount > 0) {
                        account.deposit(amount);
                        System.out.println("Deposit successful! Your new balance is: Rs. " + account.getBalance());
                    } else {
                        System.out.println("Invalid deposit amount. Please enter a positive value.");
                    }
                    break;

                case 3: // Withdraw money
                    System.out.print("Enter the withdrawal amount (Rs.): ");
                    amount = sc.nextDouble();
                    if (amount > 0 && account.withdraw(amount)) {
                        System.out.println("Withdrawal successful! Your remaining balance is: Rs. " + account.getBalance());
                    } else if (amount <= 0) {
                        System.out.println("Invalid withdrawal amount. Please enter a positive value.");
                    } else {
                        System.out.println("Insufficient balance. Please try a smaller amount.");
                    }
                    break;

                case 4: // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;

                default: // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Main Class: ATM_Interface
public class ATM_Interface {
    public static void main(String[] args) {
        // Create a new bank account with an initial balance
        BankAccount userAccount = new BankAccount(100000);

        // Create an ATM linked to the user's bank account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.processTransaction();
    }
}
