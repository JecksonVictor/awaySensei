/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import core.Sensei;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jeckson
 */
public class PickSenseiController extends Observable implements Initializable {

    @FXML
    private JFXListView listaSenseis;
    @FXML
    private JFXButton buttonCancelar;
    @FXML
    private JFXButton buttonPickSensei;
    
    // Lista de senseis
    private ArrayList<Sensei> Senseis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Senseis = new ArrayList<>();
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        // Caso o botão cancelar foi clicado o modal é fechado
        ((JFXButton)event.getTarget()).getScene().getWindow().hide();
    }

    @FXML
    private void pickSensei(ActionEvent event) {
        // Caso um sensei tenha sido selecionado o objeto puíloLogado é notificado
        super.setChanged();
        super.notifyObservers(this.Senseis.get(listaSenseis.getSelectionModel().getSelectedIndex()));
        
        ((JFXButton)event.getTarget()).getScene().getWindow().hide();
    }
    
    public void addSensei(Sensei sen) {
        this.Senseis.add(sen);
        this.updateList(sen.getNomeDeUsuario());
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
