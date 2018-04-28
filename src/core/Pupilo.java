package core;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Pupilo extends Usuario{

    //Testar com 1 treino, mas aqui será um array de treinos
    public ArrayList<Treino> listTreinos;
    public String senseiName;
    private Faixa faixa;
    private Image image;

    public Pupilo() {
        this.listTreinos = new ArrayList<>();
    }

    public String getSenseiName() {
        return senseiName;
    }

    public void setSenseiName(String senseiName) {
        this.senseiName = senseiName;
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Faixa getFaixa() {
        return faixa;
    }

    public void setFaixa(Faixa faixa) {
        this.faixa = faixa;
    }

    public Pupilo(String usuario, String senha) {
            super(usuario, senha);
            this.listTreinos = new ArrayList<Treino>();
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
