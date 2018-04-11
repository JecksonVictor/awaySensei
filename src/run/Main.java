/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import core.Pupilo;
import core.Sensei;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Beans.PupiloBean;
import Beans.SenseiBean;
import vision.LoginFXMLController;
import vision.PupiloFXMLController;
import vision.SenseiFXMLController;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class Main extends Application implements Observer{
    
    private static Stage stage;
    
    // Tela de login
    private Scene loginScene;
    // Tela de pupilo
    private Scene pupiloScene;
    // Tela de sensei
    private Scene senseiScene;
    // Bean que guarda o pupilo logado
    private PupiloBean pup;
    // Bean que guarda o sensei logado
    private SenseiBean sen;
    
    @Override
    public void start(Stage stage2) throws Exception {
        
        // Instancia os objetos
        this.pup = new PupiloBean(new Pupilo());
        this.sen = new SenseiBean(new Sensei());
        
        stage = stage2;
        
        stage2.setTitle("Away Sensei");
        
        FXMLLoader fxmlPupilo = new FXMLLoader(getClass().getResource("/vision/PupiloFXML.fxml"));
        pupiloScene = new Scene(fxmlPupilo.load());
        PupiloFXMLController pupiloControler = (PupiloFXMLController)fxmlPupilo.getController();
        
        FXMLLoader fxmlSensei = new FXMLLoader(getClass().getResource("/vision/SenseiFXML.fxml"));
        senseiScene = new Scene(fxmlSensei.load());
        SenseiFXMLController senseiControler = (SenseiFXMLController)fxmlSensei.getController();
        
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/vision/LoginFXML.fxml"));
        loginScene = new Scene(fxmlLogin.load());
        LoginFXMLController loginControler = (LoginFXMLController)fxmlLogin.getController();
        
        // A tela Pupilo observa a tela de login
        loginControler.addObserver(pupiloControler);
        // O pupilo observa LoginControler
        loginControler.addObserver(this.pup);
        // O sensei observa LoginControler
        loginControler.addObserver(this.sen);
        this.sen.addObserver(loginControler);
        // Classe main observa a tela de login
        loginControler.addObserver(this);
        
        
        Sensei sen1 = new Sensei("sen1", "123");
        Sensei sen2 = new Sensei("sen2", "123");
        Sensei sen3 = new Sensei("sen3", "123");
        Sensei sen4 = new Sensei("sen4", "123");
        
        loginControler.addSensei(sen1);
        loginControler.addSensei(sen2);
        loginControler.addSensei(sen3);
        loginControler.addSensei(sen4);
        
        // A tela pupilo observa PupiloBean
        pup.addObserver(pupiloControler);
        pupiloControler.addObserver(pup);
        // Classe main observa a tela pupilo
        pupiloControler.addObserver(this);
        
        // Classe main observa a tela pupilo
        senseiControler.addObserver(this);
        // SenseiBean e a tela de sensei observam um ao outro
        senseiControler.addObserver(this.sen);
        this.sen.addObserver(senseiControler);
        
        stage2.setScene(loginScene);
        stage2.show();
    }
    
    // Faz update quando algum objeto observado é modificado
    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof LoginFXMLController) {
            if (arg instanceof String) {
                switch((String)arg){
                    case "telaPupilo":
                        stage.setScene(this.pupiloScene);
                        break;
                    case "telaSensei":
                        stage.setScene(this.senseiScene);
                }
            }
        } else if (observable instanceof SenseiFXMLController || observable instanceof PupiloFXMLController) {
            if (arg instanceof String) {
                if (arg == "telaLogin") {
                    stage.setScene(this.loginScene);
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
