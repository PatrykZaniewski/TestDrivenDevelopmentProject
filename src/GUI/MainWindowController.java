package GUI;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class MainWindowController {

    @FXML
    private VBox mainVBox;
    @FXML
    private VBox stepOneVBox;
    @FXML
    private VBox stepTwoVBox;
    @FXML
    private VBox stepThreeVBox;
    @FXML
    private VBox stepFourVBox;

    @FXML
    private Button savePathButton;
    @FXML
    private TextField filePathTextField;
    @FXML
    private TextField directoryTextField;
    @FXML
    private ComboBox<String> algoComboBox;

    @FXML
    public void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage mainStage = (Stage) savePathButton.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(mainStage);
        if (selectedDirectory != null) {
            String path = selectedDirectory.getAbsolutePath();
            directoryTextField.setText(path);
        }
    }

    @FXML
    public void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        Stage mainStage = (Stage) savePathButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            filePathTextField.setText(path);
            animateVBox(stepTwoVBox);
        }
    }

    @FXML
    public void setAlgorithm() {
        String algoName = algoComboBox.getValue();
        System.out.println("Algo: " + algoName);
        animateVBox(stepThreeVBox);
    }

    private void animateVBox(VBox toAnimate) {
        if (toAnimate.getOpacity() != 1) {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), toAnimate);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
        }
    }
}
