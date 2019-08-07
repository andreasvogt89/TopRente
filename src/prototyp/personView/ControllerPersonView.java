package personView;

import calculate.CalculateContributions;
import contractPerson.ContractPerson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPersonView implements Initializable  {


    @FXML
    Label nameView;
    @FXML
    Label salaryView;
    @FXML
    Label koordSalaryView;

    PersonViewModel personViewModel;
    ContractPerson person;

    public ControllerPersonView(PersonViewModel personViewModel){
        this.personViewModel = personViewModel;
        this.person = personViewModel.getPerson();

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/personView/personView.fxml"));
            loader.setController(this);
            stage.setTitle(person.getName() + " " + person.getLastname());
            stage.setWidth(800);
            stage.setHeight(800);
            stage.show();

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CalculateContributions calculateContributions = new CalculateContributions(person);
        nameView.setText(person.getName() + " " + person.getLastname());
        salaryView.setText(String.valueOf(person.getSalary()));
        koordSalaryView.setText(String.valueOf(calculateContributions.calculateCoordinatedSalary(24500)));
        System.out.println(nameView.getText() + salaryView.getText());
    }

}
