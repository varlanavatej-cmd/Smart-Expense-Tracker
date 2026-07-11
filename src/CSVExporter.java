import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public static void exportTransactions(
            List<Transaction> transactions,
            String fileName
    ) throws IOException {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(fileName)
                        )
        ) {

            writer.write(
                    "ID,Type,Amount,Date,"
                            + "Description,Source/Category"
            );

            writer.newLine();

            for (Transaction transaction
                    : transactions) {

                String extraInformation;

                if (transaction instanceof Income) {

                    Income income =
                            (Income) transaction;

                    extraInformation =
                            income.getSource();

                } else {

                    Expense expense =
                            (Expense) transaction;

                    extraInformation =
                            expense
                                    .getCategory()
                                    .toString();
                }

                writer.write(
                        transaction.getId()
                                + ","
                                + transaction.getType()
                                + ","
                                + transaction.getAmount()
                                + ","
                                + transaction.getDate()
                                + ","
                                + formatForCSV(
                                        transaction
                                                .getDescription()
                                )
                                + ","
                                + formatForCSV(
                                        extraInformation
                                )
                );

                writer.newLine();
            }
        }
    }

    private static String formatForCSV(
            String value
    ) {

        String escapedValue =
                value.replace(
                        "\"",
                        "\"\""
                );

        return "\""
                + escapedValue
                + "\"";
    }
}