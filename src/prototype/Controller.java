package prototype;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class Controller implements Observer {
    @FXML private TextField inputName;
    @FXML private TextField inputAge;
    @FXML private TextField inputSalary;
    @FXML private ChoiceBox inputLevel;

public Controller(){

}

public void actionSaveButton () {
    String name = inputName.getText();
    Integer age = Integer.valueOf(inputAge.getText());
    Integer salary = Integer.valueOf(inputSalary.getText());
    String level = String.valueOf(inputLevel.getValue());
    isInt(inputSalary, inputSalary.getText());

    contractPerson contractPerson = new contractPerson(name,age,salary,level);
    System.out.println("Person:");
    System.out.println(contractPerson.getName());
    System.out.println(contractPerson.getAge());
    System.out.println(contractPerson.getEmploymentLevel());
    System.out.println(contractPerson.getAnnualSalary());
    System.out.println(contractPerson.getCoordinatedSalary());
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



    @Override
    public void update() {

    }
}

