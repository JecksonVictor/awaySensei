/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import core.Pupilo;
import core.Sensei;
import core.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tools.ControleDeUsuarios;

/**
 * FXML Controller class
 *
 * @author jeckson
 */
public class PupiloController extends Observable implements Initializable, Observer {

    /* PROFILE */
    @FXML
    private ImageView photoEdit;
    @FXML
    private JFXTextField textUserName;
    @FXML
    private JFXTextField textFullName;
    @FXML
    private JFXTextField textUserMail;
    @FXML
    private JFXTextArea textUserDescription;
    @FXML
    private JFXToggleButton toggleEditProfile;

    @FXML
    public Label senseiNome; 
    
    @FXML
    private JFXButton buttonChangeSensei;
    @FXML
    private JFXTextField textSenseiNome;

    
    private Scene mudaScene;
    private EscolherSenseiFXMLController mudaControler;
    private ArrayList<Sensei> senseis;
    
    // Abre o modal com a tela de seleção de de senseis
    @FXML
    private void changeSensei(ActionEvent event) throws IOException {
        Stage st = new Stage();
        st.setScene(this.mudaScene);
        st.setTitle("Mudar Sensei");
        st.initModality(Modality.WINDOW_MODAL);
        st.show(); 
    }
    
    // Aqui o usuário pode mudar a foto do perfil
    @FXML
    private void editPhoto() {
        if (toggleEditProfile.isSelected() == false){
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            this.photoEdit.setImage(new Image(file.toURI().toString()));
            
            // Notifica pupiloBean de que a imagem do perfi foi mudada
            super.setChanged();
            super.notifyObservers(this.photoEdit.getImage());
        }
    }
    
    public void update (Usuario user) {
        this.photoEdit.setImage(user.getImg());
        
        this.senseiNome.setText(((Pupilo)user).getSenseiName());
        
    }
    
    @FXML
    public void sair () {
        super.setChanged();
        super.notifyObservers("sair");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Instancia a lista de senseis
        this.senseis = new ArrayList<>();
        
        // Carrega o layout da tela de seleção de senseis
        FXMLLoader fxmlMuda = new FXMLLoader(getClass().getResource("EscolherSenseiFXML.fxml"));
        try {
            mudaScene = new Scene(fxmlMuda.load());
        } catch (IOException ex) {
            Logger.getLogger(PupiloController.class.getName()).log(Level.SEVERE, null, ex);
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
                
                super.setChanged();
                super.notifyObservers(((Sensei) arg));
            }
        }
    }
    
    //Não Salva ainda
    public void editProfile(){
        Boolean status = true;
        if (toggleEditProfile.isSelected() == true){
            status = false;
        }
        textUserName.setDisable(status);
        textFullName.setDisable(status);
        textUserMail.setDisable(status);
        textUserDescription.setDisable(status);        
    }
    
    /* Não usado no momento
    @FXML
    private void sair(ActionEvent event) {
        super.setChanged();
        super.notifyObservers("sair");
    }
    */
}
