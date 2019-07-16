package prototype;
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
    private static Connection connection;

    static void connect(String databaseTyp, String databaseURL) {
        connection = null;
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
                DatabaseManager.connection = connection;
                System.out.println("Successfully connection to the database");
            } catch (SQLException connectionException) {
                System.err.println(connectionException.toString());
                new Alert(Alert.AlertType.ERROR, "Datenbank konnte nicht Verbunden werden. Bitte Prüfen Sie den URL Pfad.").showAndWait();
            }

        }
    }

    ObservableList<ContractPerson> loadPersons(Statement statement, ObservableList<ContractPerson> list) throws SQLException {
        list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM " + TABLES_CUSTUMER;
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String lastname = resultSet.getString(1);
            String name = resultSet.getString(2);
            Date birthday = resultSet.getDate(3);
            Integer salary = resultSet.getInt(4);
            String level = resultSet.getString(5);
            Integer credit = resultSet.getInt(6);
            list.add(new ContractPerson(lastname,name,birthday,salary,level,credit));

        }
        statement.close();
        return list;
    }

    void exit() {
        new Alert(Alert.AlertType.CONFIRMATION, "Wollen sie die Verbindung zu " + DATABASE_NAME +" wirklich trennen? ").showAndWait();
         connection = null;
         System.out.println("Database disconnected");
    }



    static void createDatabase(Connection connection){
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

    static void createPerson (Statement statement, ContractPerson contractPerson)throws SQLException {
            statement.execute("INSERT INTO " + TABLES_CUSTUMER +
                    " VALUES " + "('" + contractPerson.getLastname() + "', " +
                    "'" + contractPerson.getName() + "', " +
                    "'" + contractPerson.getSalary() + "', " +
                    "'" + contractPerson.getBirthday() + "', " +
                    "'" + contractPerson.getLevel() + "', " +
                    "'" + contractPerson.getCredit() + "');");
        }

    Statement getStatement () {
            return statement;
        }

    Connection getConnection (){return connection;}

    void isDatabaseConnected (){
        if (connection == null)
            new Alert(Alert.AlertType.CONFIRMATION, "Bitte erstellen Sie eine Verbindung zu einer Datenbank").showAndWait();
        System.out.println("no database connected");
    }

}
