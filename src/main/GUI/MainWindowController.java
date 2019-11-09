package GUI;

import engine.Creator;
import engine.Encryptor;
import engine.exceptions.AlgorithmException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class MainWindowController {

    @FXML
    private VBox stepTwoEncVBox;
    @FXML
    private VBox stepThreeEncVBox;
    @FXML
    private VBox stepFourEncVBox;
    @FXML
    private VBox stepTwoDecVBox;
    @FXML
    private VBox stepThreeDecVBox;

    @FXML
    private Button savePathButtonEnc;
    @FXML
    private Button startEncButton;
    @FXML
    private Button startDecButton;
    @FXML
    private TextField filePathTextFieldEnc;
    @FXML
    private TextField filePathTextFieldDec;
    @FXML
    private TextField directoryTextFieldEnc;
    @FXML
    private TextField directoryTextFieldDec;
    @FXML
    private ComboBox<String> algoComboBox;
    @FXML
    private TextField keyInput;

    @FXML
    public void openDirectoryChooserEnc() {
        openDirecChooser(directoryTextFieldEnc, startEncButton);
    }

    @FXML
    public void openStartButtonEnc() {
        animateNode(startEncButton);
    }

    @FXML
    public void openDirectoryChooserDec() {
        openDirecChooser(directoryTextFieldDec, startDecButton);
    }

    @FXML
    public void openStartButtonDec() {
        animateNode(startDecButton);
    }

    @FXML
    public void openFileChooserEnc() {
        openFileChooser(filePathTextFieldEnc, stepTwoEncVBox);
    }

    @FXML
    public void openStepTwoEnc() {
        animateNode(stepTwoEncVBox);
    }

    @FXML
    public void openFileChooserDec() {
        openFileChooser(filePathTextFieldDec, stepTwoDecVBox);
    }

    @FXML
    public void openStepTwoDec() {
        animateNode(stepTwoDecVBox);
    }

    @FXML
    public void setAlgorithm() {
        animateNode(stepThreeEncVBox);
    }

    @FXML
    public void setKeyEnc() {
        animateNode(stepFourEncVBox);
    }

    @FXML
    public void setKeyDec() {
        animateNode(stepThreeDecVBox);
    }

    @FXML
    public void startEnc() throws AlgorithmException, NoSuchAlgorithmException{
        //TODO przygotuj odpowiedni szyfr -> osobna klasa
        String filePath = filePathTextFieldEnc.getText();
        String savePath = directoryTextFieldEnc.getText();
        String key = keyInput.getText();
        String algorithm = null;
        String mode = null;
        if(algoComboBox.getSelectionModel().getSelectedIndex() < 6){
            String[] splitted= algoComboBox.getValue().split("\\s+");
            algorithm = splitted[0];
            mode = splitted[3];
        }
        int checkFiles = checkFilePaths(filePath, savePath);
        if (checkFiles == 1) {
            showError(1);
        } else if (checkFiles == 2) {
            showError(2);
        } else if (key.equals("")) {
            showError(3);
        }



        Creator creator = new Creator();
        Encryptor encryptor = creator.createEncryptor(algorithm, mode, "ssshhhhhhhhhhh!!!!");
        String test = "howtodoinjava.com";
        System.out.println(Base64.getEncoder().encodeToString(encryptor.encrypt(test.getBytes(StandardCharsets.UTF_8))));
    }

    private int checkFilePaths(String filePath, String savePath) {
        Path inputFile = new File(filePath).toPath();
        Path saveDir = new File(savePath).toPath();
        if (Files.isWritable(saveDir)) {
            if (Files.exists(inputFile)) {
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    private void showError(int code) {
        String mes;
        switch (code) {
            case 1:
                mes = "Nie można zapisać pliku w podanej lokalizacji";
                break;
            case 2:
                mes = "Podany plik nie istnieje";
                break;
            case 3:
                mes = "Podaj klucz, any kontynuować";
                break;
            default:
                mes = "Wystąpił bład";
                break;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!");
        alert.setHeaderText(mes);
        alert.initOwner(savePathButtonEnc.getScene().getWindow());
        alert.showAndWait();
    }

    private void animateNode(Node toAnimate) {
        if (toAnimate.getOpacity() == 0) {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), toAnimate);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            toAnimate.setDisable(false);
        }
    }

    private void openDirecChooser(TextField pathField, Button startButton) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage mainStage = (Stage) savePathButtonEnc.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(mainStage);
        if (selectedDirectory != null) {
            String path = selectedDirectory.getAbsolutePath();
            pathField.setText(path);
            animateNode(startButton);
        }
    }

    private void openFileChooser(TextField pathField, VBox toAnimate) {
        FileChooser fileChooser = new FileChooser();
        Stage mainStage = (Stage) savePathButtonEnc.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            pathField.setText(path);
            animateNode(toAnimate);
        }
    }
}