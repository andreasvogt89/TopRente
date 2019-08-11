import interfaceView.ControllerInterfaceView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import personView.ControllerPersonView;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Top Rente");
        final ControllerInterfaceView controllerInterfaceView = new ControllerInterfaceView();
        final Scene scene = new Scene(controllerInterfaceView, 1300, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
