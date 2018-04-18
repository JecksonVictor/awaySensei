/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import Beans.SenseiBean;
import core.Treino;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José Carlos Emídio Pereira
 */
public class SenseiFXMLController extends Observable implements Observer ,Initializable {

    @FXML
    private void sair(ActionEvent event) {
        super.setChanged();
        super.notifyObservers("sair");
    }
    
    @FXML
    ListView listaAlunos;
    
    @FXML
    ImageView foto;
    
    @FXML
    ListView listaDeTreinos;
    private ObservableList<String> treinos = FXCollections.observableArrayList();
    private ObservableList<String> alunos = FXCollections.observableArrayList();
    
    private Scene adicionaScene;
    
    @FXML
    private void adicionaTreino(ActionEvent event) {
        Stage st = new Stage();
        st.setScene(this.adicionaScene);
        st.setTitle("Adicionar treino");
        st.initModality(Modality.WINDOW_MODAL);
        st.show(); 
    }
    
    
    @FXML
    private void mudarFoto () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            foto.setImage(image1);
            super.setChanged();
            super.notifyObservers(this.foto.getImage());
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.listaDeTreinos.setItems(treinos);
        this.listaAlunos.setItems(alunos);
        
        FXMLLoader fxmlMuda = new FXMLLoader(getClass().getResource("AdicionaTreinoFXML.fxml"));
        try {
            adicionaScene = new Scene(fxmlMuda.load());
        } catch (IOException ex) {
            Logger.getLogger(PupiloFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AdicionaTreinoFXMLController adicionaControler = (AdicionaTreinoFXMLController)fxmlMuda.getController();
        
        // A tela de senseis oserva a tela de adição de treinos
        adicionaControler.addObserver(this);
    }    

    @Override
    public void update(Observable observable, Object arg) {
        
        // Verifica Se a modoficação foi em SenseiBean
        if(observable instanceof SenseiBean) {
            // Verifica se algum treino foi aionado
            if (arg instanceof Treino) {
                this.treinos.add(((Treino) arg).getDescricao());
            } 
            // Verifica se alguma imagem foi adicionada
            else if (arg instanceof Image) {
                foto.setImage((Image) arg);
            }
            
            // Verifica se o usuário acabou de se logar, se for o caso,
            // a lista é esvazia
            else if (arg instanceof String && arg == "") {
                this.treinos.clear();
            }
        } 
        
        // Notifica a senseiBean que um novo treino foi adicionado
        else if (observable instanceof AdicionaTreinoFXMLController) {
            if (arg instanceof Treino) {
                this.treinos.add(((Treino) (arg)).getDescricao());
                super.setChanged();
                super.notifyObservers(arg);
            }
        }
    }
    
}
