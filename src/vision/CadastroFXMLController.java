/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Pupilo;
import core.Usuario;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JC
 */
public class CadastroFXMLController extends Observable implements Initializable {

    @FXML
    TextField nome;
    @FXML
    PasswordField senha;
    @FXML
    private Label aviso;
    
    @FXML
    private void cadastrar(ActionEvent event) {
        Pupilo pup = new Pupilo(this.nome.getText(), this.senha.getText());
        super.setChanged();
        super.notifyObservers(pup);
        
        this.nome.clear();
        this.senha.clear();
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        this.nome.clear();
        this.senha.clear();
        
        super.setChanged();
        super.notifyObservers("cancelar");
    }
    
    public void aviso () {
        this.aviso.setVisible(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
