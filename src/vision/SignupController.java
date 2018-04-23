/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.ErroNoCadastroException;
import core.Pupilo;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author jeckson
 */
public class SignupController extends Observable implements Initializable {

    @FXML
    private Button cancel;
    @FXML
    private Button buttonSignup;
    @FXML
    private Button buttonCancelar;
    @FXML
    private JFXTextField textUsername;
    @FXML
    private JFXTextField textMail;
    @FXML
    private JFXPasswordField textPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) throws ErroNoCadastroException {
        if (this.textUsername.getText().length() >= 4 && this.textPassword.getText().length() >= 4){
            Pupilo pupilo = new Pupilo(this.textUsername.getText(), this.textPassword.getText());
            super.setChanged();
            super.notifyObservers(pupilo);

            this.textUsername.clear();
            this.textPassword.clear();
        } else {
            throw new ErroNoCadastroException();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.textUsername.clear();
        this.textPassword.clear();
        
        super.setChanged();
        super.notifyObservers("cancelar");
    }
    
}
