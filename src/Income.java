import java.time.LocalDate;

public class Income extends Transaction {

    private String source;

    public Income(
            int id,
            double amount,
            LocalDate date,
            String description,
            String source
    ) {
        super(id, amount, date, description);

        this.source = source;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String getType() {
        return "Income";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Source: " + source;
    }
}