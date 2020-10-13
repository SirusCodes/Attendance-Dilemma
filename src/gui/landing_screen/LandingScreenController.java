package gui.landing_screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.util.Optional;

public class LandingScreenController {

    @FXML
    private Button selectBtn;

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

    public void gotoAddRecordScreen() {
        System.out.println("NextScreen");
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
}
