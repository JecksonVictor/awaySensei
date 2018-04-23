/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import core.Pupilo;
import core.Sensei;
import core.Usuario;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.Autenticador;
import tools.ControleDeUsuarios;
import vision.HomeController;
import vision.LoginController;
import vision.PupiloFXMLController;
import vision.SenseiFXMLController;
import vision.SignupController;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class Main extends Application implements Observer{
    
    private Stage stage;
    
    // Tela de login
    private Scene loginScene;
    // Tela de pupilo
    private Scene pupiloScene;
    // Tela de sensei
    private Scene senseiScene;    
    // Tela de sensei
    private Scene inicialScene;    
    // Tela de sensei
    private Scene cadastroScene;
    
    private LoginController loginController;
    
    private SignupController cadastroController;
   
    ControleDeUsuarios controlador;
    Autenticador aut;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // Instancia os objetos
        this.controlador = new ControleDeUsuarios();
        this.aut = new Autenticador();
        
        this.stage = stage;
        
        this.stage.setTitle("AwaySensei");
        
        FXMLLoader fxmlInicial = new FXMLLoader(getClass().getResource("/vision/Home.fxml"));
        this.inicialScene = new Scene(fxmlInicial.load());
        HomeController inicialController = (HomeController)fxmlInicial.getController();
        
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/vision/Login.fxml"));
        this.loginScene = new Scene(fxmlLogin.load());
        this.loginController = (LoginController)fxmlLogin.getController();
        
        FXMLLoader fxmlCadastro = new FXMLLoader(getClass().getResource("/vision/Signup.fxml"));
        this.cadastroScene = new Scene(fxmlCadastro.load());
        this.cadastroController = (SignupController)fxmlCadastro.getController();
        
        FXMLLoader fxmlPupilo = new FXMLLoader(getClass().getResource("/vision/PupiloFXML.fxml"));
        this.pupiloScene = new Scene(fxmlPupilo.load());
        PupiloFXMLController pupiloController = (PupiloFXMLController)fxmlPupilo.getController();
        
        FXMLLoader fxmlSensei = new FXMLLoader(getClass().getResource("/vision/SenseiFXML.fxml"));
        this.senseiScene = new Scene(fxmlSensei.load());
        SenseiFXMLController senseiController = (SenseiFXMLController)fxmlSensei.getController();
        
        inicialController.addObserver(this);
        this.loginController.addObserver(this);
        this.cadastroController.addObserver(this);
        pupiloController.addObserver(this);
        senseiController.addObserver(this);
        
        this.controlador.addObserver(pupiloController);
        
        this.controlador.addSensei(new Sensei("sen1", "123"));
        this.controlador.addSensei(new Sensei("sen2", "123"));
        this.controlador.addSensei(new Sensei("sen3", "123"));
        this.controlador.addSensei(new Sensei("sen4", "123"));
        
        this.stage.setScene(inicialScene);
        this.stage.show();
    }
    
    // Faz update quando algum objeto observado é modificado
    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof HomeController) {
            if (arg instanceof String) {
                if (arg == "login") {
                    this.stage.setScene(this.loginScene);
                } else if (arg == "cadastro") {
                    this.stage.setScene(this.cadastroScene);
                }
            }
        } 
        
        else if (observable instanceof LoginController) {
            if (arg instanceof Usuario) {
                Usuario user = this.aut.autenticar(controlador, (Usuario) arg);
                
                if (user instanceof Sensei) {
                    this.stage.setScene(senseiScene);
                } else if (user instanceof Pupilo) {
                    this.stage.setScene(pupiloScene);
                }else {
                    //this.loginController.aviso();
                }
            } else if (arg instanceof String) {
                if(arg == "cancelar") {
                    this.stage.setScene(inicialScene);
                }
            }
        }
        
        else if (observable instanceof SignupController) {
            if (arg instanceof Usuario) {
                Usuario user = this.aut.autenticar(controlador, (Usuario) arg);
                
                if (user instanceof Sensei || user instanceof Pupilo) {
                    //this.SignupController.aviso();
                } else if (user == null) {
                    this.controlador.addPupilo((Pupilo) arg);
                    this.stage.setScene(inicialScene);
                }
            } else if (arg instanceof String) {
                if(arg == "cancelar") {
                    this.stage.setScene(inicialScene);
                }
            }
        }
        
        else if (observable instanceof PupiloFXMLController) {
            if (arg instanceof String) {
                if (arg == "sair") {
                    this.stage.setScene(loginScene);
                }
            }
        }
        
        else if (observable instanceof SenseiFXMLController) {
            if (arg instanceof String) {
                if (arg == "sair") {
                    this.stage.setScene(loginScene);
                }
            }
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
