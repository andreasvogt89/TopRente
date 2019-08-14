package interfaceView;


import ContributionRates.ContributionRates;
import Database.DatabaseManager;
import contractPerson.ContractPerson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import personView.ControllerPersonView;
import personView.PersonViewModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private TextField inputInsuranceNumber;
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
    private TableColumn<ContractPerson, String> insurance;
    @FXML
    private TableColumn<ContractPerson, String> sex;
    @FXML
    private Button SavePerson;
    @FXML
    private TextField InputSavingContributionANGroup1;
    @FXML
    private TextField InputSavingContributionAGGroup1;
    @FXML
    private TextField InputRiskContributionANGroup1;
    @FXML
    private TextField InputRiskContributionAGGroup1;
    @FXML
    private TextField InputSavingContributionANGroup2;
    @FXML
    private TextField InputSavingContributionAGGroup2;
    @FXML
    private TextField InputRiskContributionANGroup2;
    @FXML
    private TextField InputRiskContributionAGGroup2;
    @FXML
    private TextField InputSavingContributionANGroup3;
    @FXML
    private TextField InputSavingContributionAGGroup3;
    @FXML
    private TextField InputRiskContributionANGroup3;
    @FXML
    private TextField InputRiskContributionAGGroup3;
    @FXML
    private TextField InputSavingContributionANGroup4;
    @FXML
    private TextField InputSavingContributionAGGroup4;
    @FXML
    private TextField InputRiskContributionANGroup4;
    @FXML
    private TextField InputRiskContributionAGGroup4;
    @FXML
    private Label InputSavingContributionANGroup1BVG;
    @FXML
    private Label InputSavingContributionAGGroup1BVG;
    @FXML
    private Label InputRiskContributionANGroup1BVG;
    @FXML
    private Label InputRiskContributionAGGroup1BVG;
    @FXML
    private Label InputSavingContributionANGroup2BVG;
    @FXML
    private Label InputSavingContributionAGGroup2BVG;
    @FXML
    private Label InputRiskContributionANGroup2BVG;
    @FXML
    private Label InputRiskContributionAGGroup2BVG;
    @FXML
    private Label InputSavingContributionANGroup3BVG;
    @FXML
    private Label InputSavingContributionAGGroup3BVG;
    @FXML
    private Label InputRiskContributionANGroup3BVG;
    @FXML
    private Label InputRiskContributionAGGroup3BVG;
    @FXML
    private Label InputSavingContributionANGroup4BVG;
    @FXML
    private Label InputSavingContributionAGGroup4BVG;
    @FXML
    private Label InputRiskContributionANGroup4BVG;
    @FXML
    private Label InputRiskContributionAGGroup4BVG;
    @FXML
    private TextField InputConversionRateManGroup60;
    @FXML
    private TextField InputConversionRateWomanGroup60;
    @FXML
    private TextField InputConversionRateManGroup61;
    @FXML
    private TextField InputConversionRateWomanGroup61;
    @FXML
    private TextField InputConversionRateManGroup62;
    @FXML
    private TextField InputConversionRateWomanGroup62;
    @FXML
    private TextField InputConversionRateManGroup63;
    @FXML
    private TextField InputConversionRateWomanGroup63;
    @FXML
    private TextField InputConversionRateManGroup64;
    @FXML
    private TextField InputConversionRateWomanGroup64;
    @FXML
    private TextField InputConversionRateManGroup65;
    @FXML
    private TextField InputConversionRateWomanGroup65;
    @FXML
    private Label InputConversionRateMin;
    @FXML
    private Label InputCoordinatedSalaryBVG;
    @FXML
    private TextField InputCoordinatedSalary;



    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("SQLite");
    private String SqlLiteURL = "jdbc:sqlite:C:\\Users\\admin\\IdeaProjects\\TopRente\\src\\";
    private ObservableList<ContractPerson> persons;
    final static DatabaseManager databaseManager = new DatabaseManager();
    ObservableList<String> inputSexList = FXCollections.observableArrayList("Männlich","Weiblich");
    ObservableList<String> inputLevelList = FXCollections.observableArrayList("10%","20%","30%","40%","50%","60%","70%","80%","90%","100%");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseTyp.setItems(dbTypeList);
        databaseTyp.setValue(dbTypeList.get(0));
        databaseURL.setText(SqlLiteURL);
        inputSex.setItems(inputSexList);
        inputLevel.setItems(inputLevelList);
        InputCoordinatedSalaryBVG.setText("24885");

        ContributionRates contributionRatesBVG22until34 = new ContributionRates(3.5,3.5,0.5,0.75,6.8,"22-34");
        InputSavingContributionANGroup1BVG.setText(String.valueOf(contributionRatesBVG22until34.getSavingContributionAN()));
        InputSavingContributionAGGroup1BVG.setText(String.valueOf(contributionRatesBVG22until34.getSavingContributionAG()));
        InputRiskContributionANGroup1BVG.setText(String.valueOf(contributionRatesBVG22until34.getRiskContributionAN()));
        InputRiskContributionAGGroup1BVG.setText(String.valueOf(contributionRatesBVG22until34.getRiskContributionAG()));
        ContributionRates contributionRatesBVG34until44 = new ContributionRates(3.5,3.5,0.5,0.75,6.8,"22-34");
        InputSavingContributionANGroup2BVG.setText(String.valueOf(contributionRatesBVG34until44.getSavingContributionAN()));
        InputSavingContributionAGGroup2BVG.setText(String.valueOf(contributionRatesBVG34until44.getSavingContributionAG()));
        InputRiskContributionANGroup2BVG.setText(String.valueOf(contributionRatesBVG34until44.getRiskContributionAN()));
        InputRiskContributionAGGroup2BVG.setText(String.valueOf(contributionRatesBVG34until44.getRiskContributionAG()));


        cancleInput();
        CostumerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ContractPerson person = CostumerTable.getSelectionModel().getSelectedItem();
                System.out.println(person.getName());
                PersonViewModel personViewModel = new PersonViewModel(person);
                ControllerPersonView controllerPersonView = new ControllerPersonView(person,personViewModel);
                controllerPersonView.loadView(controllerPersonView);

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
        if (createNewPerson().checkAge(createNewPerson().getBirthday())){
        databaseManager.createPerson(databaseManager.getStatement(), createNewPerson());
        loadContent();}
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
        Integer insurance = Integer.valueOf(inputInsuranceNumber.getText());
        String sex = String.valueOf(inputSex.getValue());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());
        isInt(inputInsuranceNumber,inputInsuranceNumber.getText());
        ContractPerson newContractPerson = new ContractPerson(lastName, name, birthday, salary, level, credit,insurance,sex);
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
        insurance.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("insurance"));
        sex.setCellValueFactory(new PropertyValueFactory<ContractPerson, String>("sex"));
        CostumerTable.setItems(persons);
        cancleInput();

    }

     private void cancleInput(){

        inputLastName.setPromptText("Nachname");
        inputName.setPromptText("Vorname");
        inputSalary.setPromptText("Jahreslohn");
        inputCredit.setPromptText("Altersguthaben");
        inputBirthDate.setPromptText("Geburtsdatum");
        inputInsuranceNumber.setPromptText("Versicherungsnummer");
        inputSex.setValue(inputSexList.get(0));
        inputLevel.setValue(inputLevelList.get(9));

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
        String xString = inputInsuranceNumber.getText();


        boolean createButtonDisable = (fString.isEmpty() || fString.trim().isEmpty())||
        (sString.isEmpty() || sString.trim().isEmpty()) || (saString.isEmpty() || saString.trim().isEmpty()) ||
        (eString.isEmpty() || eString.trim().isEmpty()) || (wString.isEmpty() || wString.trim().isEmpty()) ||
        (xString.isEmpty() || xString.trim().isEmpty()) || gString.matches("null") || lString.matches("null");

        if (!createButtonDisable) {
            SavePerson.setDisable(false);
        } else {
            SavePerson.setDisable(true);
        }
    }

}
