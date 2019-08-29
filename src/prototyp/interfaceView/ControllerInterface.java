package interfaceView;


import ContributionRates.ContributionRates;
import contractPerson.ContractPerson;
import Database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import personView.ControllerPersonView;
import personView.ModelPersonView;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
/**
 * Controller for the interface
 *
 *
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class ControllerInterface implements Initializable {


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
    private DatePicker inputEntrydate;
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
    private TextField InputConversionRateGroup60;
    @FXML
    private TextField InputConversionRateGroup61;
    @FXML
    private TextField InputConversionRateGroup62;
    @FXML
    private TextField InputConversionRateGroup63M;
    @FXML
    private TextField InputConversionRateGroup63W;
    @FXML
    private TextField InputConversionRateGroup64M;
    @FXML
    private TextField InputConversionRateGroup64W;
    @FXML
    private TextField InputConversionRateGroup65;
    @FXML
    private Label InputConversionRateMin;
    @FXML
    private Label InputCoordinatedDetuctionBVG;
    @FXML
    private TextField InputCoordinatedSalaryRate;
    @FXML
    private Label InputInterestRate;

    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("SQLite");
    private String SqlLiteURL = "jdbc:sqlite:C:\\Users\\admin\\IdeaProjects\\TopRente\\src\\";
    private ObservableList<ContractPerson> persons;
    private static final DatabaseManager databaseManager = new DatabaseManager();
    private ObservableList<String> inputSexList = FXCollections.observableArrayList("Männlich","Weiblich");
    private ObservableList<String> inputLevelList = FXCollections.observableArrayList("10%","20%","30%","40%","50%","60%","70%","80%","90%","100%");
    private InterfaceModel interfaceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InitializeInput();
        setBVGRates();
        setRates();
        CostumerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ContractPerson person = CostumerTable.getSelectionModel().getSelectedItem();
                ModelPersonView personViewModel = new ModelPersonView(person,createAllContributionRates());
                ControllerPersonView controllerPersonView = new ControllerPersonView();

                    controllerPersonView.loadView(controllerPersonView,personViewModel);

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
        SavePerson.setDisable(true);
        inputLastName.clear();
        inputName.clear();
        inputSalary.clear();
        inputCredit.clear();
        inputBirthDate.getEditor().clear();
        inputEntrydate.getEditor().clear();
        inputInsuranceNumber.clear();
    }

    public void actionSaveButton() throws SQLException {
        InterfaceModel interfaceModel = new InterfaceModel();
        this.interfaceModel = interfaceModel;
        if (interfaceModel.checkSalary(Integer.valueOf(inputSalary.getText())) && interfaceModel.checkAge(String.valueOf(inputBirthDate.getValue()))) {
            DatabaseManager.createPerson(databaseManager.getStatement(), createNewPerson());
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
        String entrydate = String.valueOf(inputEntrydate.getValue());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());
        isInt(inputInsuranceNumber,inputInsuranceNumber.getText());
        ContractPerson newContractPerson = new ContractPerson(lastName, name, birthday, salary, level, credit,insurance,sex,entrydate);
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
        InitializeInput();

    }

    private void InitializeInput(){
        SavePerson.setDisable(true);
        inputLastName.setPromptText("Nachname");
        inputName.setPromptText("Vorname");
        inputSalary.setPromptText("Jahreslohn");
        inputCredit.setPromptText("Altersguthaben");
        inputBirthDate.setPromptText("Geburtsdatum");
        inputInsuranceNumber.setPromptText("Versicherungsnummer");
        inputEntrydate.setPromptText("Eintrittsdatum");
        inputSex.setValue(inputSexList.get(0));
        inputLevel.setValue(inputLevelList.get(9));
        databaseTyp.setItems(dbTypeList);
        databaseTyp.setValue(dbTypeList.get(0));
        databaseURL.setText(SqlLiteURL);
        inputSex.setItems(inputSexList);
        inputLevel.setItems(inputLevelList);

    }

    private ContributionRates createAllContributionRates(){

        ContributionRates contributionRates = new ContributionRates(
                Double.valueOf(InputSavingContributionANGroup1.getText()),
                Double.valueOf(InputSavingContributionAGGroup1.getText()),
                Double.valueOf(InputRiskContributionANGroup1.getText()),
                Double.valueOf(InputRiskContributionAGGroup1.getText()),
                Double.valueOf(InputSavingContributionANGroup2.getText()),
                Double.valueOf(InputSavingContributionAGGroup2.getText()),
                Double.valueOf(InputRiskContributionANGroup2.getText()),
                Double.valueOf(InputRiskContributionAGGroup2.getText()),
                Double.valueOf(InputSavingContributionANGroup3.getText()),
                Double.valueOf(InputSavingContributionAGGroup3.getText()),
                Double.valueOf(InputRiskContributionANGroup3.getText()),
                Double.valueOf(InputRiskContributionAGGroup3.getText()),
                Double.valueOf(InputSavingContributionANGroup4.getText()),
                Double.valueOf(InputSavingContributionAGGroup4.getText()),
                Double.valueOf(InputRiskContributionANGroup4.getText()),
                Double.valueOf(InputRiskContributionAGGroup4.getText()),
                Double.valueOf(InputSavingContributionANGroup1BVG.getText()),
                Double.valueOf(InputSavingContributionAGGroup1BVG.getText()),
                Double.valueOf(InputRiskContributionANGroup1BVG.getText()),
                Double.valueOf(InputRiskContributionAGGroup1BVG.getText()),
                Double.valueOf(InputSavingContributionANGroup2BVG.getText()),
                Double.valueOf(InputSavingContributionAGGroup2BVG.getText()),
                Double.valueOf(InputRiskContributionANGroup2BVG.getText()),
                Double.valueOf(InputRiskContributionAGGroup2BVG.getText()),
                Double.valueOf(InputSavingContributionANGroup3BVG.getText()),
                Double.valueOf(InputSavingContributionAGGroup3BVG.getText()),
                Double.valueOf(InputRiskContributionANGroup3BVG.getText()),
                Double.valueOf(InputRiskContributionAGGroup3BVG.getText()),
                Double.valueOf(InputSavingContributionANGroup4BVG.getText()),
                Double.valueOf(InputSavingContributionAGGroup4BVG.getText()),
                Double.valueOf(InputRiskContributionANGroup4BVG.getText()),
                Double.valueOf(InputRiskContributionAGGroup4BVG.getText()),
                Double.valueOf(InputConversionRateMin.getText()),
                Double.valueOf(InputConversionRateGroup60.getText()),
                Double.valueOf(InputConversionRateGroup61.getText()),
                Double.valueOf(InputConversionRateGroup62.getText()),
                Double.valueOf(InputConversionRateGroup63M.getText()),
                Double.valueOf(InputConversionRateGroup63W.getText()),
                Double.valueOf(InputConversionRateGroup64M.getText()),
                Double.valueOf(InputConversionRateGroup64W.getText()),
                Double.valueOf(InputConversionRateGroup65.getText()),
                Double.valueOf(InputCoordinatedSalaryRate.getText()),
                Double.valueOf(InputCoordinatedDetuctionBVG.getText()),
                Double.valueOf(InputInterestRate.getText()),
                Double.valueOf(3555.0),
                Double.valueOf(85320.0)
        );
        return contributionRates;
    }

    private void setBVGRates (){
        InputCoordinatedDetuctionBVG.setText("24885");
        InputSavingContributionANGroup1BVG.setText("3.5");
        InputSavingContributionAGGroup1BVG.setText("3.5");
        InputRiskContributionANGroup1BVG.setText("0.5");
        InputRiskContributionAGGroup1BVG.setText("0.75");
        InputSavingContributionANGroup2BVG.setText("5.0");
        InputSavingContributionAGGroup2BVG.setText("5.0");
        InputRiskContributionANGroup2BVG.setText("0.5");
        InputRiskContributionAGGroup2BVG.setText("0.75");
        InputSavingContributionANGroup3BVG.setText("7.5");
        InputSavingContributionAGGroup3BVG.setText("7.5");
        InputRiskContributionANGroup3BVG.setText("0.5");
        InputRiskContributionAGGroup3BVG.setText("0.75");
        InputSavingContributionANGroup4BVG.setText("9.0");
        InputSavingContributionAGGroup4BVG.setText("9.0");
        InputRiskContributionANGroup4BVG.setText("0.5");
        InputRiskContributionAGGroup4BVG.setText("0.75");
        InputConversionRateMin.setText("6.8");
        InputInterestRate.setText("1");
    }

    private void setRates (){
        InputCoordinatedSalaryRate.setText("40");
        InputSavingContributionANGroup1.setText("5.85");
        InputSavingContributionAGGroup1.setText("6.9");
        InputRiskContributionANGroup1.setText("0.5");
        InputRiskContributionAGGroup1.setText("0.75");
        InputSavingContributionANGroup2.setText("7.25");
        InputSavingContributionAGGroup2.setText("9.0");
        InputRiskContributionANGroup2.setText("0.5");
        InputRiskContributionAGGroup2.setText("0.75");
        InputSavingContributionANGroup3.setText("9.4");
        InputSavingContributionAGGroup3.setText("16.60");
        InputRiskContributionANGroup3.setText("0.5");
        InputRiskContributionAGGroup3.setText("0.75");
        InputSavingContributionANGroup4.setText("12.5");
        InputSavingContributionAGGroup4.setText("21.75");
        InputRiskContributionANGroup4.setText("0.5");
        InputRiskContributionAGGroup4.setText("0.75");
        InputConversionRateGroup60.setText("4.47");
        InputConversionRateGroup61.setText("4.58");
        InputConversionRateGroup62.setText("4.7");
        InputConversionRateGroup63M.setText("4.83");
        InputConversionRateGroup63W.setText("4.9");
        InputConversionRateGroup64M.setText("4.96");
        InputConversionRateGroup64W.setText("5.09");
        InputConversionRateGroup65.setText("5.09");

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
        String zString = inputEntrydate.toString();


        boolean createButtonDisable = (fString.isEmpty() || fString.trim().isEmpty())||
                (sString.isEmpty() || sString.trim().isEmpty()) || (saString.isEmpty() || saString.trim().isEmpty()) ||
                (eString.isEmpty() || eString.trim().isEmpty()) || (wString.isEmpty() || wString.trim().isEmpty()) ||
                (xString.isEmpty() || xString.trim().isEmpty()) || gString.matches("null") ||
                lString.matches("null") || zString.matches("null");

        if (!createButtonDisable) {
            SavePerson.setDisable(false);
        } else {
            SavePerson.setDisable(true);
        }
    }

}
