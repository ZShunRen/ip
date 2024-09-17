package nedui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ned.Ned;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ned ned;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image nedImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Eddard.png")));

    /** Sets up the scroll pane and prompt text in the user input */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Command me m'lord");
    }

    /** Injects the Duke instance */
    public void setNed(Ned d) {
        this.ned = d;
        dialogContainer.getChildren().addAll(
                encloseNedInput(ned.getWelcomeMessage(), false)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ned.getResponse(input);
        boolean isErrorMessage = ned.getMessageErrorStatus();
        if (response.equals("EXIT MESSAGE")) {
            dialogContainer.getChildren().addAll(
                    encloseUserInput(input),
                    encloseNedInput(ned.getByeMessage(), false));
            exitApplicationWithDelay(2);
            return;

        }
        dialogContainer.getChildren().addAll(
                encloseUserInput(input),
                encloseNedInput(response, isErrorMessage)
        );
        userInput.clear();
    }

    private DialogBox encloseUserInput(String input) {
        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
        return userDialogBox;
    }

    private DialogBox encloseNedInput(String response, boolean isErrorMessage) {
        DialogBox nedDialogBox = DialogBox.getNedDialog(response, nedImage, isErrorMessage);
        return nedDialogBox;
    }
    private void exitApplicationWithDelay(double seconds) {
        PauseTransition delayAnimation = new PauseTransition(Duration.seconds(seconds));
        delayAnimation.setOnFinished(event -> Platform.exit());
        delayAnimation.play();
    }
}
