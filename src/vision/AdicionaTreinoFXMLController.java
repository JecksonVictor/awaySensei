/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Treino;
import core.Video;
import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author JC
 */
public class AdicionaTreinoFXMLController extends Observable implements Observer ,Initializable {

    @FXML
    TextField linkVideo;
    @FXML
    TextArea descricaoVideo;
    
    @FXML
    private void adicionarVideo () {
       super.setChanged();
       super.notifyObservers(new Treino(new Video(linkVideo.getText()), descricaoVideo.getText()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
