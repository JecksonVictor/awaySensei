package core;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Sensei extends Usuario{
    
    //Também outro array de treinos, posteriormente
    private ArrayList<Treino> treinosSalvos;
    private ArrayList<String> pupilos;
    
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public Sensei() {
    }
    
    public void addPupilo (String str) {
        this.pupilos.add(str);
    }

    public Sensei(ArrayList<Treino> treinosSalvos) {
        this.treinosSalvos = treinosSalvos;
    }

    public Sensei(String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
        this.treinosSalvos = new ArrayList<Treino>();
        this.pupilos = new ArrayList<String>();
    }

    public void criarTreino(Video video_, String descricao_){
        this.treinosSalvos.add(new Treino(video_,descricao_));
    }

    public void cadastrarTreino(Pupilo pupilo_, Treino treino_) {
        pupilo_.addTreino(treino_);
    }

    public ArrayList<Treino> getTreinosSalvos() {
        return treinosSalvos;
    }

    public void setTreinosSalvos(ArrayList<Treino> treinosSalvos) {
        this.treinosSalvos = treinosSalvos;
    }
}
