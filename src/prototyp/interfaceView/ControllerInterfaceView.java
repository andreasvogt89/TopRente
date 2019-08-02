package interfaceView;


import Database.DatabaseManager;
import contractPerson.ContractPerson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class ControllerInterfaceView implements Initializable {

    @FXML
    private TextField inputLastName;
    @FXML
    private TextField inputName;
    @FXML
    private DatePicker inputBirthDate;
    @FXML
    private ChoiceBox inputSex;
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
    private TableColumn<ContractPerson, String> birthday;
    @FXML
    private TableColumn<ContractPerson, String> salary;
    @FXML
    private TableColumn<ContractPerson, String> credit;
    @FXML
    private TableColumn<ContractPerson, String> level;
    @FXML
    private Button SavePerson;

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("SQLite");
    private ObservableList<ContractPerson> persons;
    final static DatabaseManager databaseManager = new DatabaseManager();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseTyp.setItems(dbTypeList);
        databaseTyp.setValue(dbTypeList.get(0));
        databaseURL.setText("jdbc:sqlite:C:\\Users\\%User%\\IdeaProjects\\TopRente\\src\\");
        cancleInput();
        SavePerson.setDisable(true);

        CostumerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ContractPerson person = CostumerTable.getSelectionModel().getSelectedItem();
                System.out.println(person.getName());
            }
        });
    }

    public void disconnectDB() {
        databaseManager.disconnectDatabase();
        setControlLampsRed();
    }

    public void connectDB() {
        String url = String.valueOf(databaseURL.getText());
        String typ = String.valueOf(databaseTyp.getValue());
        databaseManager.connectDatabase(typ, url);
       if (databaseManager.isDatabaseConnected()){
        setControlLampsGreen();}
        loadContent();
    }

    public void refreshDatabase() {
        databaseManager.isDatabaseConnected();
        loadContent();
    }

    public void actionCancleButton() {
        cancleInput();
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
        String birthday = String.valueOf(inputBirthDate.getValue());
        Integer salary = Integer.valueOf(inputSalary.getText());
        String level = String.valueOf(inputLevel.getValue());
        Integer credit = Integer.valueOf(inputCredit.getText());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());
        ContractPerson newContractPerson = new ContractPerson(lastName, name, birthday, salary, level, credit);
        newContractPerson.calculateAge(birthday);
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

    private void loadContent() {
        try {
           persons = databaseManager.loadPersons(databaseManager.getStatement(), persons);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        lastname.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("name"));
        salary.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("salary"));
        birthday.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("birthday"));
        credit.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("credit"));
        level.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("level"));

        CostumerTable.setItems(persons);

    }

    private void cancleInput(){

        inputLastName.setPromptText("Nachname");
        inputName.setPromptText("Vorname");
        inputSalary.setPromptText("Jahreslohn");
        inputCredit.setPromptText("Altersguthaben");
        inputBirthDate.setPromptText("Geburtsdatum");

    }

    @FXML
    public void  keyReleasedProperty(KeyEvent event) {
        String fString = inputLastName.getText();
        String sString = inputName.getText();
        String saString = inputCredit.getText();
        String wString = inputSalary.getText();
        String eString = inputBirthDate.toString();
        String gString = String.valueOf(inputLevel.getValue());
        String lString = String.valueOf(inputSex.getValue());

        boolean createButtonDisable = (fString.isEmpty() || fString.trim().isEmpty())||
        (sString.isEmpty() || sString.trim().isEmpty()) || (saString.isEmpty() || saString.trim().isEmpty()) ||
        (eString.isEmpty() || eString.trim().isEmpty()) || (wString.isEmpty() || wString.trim().isEmpty()) ||
                gString.matches("null") || lString.matches("null");

        if (!createButtonDisable) {
            SavePerson.setDisable(false);
        } else {
            SavePerson.setDisable(true);
        }
    }

}
