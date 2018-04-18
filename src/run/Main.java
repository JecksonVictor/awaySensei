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
import vision.CadastroFXMLController;
import vision.LoginFXMLController;
import vision.PupiloFXMLController;
import vision.SenseiFXMLController;
import vision.TelaInicialFXMLController;

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
    
    private LoginFXMLController loginControler;
    
    private CadastroFXMLController cadastroControler;
   
    ControleDeUsuarios controlador;
    Autenticador aut;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // Instancia os objetos
        this.controlador = new ControleDeUsuarios();
        this.aut = new Autenticador();
        
        this.stage = stage;
        
        this.stage.setTitle("Away Sensei");
        
        FXMLLoader fxmlInicial = new FXMLLoader(getClass().getResource("/vision/TelaInicialFXML.fxml"));
        this.inicialScene = new Scene(fxmlInicial.load());
        TelaInicialFXMLController inicialControler = (TelaInicialFXMLController)fxmlInicial.getController();
        
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/vision/LoginFXML.fxml"));
        this.loginScene = new Scene(fxmlLogin.load());
        this.loginControler = (LoginFXMLController)fxmlLogin.getController();
        
        FXMLLoader fxmlCadastro = new FXMLLoader(getClass().getResource("/vision/CadastroFXML.fxml"));
        this.cadastroScene = new Scene(fxmlCadastro.load());
        this.cadastroControler = (CadastroFXMLController)fxmlCadastro.getController();
        
        FXMLLoader fxmlPupilo = new FXMLLoader(getClass().getResource("/vision/PupiloFXML.fxml"));
        this.pupiloScene = new Scene(fxmlPupilo.load());
        PupiloFXMLController pupiloControler = (PupiloFXMLController)fxmlPupilo.getController();
        
        FXMLLoader fxmlSensei = new FXMLLoader(getClass().getResource("/vision/SenseiFXML.fxml"));
        this.senseiScene = new Scene(fxmlSensei.load());
        SenseiFXMLController senseiControler = (SenseiFXMLController)fxmlSensei.getController();
        
        inicialControler.addObserver(this);
        this.loginControler.addObserver(this);
        this.cadastroControler.addObserver(this);
        pupiloControler.addObserver(this);
        senseiControler.addObserver(this);
        
        this.controlador.addObserver(pupiloControler);
        
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
        if (observable instanceof TelaInicialFXMLController) {
            if (arg instanceof String) {
                if (arg == "login") {
                    this.stage.setScene(this.loginScene);
                } else if (arg == "cadastro") {
                    this.stage.setScene(this.cadastroScene);
                }
            }
        } 
        
        else if (observable instanceof LoginFXMLController) {
            if (arg instanceof Usuario) {
                Usuario user = this.aut.autenticar(controlador, (Usuario) arg);
                
                if (user instanceof Sensei) {
                    this.stage.setScene(senseiScene);
                } else if (user instanceof Pupilo) {
                    this.stage.setScene(pupiloScene);
                }else {
                    this.loginControler.aviso();
                }
            } else if (arg instanceof String) {
                if(arg == "cancelar") {
                    this.stage.setScene(inicialScene);
                }
            }
        }
        
        else if (observable instanceof CadastroFXMLController) {
            if (arg instanceof Usuario) {
                Usuario user = this.aut.autenticar(controlador, (Usuario) arg);
                
                if (user instanceof Sensei || user instanceof Pupilo) {
                    this.cadastroControler.aviso();
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
