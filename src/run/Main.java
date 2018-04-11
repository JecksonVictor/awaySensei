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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tools.ControleDeUsuarios;
import tools.PupiloBean;
import vision.EscolherSenseiFXMLController;
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
    // Tela para mudar sensei
    private Scene mudaSenseiScene;
    // Bean que chama a classe que guarda os usuários
    private ControleDeUsuarios controlador;
    // Bean que guarda o pupilo logado
    private PupiloBean pup;
    
    @Override
    public void start(Stage stage2) throws Exception {
        
        // Instancia os objetos
        this.controlador = new ControleDeUsuarios();
        this.pup = new PupiloBean(new Pupilo());
        
        stage = stage2;
        
        stage2.setTitle("Away Sensei");
        
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("/vision/LoginFXML.fxml"));
        loginScene = new Scene(fxmlLogin.load());
        LoginFXMLController login = (LoginFXMLController)fxmlLogin.getController();
        
        // LoginControler controlador e controlador observam um ao outro
        login.addObserver(this.controlador);
        this.controlador.addObserver(login);
        // O pupilo observa LoginControler
        login.addObserver(this.pup);
        // Classe main observa a tela de login
        login.addObserver(this);
        
        FXMLLoader fxmlPupilo = new FXMLLoader(getClass().getResource("/vision/PupiloFXML.fxml"));
        pupiloScene = new Scene(fxmlPupilo.load());
        
        PupiloFXMLController pupilo = (PupiloFXMLController)fxmlPupilo.getController();
        
        // A tela pupilo observa PupiloBean
        pup.addObserver(pupilo);
        // Classe main observa a tela pupilo
        pupilo.addObserver(this);
        
        
        FXMLLoader fxmlSensei = new FXMLLoader(getClass().getResource("/vision/SenseiFXML.fxml"));
        senseiScene = new Scene(fxmlSensei.load());
        SenseiFXMLController sensei = (SenseiFXMLController)fxmlSensei.getController();
        // Classe main observa a tela pupilo
        sensei.addObserver(this);
        
        FXMLLoader fxmlMudaSensei = new FXMLLoader(getClass().getResource("/vision/EscolherSenseiFXML.fxml"));
        mudaSenseiScene =  new Scene(fxmlMudaSensei.load());
        EscolherSenseiFXMLController mudaSensei = (EscolherSenseiFXMLController)fxmlMudaSensei.getController();
        
        // O pupilo observa MudaSenseiControler
        mudaSensei.addObserver(this.pup);
        // MudaSenseiControler observa o controlador
        this.controlador.addObserver(mudaSensei);
        
        Sensei sen1 = new Sensei("sen1", "123");
        Sensei sen2 = new Sensei("sen2", "123");
        Sensei sen3 = new Sensei("sen3", "123");
        Sensei sen4 = new Sensei("sen4", "123");
        
        this.controlador.addSensei(sen1);
        this.controlador.addSensei(sen2);
        this.controlador.addSensei(sen3);
        this.controlador.addSensei(sen4);
        
        stage2.setScene(loginScene);
        stage2.show();
    }
    
    public void mudarTela( String tela ) {
        switch(tela){
            case "telaLogin":
                stage.setScene(this.loginScene);
                break;
            case "telaPupilo":
                stage.setScene(this.pupiloScene);
                break;
            case "telaSensei":
                stage.setScene(this.senseiScene);
        }
    }
    
    public void mudaSensei() {
        Stage st = new Stage();
        st.setScene(this.mudaSenseiScene);
        st.setTitle("Mudar Sensei");
        st.initModality(Modality.WINDOW_MODAL);
        st.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable theObservable, Object arg) {
        if (theObservable instanceof PupiloFXMLController) {
            if (arg == "mudaSensei") {
                this.mudaSensei();
            } else if (arg == "telaLogin") {
                mudarTela("telaLogin");
            }
        } else if (theObservable instanceof LoginFXMLController) {
            if(arg == "telaPupilo") {
                mudarTela("telaPupilo");
            } else if(arg == "telaSensei") {
                mudarTela("telaSensei");
            }
        } else if (theObservable instanceof SenseiFXMLController) {
            if (arg == "telaLogin") {
                mudarTela("telaLogin");
            }
        }
    }
    
}
