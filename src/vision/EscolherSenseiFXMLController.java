/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Sensei;
import core.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import run.Main;

/**
 * FXML Controller class
 *
 * @author JC
 */
public class EscolherSenseiFXMLController implements Initializable {

    @FXML
    private ListView listaSenseis;
    
    @FXML
    private void selecionar(ActionEvent event) {
        Main.mudarSensei(Main.controlador.getListaDeUsuarios().get(listaSenseis.getSelectionModel().getSelectedIndex()).getNomeDeUsuario());
        
        ((Button)event.getTarget()).getScene().getWindow().hide();
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        ((Button)event.getTarget()).getScene().getWindow().hide();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for(Usuario user: Main.controlador.getListaDeUsuarios()){
            if(user instanceof Sensei) {
                listaSenseis.getItems().add(listaSenseis.getItems().size(), user.getNomeDeUsuario());
                listaSenseis.scrollTo(listaSenseis.getItems().size() - 1);
            }
        }
        
        // list.edit(list.getItems().size() - 1);

        new AnimationTimer() {

            int frameCount = 0 ;

            @Override
            public void handle(long now) {
                frameCount++ ;
                if (frameCount > 1) {        
                    listaSenseis.edit(listaSenseis.getItems().size() - 1);
                    stop();
                }
            }

        }.start();
    }    
    
}
