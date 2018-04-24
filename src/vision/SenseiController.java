/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import com.jfoenix.controls.JFXListView;
import core.Sensei;
import core.Usuario;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Carlos Emidio
 */
public class SenseiController extends Observable implements Initializable {

    @FXML
    private ImageView photoEdit;
    
    @FXML
    private JFXListView<String> pupilos;
    
    private ArrayList<String> list;
    
    ObservableList<String> listView;
    
    static class PupilosCell extends ListCell<String>
    {
        HBox hbox = new HBox();
        Label name = new Label();
        Pane pane = new Pane();
        Image profile = new Image("imgs/user.png");
        ImageView img = new ImageView(profile);
        
        public PupilosCell ()
        {
            super();
            
            img.setFitHeight(50);
            img.setFitWidth(50);
            
            name.setStyle("-fx-font-size:32px;-fx-font-weight: bold;-fx-text-fill:#5E34B1;-fx-margin-top: 10px;-fx-padding-left: 30px;");
            
            hbox.getChildren().addAll(img, name, pane);
            hbox.setHgrow(pane, Priority.ALWAYS);
        }
        
        public void updateItem(String name, boolean empty)
        {
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            
            if (name != null && !empty) 
            {
                this.name.setText(name);
                setGraphic(hbox);
            }
        }
    }
    
    @FXML
    private JFXListView<String> treinos;
    
    private ArrayList<String> treinosList;
    
    ObservableList<String> treinosListView;
    
    static class TreinosCell extends ListCell<String>
    {
        HBox hbox = new HBox();
        Label name = new Label();
        Pane pane = new Pane();
        
        WebView view = new WebView();
        
        public TreinosCell ()
        {
            super();
            
            name.setStyle("-fx-font-size:32px;-fx-font-weight: bold;-fx-text-fill:#5E34B1;-fx-margin-top: 10px;-fx-padding-left: 30px;");
            
            String content_Url = "<iframe width=\"580\" height=\"380\" src=\"https://www.youtube.com/embed/WC4jU14sr8w?list=RDMMzjKOmkrs6_Q\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";
            
            view.getEngine().loadContent(content_Url);
            
            view.setPrefSize(600, 400);
            
            hbox.getChildren().addAll(view,name, pane);
            hbox.setHgrow(pane, Priority.ALWAYS);
        }
        
        public void updateItem(String name, boolean empty)
        {
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            
            if (name != null && !empty) 
            {
                this.name.setText(name);
                setGraphic(hbox);
            }
        }
    }
    // Aqui o usuário pode mudar a foto do perfil
    @FXML
    private void editPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            this.photoEdit.setImage(new Image(file.toURI().toString()));
            
            // Notifica pupiloBean de que a imagem do perfi foi mudada
            super.setChanged();
            super.notifyObservers(this.photoEdit.getImage());
        }
    }
    
    @FXML
    public void sair () {
        super.setChanged();
        super.notifyObservers("sair");
    }
    
    public void update (Usuario user) {
        this.photoEdit.setImage(user.getImg());
        
        this.pupilos.getItems().clear();;
        this.list.clear();
        for (String pup : ((Sensei)user).getPupilos()) {
            this.list.add(pup);
            System.out.println(pup);
        }
        
        this.listView = FXCollections.observableArrayList(this.list);
        
        this.pupilos.setItems(listView);
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.list = new ArrayList<String>();
        
        this.listView = FXCollections.observableArrayList(this.list);
        
        this.pupilos.setItems(this.listView);
        
        this.pupilos.setCellFactory(param -> new PupilosCell(){});
        
        
        
        
        this.treinosList = new ArrayList<String>();
        
        this.treinosListView = FXCollections.observableArrayList(this.treinosList);
        
        this.treinos.setItems(this.treinosListView);
        
        this.treinos.setCellFactory(param -> new TreinosCell(){});
        
        this.treinosList.add("treino 1");
        
        this.treinosList.add("treino 2");
        
        this.treinosList.add("treino 3");
        
    }    
    
}
