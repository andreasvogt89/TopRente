package prototype;
import java.sql.*;


public class DatabaseManager {

    public static final String DATABASE_NAME = "Custumer_Database";
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\andre\\IdeaProjects\\TopRente\\src\\" + DATABASE_NAME;
    public static final String TABLES_CUSTUMER = "Custumer_Table";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_ANUALSALARY  = "Anual_Salary";
    public static final String COLUMN_BIRTHDATE  = "Birthdate";
    public static final String COLUMN_EMPLOYMENDLEVEL  = "Employmend_Level";
    public static final String COLUMN_CREDIT  = "Credit";
    private static Connection connection;
    private static Statement statement;



    public DatabaseManager (){
        connection();


    }

    public static void connection (){
        String url = CONNECTION;
        try {
            Connection connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " +
                    TABLES_CUSTUMER + "(" +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_ANUALSALARY + " INTEGER, " +
                    COLUMN_BIRTHDATE + " DATE, " +
                    COLUMN_EMPLOYMENDLEVEL + " TEXT, " +
                    COLUMN_CREDIT + " INTEGER " + ")"
                    );
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void create (Statement statement, ContractPerson contractPerson)throws SQLException {
            System.out.println(contractPerson);
            statement.executeUpdate("INSERT INTO " + TABLES_CUSTUMER +
                    " VALUES " + "('" + contractPerson.getName() + "', " +
                    "'" + contractPerson.getAnnualSalary() + "', " +
                    "'" + contractPerson.getBirthdate() + "', " +
                    "'" + contractPerson.getEmploymentLevel() + "', " +
                    "'" + contractPerson.getCredit() +"');");
    }
    public static void update (){}
    public static void read (){}
    public static void delet (){}


    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
