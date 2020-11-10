package gui.addStudent;

import gui.observableModel.StudentRawObservable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentDialogController implements Initializable {
    @FXML
    TextField fnameField;
    @FXML
    TextField lnameField;
    @FXML
    TextField emailField;

    public void setFieldBinding(StudentRawObservable model) {
        fnameField.textProperty().bindBidirectional(model.fnameProperty());
        lnameField.textProperty().bindBidirectional(model.lnameProperty());
        emailField.textProperty().bindBidirectional(model.emailProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
