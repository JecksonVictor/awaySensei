/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Usuario;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jeckson
 */
public class LoginController extends Observable implements Initializable {

    @FXML
    private Button buttonLogin;
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void login(ActionEvent event) {
        Usuario user = new Usuario(this.textUsername.getText(), this.textPassword.getText());
        super.setChanged();
        super.notifyObservers(user);

        this.textUsername.clear();
        this.textPassword.clear();
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        this.textUsername.clear();
        this.textPassword.clear();
        
        super.setChanged();
        super.notifyObservers("cancelar");
    }
    
}
