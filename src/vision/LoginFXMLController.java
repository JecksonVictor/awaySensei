/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import core.Usuario;
import core.Visitante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import run.Main;
import tools.Autenticador;
import tools.ControleDeUsuarios;

/**
 *
 * @author proae
 */
public class LoginFXMLController implements Initializable {
    
    private Visitante visitante;
    private ControleDeUsuarios controlador;

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
        Usuario user_ = new Usuario(nomedeusuario_,senha_);
        controlador.addUsuario(user_);
    }
            
    public void Entrar(String nomeDeUsuario, String senha) {
        Usuario user = new Usuario(nomeDeUsuario, senha);
        Autenticador aut = new Autenticador();
        
        String loginID = aut.autenticar(controlador, user);
        
        System.out.println(loginID);
        
        if (loginID != null && !"SI".equals(loginID)){
            Main.mudarTela("telaPupilo");
        } else {
            senhaIncorreta.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.visitante = new Visitante();
        this.controlador = new ControleDeUsuarios();
    }    
    
}
