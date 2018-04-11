package core;
 
import java.util.ArrayList;
 
public class Sensei extends Usuario{
    
    //Também outro array de treinos, posteriormente
    private ArrayList<Treino> treinosSalvos;
    
    public Sensei() {
    }
    
    public Sensei(ArrayList<Treino> treinosSalvos) {
        this.treinosSalvos = treinosSalvos;
    }
 
    public Sensei(String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
        this.treinosSalvos = new ArrayList<Treino>();
    }
 
    public void criarTreino(ArrayList<Video> video_, String descricao_){
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