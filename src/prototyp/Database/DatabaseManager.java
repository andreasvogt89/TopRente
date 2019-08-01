package Database;
import contractPerson.ContractPerson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import java.sql.*;


public class DatabaseManager {

    private static final String DATABASE_NAME = "Custumer_Database";
    private static final String TABLES_CUSTUMER = "Custumer_Table";
    private static final String COLUMN_LASTNAME = "Nachname";
    private static final String COLUMN_NAME = "Vorname";
    private static final String COLUMN_ANUALSALARY = "Jahreslohn";
    private static final String COLUMN_BIRTHDATE = "Geburtsdatum";
    private static final String COLUMN_EMPLOYMENDLEVEL = "Beschäftigungsgrad";
    private static final String COLUMN_CREDIT = "Alterguthaben";
    private static Statement statement;
    private static Connection connection;

    public static void connect(String databaseTyp, String databaseURL) {
        connection = null;
        if (databaseTyp == null) {
            new Alert(Alert.AlertType.ERROR, "Datenbank auswählen.").showAndWait();
        } else if (databaseURL.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "URL eintragen.").showAndWait();
        } else {

            String url = databaseURL + DATABASE_NAME;
            System.out.println(url);

            try {
                Connection connection = DriverManager.getConnection(url);
                createDatabase(connection);
                DatabaseManager.connection = connection;
                System.out.println("Successfully connection to the database");
            } catch (SQLException connectionException) {
                System.err.println(connectionException.toString());
                new Alert(Alert.AlertType.ERROR, "Datenbank konnte nicht Verbunden werden.\nBitte Prüfen Sie den URL Pfad.").showAndWait();
            }

        }
    }

    public ObservableList<ContractPerson> loadPersons(Statement statement, ObservableList<ContractPerson> list) throws SQLException {
        list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM " + TABLES_CUSTUMER;
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String lastname = resultSet.getString(1);
            String name = resultSet.getString(2);
            String birthday = resultSet.getString(4);
            Integer salary = resultSet.getInt(3);
            String level = resultSet.getString(5);
            Integer credit = resultSet.getInt(6);
            list.add(new ContractPerson(lastname,name,birthday,salary,level,credit));

        }
        statement.close();
        return list;
    }

    public void exit() {
        new Alert(Alert.AlertType.CONFIRMATION, "Wollen sie die Verbindung\nzu " + DATABASE_NAME +" wirklich trennen? ").showAndWait();
         connection = null;
         System.out.println("Database disconnected");
    }

    static void createDatabase(Connection connection){
            try {
                statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS " +
                        TABLES_CUSTUMER + "(" +
                        COLUMN_LASTNAME + " VARCHAR, " +
                        COLUMN_NAME + " VARCHAR, " +
                        COLUMN_ANUALSALARY + " INTEGER, " +
                        COLUMN_BIRTHDATE + " VARCHAR, " +
                        COLUMN_EMPLOYMENDLEVEL + " VARCHAR, " +
                        COLUMN_CREDIT + " INTEGER " + ")"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void createPerson (Statement statement, ContractPerson contractPerson)throws SQLException {
           String sqlLocalStatemant =  "INSERT INTO " + TABLES_CUSTUMER +
                   " VALUES " + "('" + contractPerson.getLastname() + "', " +
                   "'" + contractPerson.getName() + "', " +
                   "'" + contractPerson.getSalary() + "', " +
                   "'" + contractPerson.getBirthday() + "', " +
                   "'" + contractPerson.getLevel() + "', " +
                   "'" + contractPerson.getCredit() + "');";
        statement.execute(sqlLocalStatemant);
        System.out.println(sqlLocalStatemant);

        }

    public Statement getStatement () {
            return statement;
        }

    public Connection getConnection (){return connection;}

    public boolean isDatabaseConnected() {
        if (connection == null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Bitte erstellen Sie erst\neine Verbindung zu einer Datenbank").showAndWait();
            System.out.println("no database connected");
            return false;
        }else return true;
    }
}
