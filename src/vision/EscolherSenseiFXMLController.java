/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Sensei;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import tools.ControleDeUsuarios;

/**
 * FXML Controller class
 *
 * @author JC
 */
public class EscolherSenseiFXMLController extends Observable implements Observer, Initializable {
    
    @FXML
    private ListView listaSenseis;
    
    private ArrayList<Sensei> listaSensei;
    
    @FXML
    private void selecionar(ActionEvent event) {
        super.setChanged();
        super.notifyObservers(this.listaSensei.get(listaSenseis.getSelectionModel().getSelectedIndex()).getNomeDeUsuario());
        
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
        
        this.listaSensei = new ArrayList<Sensei>();
    }    

    @Override
    public void update(Observable theObservable, Object arg) {
        if (theObservable instanceof ControleDeUsuarios) {
            if (arg instanceof Sensei) {
                this.listaSensei.add((Sensei)arg);  
                this.updateList(((Sensei) arg).getNomeDeUsuario());
            }
        }
    }
    
    public void updateList(String str) {
        listaSenseis.getItems().add(listaSenseis.getItems().size(), str);
        listaSenseis.scrollTo(listaSenseis.getItems().size() - 1);
        
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
