package prototype;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import java.sql.*;
import java.util.Date;


public class DatabaseManager {

    public static final String DATABASE_NAME = "Custumer_Database";
    public static final String TABLES_CUSTUMER = "Custumer_Table";
    public static final String COLUMN_LASTNAME = "Nachname";
    public static final String COLUMN_NAME = "Vorname";
    public static final String COLUMN_ANUALSALARY = "Jahreslohn";
    public static final String COLUMN_BIRTHDATE = "Geburtsdatum";
    public static final String COLUMN_EMPLOYMENDLEVEL = "Beschäftigungsgrad";
    public static final String COLUMN_CREDIT = "Alterguthaben";
    private static Statement statement;
    private static Connection connected;

    public static void connect(String databaseTyp, String databaseURL) {
        connected = null;
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
                connected = connection;
                System.out.println("Successfully connected to the database");
            } catch (SQLException connectionException) {
                System.err.println(connectionException.toString());
                new Alert(Alert.AlertType.ERROR, "Datenbank konnte nicht Verbunden werden. Bitte Prüfen Sie den URL Pfad.").showAndWait();
            }

        }
    }

    public ObservableList<ContractPerson> loadPersons(ObservableList<ContractPerson> persons) throws SQLException {
        persons = FXCollections.observableArrayList();
        String sql = "SELECT * FROM " + TABLES_CUSTUMER;
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String lastName = resultSet.getString(1);
            String name = resultSet.getString(2);
            Date birthdate = resultSet.getDate(3);
            Integer annualSalary = resultSet.getInt(4);
            String employmentLevel = resultSet.getString(5);
            Integer credit = resultSet.getInt(6);
            persons.add(new ContractPerson(lastName,name,birthdate,annualSalary,employmentLevel,credit));

        }
        statement.close();
        return persons;
    }

    public void exit() {
        new Alert(Alert.AlertType.CONFIRMATION, "Wollen sie die Verbindung zu " + DATABASE_NAME +" wirklich trennen? ").showAndWait();
         connected = null;
         System.out.println("Database disconnected");
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
