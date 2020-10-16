package gui.landingScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClassController implements Initializable {
    @FXML
    private TextField classField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField branchField;

    @FXML
    private Button saveBtn;

    private Window window;


    public void getClassDetails() {
        System.out.println(classField.getText() + " " + yearField.getText() + " " + branchField.getText());
    }

    public void setBackOnAction(Window window) {
        this.window = window;
    }

    public void saveBtnClick() {
        getClassDetails();
    }

    public void closeBtnClick(ActionEvent event) {
        window.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveBtn.disableProperty().bind(classField.textProperty().isEmpty()
                .or(yearField.textProperty().isEmpty()
                        .or(branchField.textProperty().isEmpty()
                        )
                )
        );
    }
}
