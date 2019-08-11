package personView;

import calculate.CalculateContributions;
import contractPerson.ContractPerson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPersonView implements Initializable {

    @FXML private Label fullNameView;

    PersonViewModel personViewModel;
    ContractPerson person;

    public ControllerPersonView(PersonViewModel personViewModel) {
        this.personViewModel = personViewModel;
        this.person = personViewModel.getPerson();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CalculateContributions calculateContributions = new CalculateContributions();
        // fullNameView.setText(person.getName() + " " + person.getLastname());
        //salaryView.setText(String.valueOf(person.getSalary()));
        //koordSalaryView.setText(String.valueOf(calculateContributions.calculateCoordinatedSalaryBVG(24885, person.getSalary())));

    }

}
