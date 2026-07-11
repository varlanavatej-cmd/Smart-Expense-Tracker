
import java.time.LocalDate;

public abstract class Transaction {

    private int id;
    private double amount;
    private LocalDate date;
    private String description;

    public Transaction(
            int id,
            double amount,
            LocalDate date,
            String description
    ) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "ID: " + id
                + " | Type: " + getType()
                + " | Amount: ₹" + amount
                + " | Date: " + date
                + " | Description: " + description;
    }
}

