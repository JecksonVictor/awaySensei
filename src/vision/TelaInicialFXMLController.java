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

/**
 * FXML Controller class
 *
 * @author JC
 */
public class TelaInicialFXMLController extends Observable implements Initializable {

    @FXML
    private void login(ActionEvent event) { 
        super.setChanged();
        super.notifyObservers("login");
    }
    
    @FXML
    private void cadastro(ActionEvent event) { 
        super.setChanged();
        super.notifyObservers("cadastro");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
