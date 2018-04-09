package core;

import java.util.ArrayList;

public class Pupilo extends Usuario{

    //Testar com 1 treino, mas aqui ser� um array de treinos
    public ArrayList<Treino> treinos;

    public Pupilo(String usuario, String senha) {
        super(usuario, senha);
    }

    public Pupilo(ArrayList<Treino> treinos, String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
        this.treinos = treinos;
    }

    public ArrayList<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(ArrayList<Treino> treinos) {
        this.treinos = treinos;
    }

    public void addTreino(Treino treino){
        this.treinos.add(treino);
    }
}
