import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ExpenseTracker tracker =
                new ExpenseTracker();
        Budget budget =
                new Budget();

        int nextTransactionId = 1;

        boolean running = true;

        while (running) {

            displayMenu();

            try {

                System.out.print(
                        "\nEnter your choice: "
                );

                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {

                    case 1:

                        addIncome(
                                scanner,
                                tracker,
                                nextTransactionId
                        );

                        nextTransactionId++;

                        break;

                    case 2:

                        addExpense(
                                scanner,
                                tracker,
                                nextTransactionId
                        );

                        nextTransactionId++;

                        break;

                    case 3:

                        tracker
                                .displayAllTransactions();

                        break;

                    case 4:

                        System.out.printf(
                                "%nTotal Income: ₹%.2f%n",
                                tracker
                                        .calculateTotalIncome()
                        );

                        break;

                    case 5:

                        System.out.printf(
                                "%nTotal Expenses: ₹%.2f%n",
                                tracker
                                        .calculateTotalExpenses()
                        );

                        break;

                    case 6:

                        System.out.printf(
                                "%nCurrent Balance: ₹%.2f%n",
                                tracker
                                        .calculateBalance()
                        );

                        break;

                    case 7:

                        viewExpensesByCategory(
                                scanner,
                                tracker
                        );

                        break;

                        case 8:

                                setMonthlyBudget(
                                        scanner,
                                        budget
                                );

                                break;

                            case 9:

                                displayBudgetSummary(
                                        budget,
                                        tracker
                                );

                                break;

                          case 10:

    tracker
            .displayTransactionsSortedByAmount();

    break;

case 11:

    searchTransactions(
            scanner,
            tracker
    );

    break;

case 12:

    tracker
            .displayHighestExpense();

    break;

case 13:

    tracker
            .displayExpenseStatistics();

    break;

case 14:

    exportTransactions(
            tracker
    );

    break;

case 15:

    running = false;

    System.out.println(
            "\nThank you for using "
                    + "Smart Expense Tracker!"
    );

    break;

                    default:

                        System.out.println(
                                "\nInvalid choice. "
                                        + "Enter a number "
                                        + "from 1 to 15."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "\nInvalid input. "
                                + "Please enter numbers only."
                );

                scanner.nextLine();

            } catch (InvalidAmountException e) {

                System.out.println(
                        "\nTransaction failed: "
                                + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    public static void displayMenu() {

        System.out.println(
                "\n========== SMART EXPENSE TRACKER =========="
        );

        System.out.println("1. Add Income");

        System.out.println("2. Add Expense");

        System.out.println(
                "3. View All Transactions"
        );

        System.out.println(
                "4. View Total Income"
        );

        System.out.println(
                "5. View Total Expenses"
        );

        System.out.println(
                "6. View Current Balance"
        );

            System.out.println(
                "7. View Expenses by Category"
        );

        System.out.println(
                "8. Set Monthly Budget"
        );

                System.out.println(
                    "9. View Budget Summary"
            );

            System.out.println(
                    "10. Sort Transactions by Amount"
            );

            System.out.println(
                    "11. Search Transactions"
            );

            System.out.println(
                    "12. View Highest Expense"
            );

           System.out.println(
        "13. View Expense Statistics"
);

System.out.println(
        "14. Export Transactions to CSV"
);

System.out.println("15. Exit");
                        }


    public static void addIncome(
            Scanner scanner,
            ExpenseTracker tracker,
            int transactionId
    ) throws InvalidAmountException {

        System.out.print(
                "Enter income amount: ₹"
        );

        double amount =
                scanner.nextDouble();

        scanner.nextLine();

        System.out.print(
                "Enter income description: "
        );

        String description =
                scanner.nextLine();

        System.out.print(
                "Enter income source: "
        );

        String source =
                scanner.nextLine();

        Income income = new Income(
                transactionId,
                amount,
                LocalDate.now(),
                description,
                source
        );

        tracker.addTransaction(income);
    }

    public static void addExpense(
            Scanner scanner,
            ExpenseTracker tracker,
            int transactionId
    ) throws InvalidAmountException {

        System.out.print(
                "Enter expense amount: ₹"
        );

        double amount =
                scanner.nextDouble();

        scanner.nextLine();

        System.out.print(
                "Enter expense description: "
        );

        String description =
                scanner.nextLine();

        Category category =
                selectCategory(scanner);

        Expense expense = new Expense(
                transactionId,
                amount,
                LocalDate.now(),
                description,
                category
        );

        tracker.addTransaction(expense);
    }

    public static Category selectCategory(
            Scanner scanner
    ) {

        System.out.println(
                "\n===== EXPENSE CATEGORIES ====="
        );

        System.out.println("1. Food");
        System.out.println("2. Travel");
        System.out.println("3. Shopping");
        System.out.println("4. Education");
        System.out.println("5. Health");
        System.out.println("6. Entertainment");
        System.out.println("7. Bills");
        System.out.println("8. Other");

        System.out.print(
                "Select a category: "
        );

        int categoryChoice =
                scanner.nextInt();

        scanner.nextLine();

        switch (categoryChoice) {

            case 1:

                return Category.FOOD;

            case 2:

                return Category.TRAVEL;

            case 3:

                return Category.SHOPPING;

            case 4:

                return Category.EDUCATION;

            case 5:

                return Category.HEALTH;

            case 6:

                return Category.ENTERTAINMENT;

            case 7:

                return Category.BILLS;

            case 8:

                return Category.OTHER;

            default:

                System.out.println(
                        "Invalid category. "
                                + "OTHER was selected."
                );

                return Category.OTHER;
        }
    }

    public static void viewExpensesByCategory(
            Scanner scanner,
            ExpenseTracker tracker
    ) {

        Category selectedCategory =
                selectCategory(scanner);

        tracker.displayExpensesByCategory(
                selectedCategory
        );

        double categoryTotal =
                tracker.calculateExpenseByCategory(
                        selectedCategory
                );

        System.out.printf(
                "%nTotal %s Expenses: ₹%.2f%n",
                selectedCategory,
                categoryTotal
        );
    }
    public static void setMonthlyBudget(
        Scanner scanner,
        Budget budget
) throws InvalidAmountException {

    System.out.print(
            "\nEnter your monthly budget: ₹"
    );

    double monthlyLimit =
            scanner.nextDouble();

    scanner.nextLine();

    budget.setMonthlyLimit(
            monthlyLimit
    );

    System.out.printf(
            "%nMonthly budget set to ₹%.2f%n",
            budget.getMonthlyLimit()
    );
}
public static void displayBudgetSummary(
        Budget budget,
        ExpenseTracker tracker
) {

    if (!budget.isBudgetSet()) {

        System.out.println(
                "\nNo monthly budget has been set."
        );

        System.out.println(
                "Select option 8 to set a budget."
        );

        return;
    }

    double monthlyBudget =
            budget.getMonthlyLimit();

    double totalExpenses =
            tracker.calculateTotalExpenses();

    double remainingBudget =
            budget.calculateRemainingBudget(
                    totalExpenses
            );

    System.out.println(
            "\n===== MONTHLY BUDGET SUMMARY ====="
    );

    System.out.printf(
            "Monthly Budget: ₹%.2f%n",
            monthlyBudget
    );

    System.out.printf(
            "Total Expenses: ₹%.2f%n",
            totalExpenses
    );

    if (
            budget.isBudgetExceeded(
                    totalExpenses
            )
    ) {

        System.out.printf(
                "Budget Exceeded By: ₹%.2f%n",
                Math.abs(remainingBudget)
        );

        System.out.println(
                "Warning: You have exceeded "
                        + "your monthly budget!"
        );

    } else {

        System.out.printf(
                "Remaining Budget: ₹%.2f%n",
                remainingBudget
        );

        double usedPercentage =
                (totalExpenses / monthlyBudget)
                        * 100;

        System.out.printf(
                "Budget Used: %.2f%%%n",
                usedPercentage
        );
    }
}
public static void searchTransactions(
        Scanner scanner,
        ExpenseTracker tracker
) {

    System.out.print(
            "\nEnter a keyword to search: "
    );

    String keyword =
            scanner.nextLine();

    tracker.searchTransactions(
            keyword
    );
}
public static void exportTransactions(
        ExpenseTracker tracker
) {

    if (
            tracker
                    .getTransactions()
                    .isEmpty()
    ) {

        System.out.println(
                "\nNo transactions are "
                        + "available to export."
        );

        return;
    }

    String fileName =
            "expense_report.csv";

    try {

        CSVExporter.exportTransactions(
                tracker.getTransactions(),
                fileName
        );

        System.out.println(
                "\nTransactions exported "
                        + "successfully."
        );

        System.out.println(
                "File name: "
                        + fileName
        );

    } catch (IOException e) {

        System.out.println(
                "\nFailed to export transactions."
        );

        System.out.println(
                "Reason: "
                        + e.getMessage()
        );
    }
}
}