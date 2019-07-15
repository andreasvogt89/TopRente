package prototype;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField inputLastName;
    @FXML
    private TextField inputName;
    @FXML
    private DatePicker inputBirthDate;
    @FXML
    private TextField inputSalary;
    @FXML
    private TextField inputCredit;
    @FXML
    private ChoiceBox inputLevel;
    @FXML
    public TextField databaseURL;
    @FXML
    public ComboBox<String> databaseTyp;
    @FXML
    public TableView<ContractPerson> CostumerTable;
    @FXML
    public Circle DBLamp;
    @FXML
    public Circle DBLamp1;
    @FXML
    private TableColumn<ContractPerson, String> name;
    @FXML
    private TableColumn<ContractPerson, String> lastname;
    @FXML
    private TableColumn<ContractPerson, String> birthdate;
    @FXML
    private TableColumn<ContractPerson, String> salary;
    @FXML
    private TableColumn<ContractPerson, String> credit;
    @FXML
    private TableColumn<ContractPerson, String> level;

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("SQLite");
    private ObservableList<ContractPerson> persons;
    final static DatabaseManager databaseManager = new DatabaseManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseTyp.setItems(dbTypeList);
        databaseTyp.setValue(dbTypeList.get(0));
        databaseURL.setText("jdbc:sqlite:C:\\Users\\admin\\IdeaProjects\\TopRente\\src\\");

    }

    public void disconnectDB() {
        databaseManager.exit();
        setControlLampsRed();
    }

    public void connectDB() {
        String url = String.valueOf(databaseURL.getText());
        String typ = String.valueOf(databaseTyp.getValue());
        databaseManager.connect(typ, url);
        setControlLampsGreen();
    }

    public void refreshDatabase() {
        loadContent();
    }

    public void actionViewButton() {

    }

    public void actionSaveButton() throws SQLException {
        databaseManager.createPerson(databaseManager.getStatement(), createNewPerson());
        loadContent();
    }

    private boolean isInt(TextField input, String message) {
        try {
            int value = Integer.parseInt((input.getText()));
            System.out.println(value + "is a number");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error " + message + " is not a number");
            return false;
        }
    }

    private ContractPerson createNewPerson() {
        String lastName = inputLastName.getText();
        String name = inputName.getText();
        Date birthdate = java.sql.Date.valueOf(inputBirthDate.getValue());
        Integer salary = Integer.valueOf(inputSalary.getText());
        String level = String.valueOf(inputLevel.getValue());
        Integer credit = Integer.valueOf(inputCredit.getText());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());
        ContractPerson newContractPerson = new ContractPerson(lastName, name, birthdate, salary, level, credit);
        return newContractPerson;
    }

    private void setControlLampsGreen() {
        DBLamp.setFill(Color.LIGHTGREEN);
        DBLamp1.setFill(Color.LIGHTGREEN);
    }

    private void setControlLampsRed() {
        DBLamp.setFill(Color.PALEVIOLETRED);
        DBLamp1.setFill(Color.PALEVIOLETRED);
    }


    public void loadContent() {
        try {
            databaseManager.loadPersons(persons);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        name.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("lastname"));
        salary.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("salary"));
        birthdate.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("birtdate"));
        credit.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("credit"));
        level.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("level"));

        CostumerTable.setItems(persons);

    }
}