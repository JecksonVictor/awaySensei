/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

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
 * @author proae
 */
public class LoginFXMLController extends Observable implements Observer,Initializable {

    @FXML
    TextField nome;
    @FXML
    PasswordField senha;
    @FXML
    private Label senhaIncorreta;
    
    @FXML
    private void logar(ActionEvent event) {
        this.Entrar(nome.getText(), senha.getText());
    }
    
    @FXML
    private void cadastrar(ActionEvent event) {
        this.Cadastrar(nome.getText(), senha.getText());
    }
    
    private ControleDeUsuarios controlador;
    
    public void Cadastrar(String nomedeusuario_, String senha_) {
        Pupilo pup_ = new Pupilo(nomedeusuario_,senha_);
        
        super.setChanged();
        super.notifyObservers(pup_);
        
    }
            
    public void Entrar(String nomeDeUsuario, String senha) {
        Autenticador aut = new Autenticador();
        
        Usuario user = aut.autenticar(this.controlador, new Usuario(nomeDeUsuario, senha));
        
        if (user instanceof Pupilo) {
            super.setChanged();
            super.notifyObservers(user);
            super.setChanged();
            super.notifyObservers("telaPupilo");
        } else if (user instanceof Sensei) {
            super.setChanged();
            super.notifyObservers(user);
            super.setChanged();
            super.notifyObservers("telaSensei");
        }else {
            senhaIncorreta.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.controlador = new ControleDeUsuarios();
    }    

    @Override
    public void update(Observable theObservable, Object arg) {
        if (theObservable instanceof ControleDeUsuarios) {
            if (arg instanceof Pupilo) {
                this.controlador.addPupilo((Pupilo) arg);
            } else if (arg instanceof Sensei) {
                this.controlador.addSensei((Sensei) arg);
            }
        }
    }
    
}
