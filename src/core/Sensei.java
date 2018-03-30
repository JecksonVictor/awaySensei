package core;

public class Sensei extends Usuario{
    public Sensei() {
    }

    public Sensei(String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
    }
    //Também outro array de treinos, posteriormente
    private Treino treinosSalvos;

    public void criarTreino(Video video_, String descricao_){
        this.treinosSalvos = new Treino(video_,descricao_);
    }

    public void cadastrarTreino(Pupilo pupilo_, Treino treino_) {
        pupilo_.setTreino1_(treino_);
    }

    public Treino getTreinosSalvos() {
        return treinosSalvos;
    }

    public void setTreinosSalvos(Treino treinosSalvos) {
        this.treinosSalvos = treinosSalvos;
    }
}
