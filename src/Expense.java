import java.time.LocalDate;

public class Expense extends Transaction {

    private Category category;

    public Expense(
            int id,
            double amount,
            LocalDate date,
            String description,
            Category category
    ) {
        super(
                id,
                amount,
                date,
                description
        );

        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String getType() {
        return "Expense";
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Category: "
                + category;
    }
}