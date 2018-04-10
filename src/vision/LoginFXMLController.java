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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import run.Main;
import tools.Autenticador;

/**
 *
 * @author proae
 */
public class LoginFXMLController extends Observable implements Initializable {

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
    
    public void Cadastrar(String nomedeusuario_, String senha_) {
        Pupilo pup_ = new Pupilo(nomedeusuario_,senha_);
        Main.controlador.addPupilo(pup_);
    }
            
    public void Entrar(String nomeDeUsuario, String senha) {
        Autenticador aut = new Autenticador();
        
        Usuario user = aut.autenticar(Main.controlador, new Usuario(nomeDeUsuario, senha));
        
        if (user instanceof Pupilo) {
            super.setChanged();
            super.notifyObservers(user);
            
            Main.mudarTela("telaPupilo");
        }else if (user instanceof Sensei) {
            Main.mudarTela("telaSensei");
        } else {
            senhaIncorreta.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
