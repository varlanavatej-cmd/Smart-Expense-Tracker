public class Budget {

    private double monthlyLimit;

    public Budget() {
        this.monthlyLimit = 0;
    }

    public void setMonthlyLimit(
            double monthlyLimit
    ) throws InvalidAmountException {

        if (monthlyLimit <= 0) {

            throw new InvalidAmountException(
                    "Budget must be greater than zero."
            );
        }

        this.monthlyLimit = monthlyLimit;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public boolean isBudgetSet() {
        return monthlyLimit > 0;
    }

    public double calculateRemainingBudget(
            double totalExpenses
    ) {

        return monthlyLimit - totalExpenses;
    }

    public boolean isBudgetExceeded(
            double totalExpenses
    ) {

        return totalExpenses > monthlyLimit;
    }
}