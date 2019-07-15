package prototype;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML private TextField inputLastName;
    @FXML private TextField inputName;
    @FXML private DatePicker inputBirthDate;
    @FXML private TextField inputSalary;
    @FXML private TextField inputCredit;
    @FXML private ChoiceBox inputLevel;
    @FXML public TextField databaseURL;
    @FXML public ComboBox<String> databaseTyp;
    @FXML public TableView CostumerTable;
    private ObservableList<String> dbTypeList = FXCollections.observableArrayList("SQLite");
    final static DatabaseManager databaseManager = new DatabaseManager();

@Override
public void initialize(URL location, ResourceBundle resources) {
        databaseTyp.setItems(dbTypeList);
        databaseTyp.setValue(dbTypeList.get(0));
        databaseURL.setText("jdbc:sqlite:C:\\Users\\admin\\IdeaProjects\\TopRente\\src\\");

    }

public void exitOnClick() { databaseManager.exit(); }

public void connectDB(){
        String url = String.valueOf(databaseURL.getText());
        String typ = String.valueOf(databaseTyp.getValue());
        databaseManager.connect(typ,url,CostumerTable);
    }

public void actionViewButton() {

}

public void actionSaveButton() throws SQLException {
    databaseManager.createPerson(databaseManager.getStatement(), createNewPerson());
}

private boolean isInt(TextField input, String message) {
        try{
            int value = Integer.parseInt((input.getText()));
            System.out.println(value + "is a number");
            return true;
        }catch (NumberFormatException e){
            System.out.println("Error " + message + " is not a number");
            return false;
        }
    }

private ContractPerson createNewPerson(){
        String lastName = inputLastName.getText();
        String name = inputName.getText();
        Date birthdate = java.sql.Date.valueOf(inputBirthDate.getValue());
        Integer salary = Integer.valueOf(inputSalary.getText());
        String level = String.valueOf(inputLevel.getValue());
        Integer credit = Integer.valueOf(inputCredit.getText());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());

        ContractPerson newContractPerson = new ContractPerson(lastName,name,birthdate,salary,level,credit);
        return newContractPerson;
    }

}

