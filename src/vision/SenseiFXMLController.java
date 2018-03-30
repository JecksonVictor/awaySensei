/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import run.Main;

/**
 * FXML Controller class
 *
 * @author proae
 */
public class SenseiFXMLController implements Initializable {

    @FXML
    private void sair(ActionEvent event) {
        Main.mudarTela("telaLogin");
    }
    
    @FXML
    ListView listaAlunos;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> names = FXCollections.observableArrayList();
        names.add("Definition of First Word");
        names.add("Second Word");
        names.add("Third Word");
        listaAlunos = new ListView<>(names);
    }    
    
}
