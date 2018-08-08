package login;

import admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();
    @FXML
    private AnchorPane pane;
    @FXML
    private Label status;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button okButton;
    @FXML
    private Button closeButton;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isConnected()) {
            System.out.println("polaczono");
        }
        else {
            System.out.println("nie udalo sie polaczyc");
        }

    }

    @FXML
    public void login(ActionEvent event) {
        try {
            if(true){

                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

                adminLogin();
            } else {
                this.status.setText("bledne dane logowania");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adminLogin() {
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/adminFXML.fxml").openStream());
            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Panel Admina");
            adminStage.setResizable(true);
            adminStage.show();
        }
        catch (IOException e) {
            System.out.println("AdminLogin() ex");
            e.printStackTrace();
        }
    }
    @FXML
    public void exitProgram(){
        System.exit(0);
    }
}
