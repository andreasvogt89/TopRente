package prototype;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.*;


public class DatabaseManager {

    public static final String DATABASE_NAME = "Custumer_Database";
    public static final String TABLES_CUSTUMER = "Custumer_Table";
    public static final String COLUMN_LASTNAME = "Name";
    public static final String COLUMN_NAME = "Vorname";
    public static final String COLUMN_ANUALSALARY = "Anual Salary";
    public static final String COLUMN_BIRTHDATE = "Birthdate";
    public static final String COLUMN_EMPLOYMENDLEVEL = "Employmend Level";
    public static final String COLUMN_CREDIT = "Credit";
    private static Statement statement;
    private static Connection connectet;

    public static void connect(String databaseTyp, String databaseURL, TableView CustumerTable) {
        connectet = null;
        if (databaseTyp == null) {
            new Alert(Alert.AlertType.ERROR, "Database type must be selected.").showAndWait();
        } else if (databaseURL.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Database url must be specified.").showAndWait();
        } else {

            String url = databaseURL + DATABASE_NAME;
            System.out.println(url);

            try {
                Connection connection = DriverManager.getConnection(url);
                createDatabase(connection);
                connectet = connection;
                System.out.println("Successfully connected to the database");
            } catch (SQLException e) {
                System.err.println(e.toString());
            }
        }
        String tableQuery = "SELECT * FROM sqlite_master WHERE type='table' ORDER BY name";
        try (PreparedStatement tableQueryPS = connectet.prepareStatement(tableQuery)) {
            ResultSet tableNames = tableQueryPS.executeQuery();
            System.out.println("Table query successful.");

            while (tableNames.next()) {


                String columnQuery = "PRAGMA table_info(" + tableNames.getString("name") + ")";

                try (PreparedStatement columnQueryPS = connectet.prepareStatement(columnQuery)) {
                    ResultSet columnNames = columnQueryPS.executeQuery();

                    while (columnNames.next()) {
                        TableColumn column = new TableColumn(columnNames.getString("name"));
                        CustumerTable.getColumns().add(column);

                        String dataQuery = "SELECT " + columnNames.getString("name") + " FROM " + tableNames.getString("name");

                        try (PreparedStatement dataQueryPS = connectet.prepareStatement(dataQuery)) {
                            ResultSet columnData = dataQueryPS.executeQuery();

                            while (columnData.next()) {
                                TableRow row = new TableRow();
                                CustumerTable.getItems().add(row);
                                // populate the table!!!
                                // I am here!!!
                            }
                        } catch (SQLException dataQueryException) {
                            System.err.println(dataQueryException.toString());
                        }
                    }
                    System.out.println("Added TableColumns to the TableView");
                } catch (SQLException columnQueryException) {
                    System.err.println(columnQueryException.toString());
                }
            }
            System.out.println("Added Tabs to the TabPane");
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }

    }

    public void exit() {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?").showAndWait();
         connectet = null;
        Platform.exit();
    }

    public static void createDatabase(Connection connection){
            try {
                statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS " +
                        TABLES_CUSTUMER + "(" +
                        COLUMN_LASTNAME + " TEXT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_ANUALSALARY + " INTEGER, " +
                        COLUMN_BIRTHDATE + " DATE, " +
                        COLUMN_EMPLOYMENDLEVEL + " TEXT, " +
                        COLUMN_CREDIT + " INTEGER " + ")"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void createPerson (Statement statement, ContractPerson contractPerson)throws SQLException {
            statement.execute("INSERT INTO " + TABLES_CUSTUMER +
                    " VALUES " + "('" + contractPerson.getLastName() + "', " +
                    "'" + contractPerson.getName() + "', " +
                    "'" + contractPerson.getAnnualSalary() + "', " +
                    "'" + contractPerson.getBirthdate() + "', " +
                    "'" + contractPerson.getEmploymentLevel() + "', " +
                    "'" + contractPerson.getCredit() + "');");
        }

    public Statement getStatement () {
            return statement;
        }


}
