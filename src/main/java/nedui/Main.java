package nedui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ned.Ned;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ned nedInstance = new Ned(Ned.CACHED_TASKS_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ned the Assistant");
            Image crownLogo = new Image(getClass().getResourceAsStream("/images/crown_logo.png"));
            stage.getIcons().add(crownLogo);
            fxmlLoader.<MainWindow>getController().setNed(nedInstance); // inject the Ned instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
