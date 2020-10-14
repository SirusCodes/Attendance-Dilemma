package gui.landingScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LandingScreenController implements Initializable {

    @FXML
    private Button selectBtn, addRecordBtn;

    @FXML
    private Label fileAddress;

    @FXML
    public void showFileChooser(ActionEvent event) {
        System.out.println("File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("Excel Files (*.xlsx)", "*.xlsx")
        );

        fileChooser.showOpenDialog(null);

    }

    public void addRecordClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_record_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoAddRecordScreen();
    }

    public void gotoAddRecordScreen() throws IOException {
        Stage stage = (Stage) addRecordBtn.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../addRecord/add_record_screen.fxml"));

        Scene scene = new Scene(parent,960, 540);
        stage.setScene(scene);
        stage.setTitle("Add Record");
        stage.show();
    }

    public void showRecordClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("show_record_dialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Select details");
        Optional<ButtonType> response = dialog.showAndWait();

        if (response.isPresent())
            if (response.get().equals(ButtonType.NEXT))
                gotoShowRecordScreen();
    }

    public void gotoShowRecordScreen() {
        System.out.println("NextScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
