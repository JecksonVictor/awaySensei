/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import Beans.PupiloBean;
import core.Pupilo;
import core.Sensei;
import core.Usuario;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.Autenticador;
import tools.ControleDeUsuarios;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class LoginFXMLController extends Observable implements Observer,Initializable {

    @FXML
    TextField nome;
    @FXML
    PasswordField senha;
    @FXML
    private Label senhaIncorreta;
    // Bean que chama a classe que guarda os usuários
    private ControleDeUsuarios controlador;
    
    @FXML
    private void logar(ActionEvent event) {
        
        //  Verifica se o usuário consta na base de dados
        Autenticador aut = new Autenticador();
        Usuario user = aut.autenticar(this.controlador, new Usuario(nome.getText(), senha.getText()));
        
        // Notifica que um pupilo acaba de fazer login
        if (user instanceof Pupilo) {
            super.setChanged();
            super.notifyObservers(user);
            super.setChanged();
            super.notifyObservers("telaPupilo");
        } 
        // Notifica que um sensei acaba de fazer login
        else if (user instanceof Sensei) {
            super.setChanged();
            super.notifyObservers(user);
            super.setChanged();
            super.notifyObservers("telaSensei");
        }
        // Notifica que os dados não conferem
        else {
            senhaIncorreta.setVisible(true);
        }
    }
    
    // Cadastra um novo pupilo
    @FXML
    private void cadastrar(ActionEvent event) {
        Pupilo pup_ = new Pupilo(nome.getText(),senha.getText());
        this.controlador.addPupilo(pup_);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controlador = new ControleDeUsuarios();
   }

    public void addSensei (Sensei sen) {
        this.controlador.addSensei(sen);
        super.setChanged();
        super.notifyObservers(sen);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof PupiloBean) {
            if (arg instanceof Pupilo) {
                for (Usuario user : this.controlador.getListaDeUsuarios()) {
                    if (user instanceof Sensei && user.getNomeDeUsuario() == ((Pupilo) arg).getSenseiName()) {
                        ((Sensei)user).addPupilo(((Pupilo) arg).getSenseiName());
                    }
                }
            }
        }
    }
    
}
