package core;

import java.util.ArrayList;

public class Sensei extends Usuario{
    //Tamb�m outro array de treinos, posteriormente
    private ArrayList<Treino> treinosSalvos;

    public Sensei(String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
    }
    
    public Sensei(String nomeDeUsuario_, String senha_, ArrayList<Treino> treinosSalvos) {
        super(nomeDeUsuario_, senha_);
        this.treinosSalvos = treinosSalvos;
    }
    
//    public void criarTreino(Video video_, String descricao_){
//        this.treinosSalvos = new Treino(video_,descricao_);
//    }
//
//    public void cadastrarTreino(Pupilo pupilo_, Treino treino_) {
//        pupilo_.setTreino1_(treino_);
//    }

    public ArrayList<Treino> getTreinosSalvos() {
        return treinosSalvos;
    }

    public void setTreinosSalvos(ArrayList<Treino> treinosSalvos) {
        this.treinosSalvos = treinosSalvos;
    }
}
