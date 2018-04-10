/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Pupilo;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import run.Main;
import tools.PupiloBean;

/**
 * FXML Controller class
 *
 * @author proae
 */
public class PupiloFXMLController implements Initializable, Observer {
    
    @FXML
    private void sair(ActionEvent event) {
        Main.mudarTela("telaLogin");
    }
    
    @FXML
    Label senseiNome;
    
    @FXML
    private void mudarSensei(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(Main.mudaSenseiScene);
        stage.setTitle("Mudar Sensei");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
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
