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

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPersonView implements Initializable {

    @FXML
    private Label fullNameView;



    PersonViewModel personViewModel;
    ContractPerson person;

    public ControllerPersonView (ContractPerson contractPerson, PersonViewModel personViewModel){
        this.person = contractPerson;
        this.personViewModel = personViewModel;

    }

    public void loadView (ControllerPersonView controllerPersonView){

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResource("/personView/personView.fxml"));
            fxmlLoader.setController(controllerPersonView);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(person.getName() + " " + person.getLastname());
            stage.show();



        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("sdsd");
        CalculateContributions calculateContributions = new CalculateContributions();


    }

}
