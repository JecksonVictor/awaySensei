package core;
 
import java.util.ArrayList;
 
public class Pupilo extends Usuario{
 
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
 
    public Pupilo(String usuario, String senha) {
            super(usuario, senha);
    }
    
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
    }
}
 