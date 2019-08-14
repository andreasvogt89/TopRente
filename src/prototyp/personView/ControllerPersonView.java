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




    public void loadView (ControllerPersonView controllerPersonView, PersonViewModel personViewModel){
        this.personViewModel = personViewModel;

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/personView/personView.fxml"));
            fxmlLoader.setController(controllerPersonView);
            Pane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(personViewModel.getPerson().getName() + " " + personViewModel.getPerson().getLastname());
            stage.show();


        } catch (Exception e) {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
