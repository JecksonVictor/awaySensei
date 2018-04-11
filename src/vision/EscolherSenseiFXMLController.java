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
    
    // Lista de senseis
    private ArrayList<Sensei> listaSensei;
    
    // ButtonActionListener
    @FXML
    private void selecionar(ActionEvent event) {
        
        // Caso um sensei tenha sido selecionado o objeto puíloLogado é notificado
        super.setChanged();
        super.notifyObservers(this.listaSensei.get(listaSenseis.getSelectionModel().getSelectedIndex()).getNomeDeUsuario());
        
        ((Button)event.getTarget()).getScene().getWindow().hide();
    }
    
    // ButtonActionListener
    @FXML
    private void cancelar(ActionEvent event) {
        // Caso o botão cancelar foi clicado o modal é fechado
        ((Button)event.getTarget()).getScene().getWindow().hide();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.listaSensei = new ArrayList<Sensei>();
    }    

    // Metodo update da classe Observer 
    @Override
    public void update(Observable theObservable, Object arg) {
         // Verifica se o objeto modificado foi o controlador de usuários
        if (theObservable instanceof ControleDeUsuarios) {
             // Verifica se foi adiocionado um novo sensei, se foi
             // ele é inserido na lista de senseis
            if (arg instanceof Sensei) {
                this.listaSensei.add((Sensei)arg);  
                this.updateList(((Sensei) arg).getNomeDeUsuario());
            }
        }
    }
    
    // Atualiza a lista de senseis
    public void updateList(String str) {
        listaSenseis.getItems().add(listaSenseis.getItems().size(), str);
        listaSenseis.scrollTo(listaSenseis.getItems().size() - 1);

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
