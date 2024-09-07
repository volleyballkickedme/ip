package gui;
import FRIDAY.FRIDAY;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private FRIDAY FRIDAY;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image FRIDAYImage = new Image(this.getClass().getResourceAsStream("/images/FRIDAY.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the FRIDAY instance */
    public void setFRIDAY(FRIDAY f) {
        this.FRIDAY = f;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty()) {
            return;
        }

        String response = FRIDAY.getResponse(input.trim());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFRIDAYDialog(response, FRIDAYImage)
        );
        userInput.clear();
    }
}
