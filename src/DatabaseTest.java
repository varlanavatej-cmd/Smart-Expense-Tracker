import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {

        try (
                Connection connection =
                        DatabaseConnection
                                .getConnection()
        ) {

            System.out.println(
                    "Database connected successfully!"
            );

            System.out.println(
                    "Connected database: "
                            + connection.getCatalog()
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed."
            );

            System.out.println(
                    "Reason: "
                            + e.getMessage()
            );
        }
    }
}