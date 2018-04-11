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

/**
 * FXML Controller class
 *
 * @author José Carlos Emídio Pereira
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
    Label faixa;
    
    @FXML
    ImageView foto;
    
    private Scene mudaScene;
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
        EscolherSenseiFXMLController mudaControler = (EscolherSenseiFXMLController)fxmlMuda.getController();
        
        // Tela de seleção de senseis observa a tela de pupilo e a
        // tela de pupilo observa a tela de seleção de senseis
        this.addObserver(mudaControler);
        mudaControler.addObserver(this);
    }

    @Override
    public void update(Observable theObservable, Object arg) {
        
        // Se a modoficação foi em pupiloBean
        if (theObservable instanceof PupiloBean) {
            // Se a mudou o nome do sensei
            if(arg instanceof String) {
                this.senseiNome.setText((String) arg);
            }
            // Se mudou a imagem
            else if (arg instanceof Image) {
                this.foto.setImage((Image) arg);
            } 
            // Se mudou a faixa
            else if (arg instanceof Faixa) {
                this.faixa.setText(((Faixa)arg).getFaixa());
            }
        } 
        // Se a modoficação foi na tela de login
        else if (theObservable instanceof LoginFXMLController) {      
            // Se algum sensei foi adicionado
            if (arg instanceof Sensei) {
                this.senseis.add((Sensei) arg);
                super.setChanged();
                super.notifyObservers(((Sensei) arg));
            }
        } 
        // Se a modoficação foi em escolherSensei
        else if (theObservable instanceof EscolherSenseiFXMLController) {
            // Se o pupilo mudou de sensei
            if (arg instanceof Sensei) {
                this.senseiNome.setText(((Sensei) arg).getNomeDeUsuario());
                super.setChanged();
                super.notifyObservers(((Sensei) arg).getNomeDeUsuario());
            }
        }
    }
 
}
