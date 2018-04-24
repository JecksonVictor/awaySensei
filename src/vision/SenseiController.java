/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import com.jfoenix.controls.JFXListView;
import core.Sensei;
import core.Treino;
import core.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos Emidio
 */
public class SenseiController extends Observable implements Initializable, Observer {

    @FXML
    private ImageView photoEdit;
    
    @FXML
    private JFXListView<String> pupilos;
    private ArrayList<String> list;
    ObservableList<String> listView;
    
    @FXML
    private JFXListView<String> treinos;
    private ArrayList<String> treinosList;
    ObservableList<String> treinosListView;
    
    private Scene addScene;
    private AddVideoController addControler;

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof AddVideoController) {
            if (arg instanceof Treino) {
                this.treinosList.add(((Treino) arg).getDescricao());
                this.treinos.getItems().clear();
                this.treinosListView = FXCollections.observableArrayList(this.treinosList);
                this.treinos.setItems(this.treinosListView);
                
                this.treinos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }
        }
    }
    
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
    
    
    static class TreinosCell extends ListCell<String>
    {
        HBox hbox = new HBox();
        Label name = new Label();
        Pane pane = new Pane();
        
        public TreinosCell ()
        {
            super();
            
            name.setStyle("-fx-font-size:32px;-fx-font-weight: bold;-fx-text-fill:#5E34B1;-fx-margin-top: 10px;-fx-padding-left: 30px;");
            
            hbox.getChildren().addAll(name, pane);
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
    private void removeTreino () {
        
        for (String item : this.treinos.getSelectionModel().getSelectedItems()) {
            this.treinosList.remove(item);
//            this.treinos.getItems().remove(item);
        }
        
        this.treinos.getItems().clear();
        this.treinosListView = FXCollections.observableArrayList(this.treinosList);
        this.treinos.setItems(this.treinosListView);

        this.treinos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
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
    
    @FXML
    private void addTreino(ActionEvent event) throws IOException {
        Stage st = new Stage();
        st.setScene(this.addScene);
        st.setTitle("Mudar Sensei");
        st.initModality(Modality.WINDOW_MODAL);
        st.show(); 
    }
    
    public void update (Usuario user) {
        this.photoEdit.setImage(user.getImg());
        
        this.pupilos.getItems().clear();
        this.list.clear();
        for (String pup : ((Sensei)user).getPupilos()) {
            this.list.add(pup);
        }
        
        this.listView = FXCollections.observableArrayList(this.list);
        this.pupilos.setItems(listView);
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Carrega o layout da tela de seleção de senseis
        FXMLLoader fxmlMuda = new FXMLLoader(getClass().getResource("AddVideo.fxml"));
        try {
            addScene = new Scene(fxmlMuda.load());
        } catch (IOException ex) {
            Logger.getLogger(SenseiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Controler da tela de seleção de senseis
        this.addControler = (AddVideoController)fxmlMuda.getController();
        
        // Tela de seleção de senseis observa a tela de pupilo e a
        // tela de pupilo observa a tela de seleção de senseis
        this.addControler.addObserver(this);
        this.list = new ArrayList<String>();
        this.listView = FXCollections.observableArrayList(this.list);
        this.pupilos.setItems(listView);
        this.pupilos.setCellFactory(param -> new PupilosCell(){});
        
        this.treinosList = new ArrayList<String>();
        this.treinosListView = FXCollections.observableArrayList(this.treinosList);
        this.treinos.setItems(this.treinosListView);
        this.treinos.setCellFactory(param -> new TreinosCell(){});
        
    }    
    
}
