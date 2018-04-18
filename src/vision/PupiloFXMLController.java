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
import Beans.PupiloBean;
import core.Faixa;
import core.Sensei;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tools.ControleDeUsuarios;

/**
 * FXML Controller class
 *
 * @author José Carlos Emídio Pereira
 */
public class PupiloFXMLController extends Observable implements Initializable, Observer {
    
    @FXML
    private void sair(ActionEvent event) {
        super.setChanged();
        super.notifyObservers("sair");
    }
    
    @FXML
    Label senseiNome;
    
    @FXML
    Label faixa;
    
    @FXML
    ImageView foto;
    
    private Scene mudaScene;
    private EscolherSenseiFXMLController mudaControler;
    
    private ArrayList<Sensei> senseis;
    
    
    // Abre o modal com a tela de seleção de de senseis
    @FXML
    private void mudarSensei(ActionEvent event) throws IOException {
        
        Stage st = new Stage();
        st.setScene(this.mudaScene);
        st.setTitle("Mudar Sensei");
        st.initModality(Modality.WINDOW_MODAL);
        st.show(); 
    }
    
    // Aqui o usuário pode mudar a foto do perfil
    @FXML
    private void mudarFoto () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            this.foto.setImage(new Image(file.toURI().toString()));
            
            // Notifica pupiloBean de que a imagem do perfi foi mudada
            super.setChanged();
            super.notifyObservers(this.foto.getImage());
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Instancia a lista de senseis
        this.senseis = new ArrayList<Sensei>();
        
        // Carrega o layout da tela de seleção de senseis
        FXMLLoader fxmlMuda = new FXMLLoader(getClass().getResource("EscolherSenseiFXML.fxml"));
        try {
            mudaScene = new Scene(fxmlMuda.load());
        } catch (IOException ex) {
            Logger.getLogger(PupiloFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Controler da tela de seleção de senseis
        this.mudaControler = (EscolherSenseiFXMLController)fxmlMuda.getController();
        
        // Tela de seleção de senseis observa a tela de pupilo e a
        // tela de pupilo observa a tela de seleção de senseis
        this.mudaControler.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof ControleDeUsuarios) {
            if (arg instanceof Sensei) {
                this.senseis.add((Sensei) arg);
                this.mudaControler.addSensei((Sensei) arg);
            }
        }
        
        else if (observable instanceof EscolherSenseiFXMLController) {
            if (arg instanceof Sensei) {
                this.senseiNome.setText(((Sensei) arg).getNomeDeUsuario());
            }
        }
    }
 
}
