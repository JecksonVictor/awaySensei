/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tools.PupiloBean;

/**
 * FXML Controller class
 *
 * @author proae
 */
public class PupiloFXMLController extends Observable implements Initializable, Observer {
    
    @FXML
    private void sair(ActionEvent event) {
        super.setChanged();
        super.notifyObservers("telaLogin");
    }
    
    @FXML
    Label senseiNome;
    
    @FXML
    private void mudarSensei(ActionEvent event) throws IOException {
        super.setChanged();
        super.notifyObservers("mudaSensei");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void update(Observable theObservable, Object arg) {
        if (theObservable instanceof PupiloBean) {
            this.senseiNome.setText((String) arg);
        }
    }
 
}
