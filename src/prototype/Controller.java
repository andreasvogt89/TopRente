package prototype;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Date;
import static prototype.Main.databaseManager;


public class Controller {
    @FXML private TextField inputLastName;
    @FXML private TextField inputName;
    @FXML private DatePicker inputBirthDate;
    @FXML private TextField inputSalary;
    @FXML private TextField inputCredit;
    @FXML private ChoiceBox inputLevel;


public Controller(){

}

public void actionViewButton () {

}


public void actionSaveButton () throws SQLException {
    databaseManager.createPerson(databaseManager.getStatement(), createNewPerson());
}


private boolean isInt(TextField input, String message) {
        try{
            int salery = Integer.parseInt((input.getText()));
            System.out.println("salary is " + salery);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Error " + message + " is not a number");
            return false;
        }
    }


public ContractPerson createNewPerson (){
        String lastName = inputLastName.getText();
        String name = inputName.getText();
        Date birthdate = java.sql.Date.valueOf(inputBirthDate.getValue());
        Integer salary = Integer.valueOf(inputSalary.getText());
        String level = String.valueOf(inputLevel.getValue());
        Integer credit = Integer.valueOf(inputCredit.getText());
        isInt(inputSalary, inputSalary.getText());
        isInt(inputCredit, inputCredit.getText());

        ContractPerson newContractPerson = new ContractPerson(lastName,name,birthdate,salary,level,credit);
        System.out.println(newContractPerson.getBirthdate());
        return newContractPerson;
    }


}

