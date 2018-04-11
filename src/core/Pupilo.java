package core;

import java.util.ArrayList;

public class Pupilo extends Usuario{

<<<<<<< HEAD
    //Testar com 1 treino, mas aqui será um array de treinos
    public ArrayList<Treino> listTreinos;
    public String senseiName;

    public Pupilo() {
    }

    public String getSenseiName() {
        return senseiName;
    }

    public void setSenseiName(String senseiName) {
        this.senseiName = senseiName;
        this.listTreinos = new ArrayList<Treino>();
    }
=======
    //Testar com 1 treino, mas aqui ser� um array de treinos
    public ArrayList<Treino> treinos;
>>>>>>> master

    public Pupilo(String usuario, String senha) {
        super(usuario, senha);
    }

    public Pupilo(ArrayList<Treino> treinos, String nomeDeUsuario_, String senha_) {
        super(nomeDeUsuario_, senha_);
        this.treinos = treinos;
    }
<<<<<<< HEAD
    public Treino getTreino(String descricao) {
        for (Treino treino: listTreinos){
            if(treino.getDescricao() == null ? descricao == null : treino.getDescricao().equals(descricao)) {
                return treino;
            }
        }
        
        return null;
    }

    public void addTreino(Treino treino) {
            this.listTreinos.add(treino);
    }
    
    public ArrayList<Treino> getListTreino(){
        return listTreinos;
=======

    public ArrayList<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(ArrayList<Treino> treinos) {
        this.treinos = treinos;
    }

    public void addTreino(Treino treino){
        this.treinos.add(treino);
>>>>>>> master
    }
}
