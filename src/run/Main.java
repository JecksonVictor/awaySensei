/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import core.Usuario;
import daos.SaveUser;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author José Carlos Emídio Pereira
 */
public class Main extends Application {
    
    private static Stage stage;
    private static Scene loginScene;
    private static Scene pupiloScene;
    private static Scene senseiScene;
    
    @Override
    public void start(Stage stage2) throws Exception {
        
        stage = stage2;
        
        stage2.setTitle("Away Sensei");
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/vision/LoginFXML.fxml"));
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlPupilo = FXMLLoader.load(getClass().getResource("/vision/PupiloFXML.fxml"));
        pupiloScene = new Scene(fxmlPupilo);
        
        Parent fxmlSensei = FXMLLoader.load(getClass().getResource("/vision/SenseiFXML.fxml"));
        senseiScene = new Scene(fxmlSensei);
        
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
