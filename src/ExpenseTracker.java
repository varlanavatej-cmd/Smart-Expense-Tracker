import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ExpenseTracker {

    private List<Transaction> transactions;

    public ExpenseTracker() {
        transactions = new ArrayList<>();
    }

  public void addTransaction(
        Transaction transaction
) throws InvalidAmountException {

    if (transaction.getAmount() <= 0) {

        throw new InvalidAmountException(
                "Amount must be greater than zero."
        );
    }

    transactions.add(transaction);

    System.out.println(
            "Transaction added successfully."
    );
}

    public void displayAllTransactions() {

        if (transactions.isEmpty()) {
            System.out.println(
                    "No transactions are available."
            );

            return;
        }

        System.out.println(
                "\n===== ALL TRANSACTIONS ====="
        );

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public double calculateTotalIncome() {

        double totalIncome = 0;

        for (Transaction transaction : transactions) {

            if (transaction instanceof Income) {
                totalIncome += transaction.getAmount();
            }
        }

        return totalIncome;
    }

    public double calculateTotalExpenses() {

        double totalExpenses = 0;

        for (Transaction transaction : transactions) {

            if (transaction instanceof Expense) {
                totalExpenses += transaction.getAmount();
            }
        }

        return totalExpenses;
    }

    public double calculateBalance() {

        return calculateTotalIncome()
                - calculateTotalExpenses();
    }
    public void displayExpensesByCategory(
        Category selectedCategory
) {

    boolean expenseFound = false;

    System.out.println(
            "\n===== "
                    + selectedCategory
                    + " EXPENSES ====="
    );

    for (Transaction transaction : transactions) {

        if (transaction instanceof Expense) {

            Expense expense =
                    (Expense) transaction;

            if (
                    expense.getCategory()
                            == selectedCategory
            ) {

                System.out.println(expense);

                expenseFound = true;
            }
        }
    }

    if (!expenseFound) {

        System.out.println(
                "No expenses found "
                        + "in this category."
        );
    }
}

public double calculateExpenseByCategory(
        Category selectedCategory
) {

    double categoryTotal = 0;

    for (Transaction transaction : transactions) {

        if (transaction instanceof Expense) {

            Expense expense =
                    (Expense) transaction;

            if (
                    expense.getCategory()
                            == selectedCategory
            ) {

                categoryTotal +=
                        expense.getAmount();
            }
        }
    }

    return categoryTotal;
}
 public void displayTransactionsSortedByAmount() {

    if (transactions.isEmpty()) {

        System.out.println(
                "\nNo transactions are available."
        );

        return;
    }

    List<Transaction> sortedTransactions =
            transactions.stream()
                    .sorted(
                            Comparator.comparingDouble(
                                    Transaction::getAmount
                            ).reversed()
                    )
                    .collect(
                            Collectors.toList()
                    );

    System.out.println(
            "\n===== TRANSACTIONS: HIGHEST TO LOWEST ====="
    );

    sortedTransactions.forEach(
            System.out::println
    );
}
public void searchTransactions(
        String keyword
) {

    List<Transaction> matchingTransactions =
            transactions.stream()
                    .filter(
                            transaction ->
                                    transaction
                                            .getDescription()
                                            .toLowerCase()
                                            .contains(
                                                    keyword
                                                            .toLowerCase()
                                            )
                    )
                    .collect(
                            Collectors.toList()
                    );

    if (matchingTransactions.isEmpty()) {

        System.out.println(
                "\nNo transactions found "
                        + "for: " + keyword
        );

        return;
    }

    System.out.println(
            "\n===== SEARCH RESULTS ====="
    );

    matchingTransactions.forEach(
            System.out::println
    );
}
public void displayHighestExpense() {

    Expense highestExpense =
            transactions.stream()
                    .filter(
                            transaction ->
                                    transaction
                                            instanceof Expense
                    )
                    .map(
                            transaction ->
                                    (Expense) transaction
                    )
                    .max(
                            Comparator.comparingDouble(
                                    Expense::getAmount
                            )
                    )
                    .orElse(null);

    if (highestExpense == null) {

        System.out.println(
                "\nNo expenses are available."
        );

        return;
    }

    System.out.println(
            "\n===== HIGHEST EXPENSE ====="
    );

    System.out.println(
            highestExpense
    );
}
public void displayExpenseStatistics() {

    List<Expense> expenses =
            transactions.stream()
                    .filter(
                            transaction ->
                                    transaction
                                            instanceof Expense
                    )
                    .map(
                            transaction ->
                                    (Expense) transaction
                    )
                    .collect(
                            Collectors.toList()
                    );

    if (expenses.isEmpty()) {

        System.out.println(
                "\nNo expenses are available."
        );

        return;
    }

    double totalExpenses =
            expenses.stream()
                    .mapToDouble(
                            Expense::getAmount
                    )
                    .sum();

    double averageExpense =
            expenses.stream()
                    .mapToDouble(
                            Expense::getAmount
                    )
                    .average()
                    .orElse(0);

    double highestExpense =
            expenses.stream()
                    .mapToDouble(
                            Expense::getAmount
                    )
                    .max()
                    .orElse(0);

    double lowestExpense =
            expenses.stream()
                    .mapToDouble(
                            Expense::getAmount
                    )
                    .min()
                    .orElse(0);

    System.out.println(
            "\n===== EXPENSE STATISTICS ====="
    );

    System.out.println(
            "Number of Expenses: "
                    + expenses.size()
    );

    System.out.printf(
            "Total Expenses: ₹%.2f%n",
            totalExpenses
    );

    System.out.printf(
            "Average Expense: ₹%.2f%n",
            averageExpense
    );

    System.out.printf(
            "Highest Expense: ₹%.2f%n",
            highestExpense
    );

    System.out.printf(
            "Lowest Expense: ₹%.2f%n",
            lowestExpense
    );
}
public List<Transaction> getTransactions() {

    return new ArrayList<>(
            transactions
    );
}
}