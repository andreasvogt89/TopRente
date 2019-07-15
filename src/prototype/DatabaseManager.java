package prototype;
import javafx.scene.control.Alert;

import java.sql.*;


public class DatabaseManager {

    public static final String DATABASE_NAME = "Custumer_Database";
    //public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\admin\\IdeaProjects\\TopRente\\src\\" + DATABASE_NAME;
    public static final String TABLES_CUSTUMER = "Custumer_Table";
    public static final String COLUMN_LASTNAME = "Name";
    public static final String COLUMN_NAME = "Vorname";
    public static final String COLUMN_ANUALSALARY = "Anual_Salary";
    public static final String COLUMN_BIRTHDATE = "Birthdate";
    public static final String COLUMN_EMPLOYMENDLEVEL = "Employmend_Level";
    public static final String COLUMN_CREDIT = "Credit";
    private static Statement statement;




        public static void connect(String databaseTyp, String databaseURL) {


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
                System.out.println("Successfully connected to the database");
            } catch (SQLException e) {
                System.err.println(e.toString());
            }
        }

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
        public static void update () {
        }
        public static void read () {
        }
        public static void deletePerson (Statement statement, ContractPerson contractPerson)throws SQLException {

        }


        public Statement getStatement () {
            return statement;
        }

}
