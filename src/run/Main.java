/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import core.Pupilo;
import core.Usuario;
import java.util.Observer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tools.ControleDeUsuarios;
import tools.PupiloBean;
import vision.EscolherSenseiFXMLController;
import vision.LoginFXMLController;
import vision.PupiloFXMLController;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class Main extends Application {
    
    private static Stage stage;
    private static Scene loginScene;
    private static Scene pupiloScene;
    private static Scene senseiScene;
    public static Scene mudaSenseiScene;
    public  static ControleDeUsuarios controlador;
    
    private PupiloBean pup;
    
    @Override
    public void start(Stage stage2) throws Exception {
        
        this.controlador = new ControleDeUsuarios();
        
        this.pup = new PupiloBean(new Pupilo());
        
        stage = stage2;
        
        stage2.setTitle("Away Sensei");
        
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/vision/LoginFXML.fxml"));
        loginScene = new Scene(fxmlLogin.load());
        
        LoginFXMLController login = (LoginFXMLController)fxmlLogin.getController();
        
        login.addObserver(pup);
        
        FXMLLoader fxmlPupilo = new FXMLLoader(getClass().getResource("/vision/PupiloFXML.fxml"));
        pupiloScene = new Scene(fxmlPupilo.load());
        
        PupiloFXMLController pupilo = (PupiloFXMLController)fxmlPupilo.getController();
        
        pup.addObserver(pupilo);
        
        Parent fxmlSensei = FXMLLoader.load(getClass().getResource("/vision/SenseiFXML.fxml"));
        senseiScene = new Scene(fxmlSensei);
        
        FXMLLoader fxmlMudaSensei = new FXMLLoader(getClass().getResource("/vision/EscolherSenseiFXML.fxml"));
        mudaSenseiScene =  new Scene(fxmlMudaSensei.load());
        
        EscolherSenseiFXMLController mudaSensei = (EscolherSenseiFXMLController)fxmlMudaSensei.getController();
        
        mudaSensei.addObserver(this.pup);
        
        stage2.setScene(loginScene);
        stage2.show();
    }
    
    public static void mudarTela( String tela ) {
        switch(tela){
            case "telaLogin":
                stage.setScene(loginScene);
                break;
            case "telaPupilo":
                stage.setScene(pupiloScene);
                break;
            case "telaSensei":
                stage.setScene(senseiScene);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
