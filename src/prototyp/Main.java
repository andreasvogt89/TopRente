
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @version 1.0
 * @autor Andreas Vogt
 * @date 19.08.2019
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainInterface/mainInterface.fxml"));
        primaryStage.setTitle("Top Rente");
        primaryStage.setScene(new Scene(root, 1300, 700));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("retirement.png"));

    }

    public static void main(String[] args) {
        launch(args);
    }
}