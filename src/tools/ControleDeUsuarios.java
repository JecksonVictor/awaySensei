package tools;

import DAO.UsersDao;
import DAO.UsersMemoryDao;
import core.Faixa;
import core.Pupilo;
import core.Sensei;
import java.util.ArrayList;
import java.util.Random;

import core.Usuario;
import javafx.scene.image.Image;

public class ControleDeUsuarios {
    private final UsersDao users;
    private final Autenticador aut;
    private final Usuario usuarioLogado;
    
    
    public ControleDeUsuarios() {
        this.users = new UsersMemoryDao();
        this.aut = new Autenticador();
        this.usuarioLogado = new Usuario();
    }

    public void addPupilo(Pupilo pup_) {
        // Verifica se o usuário já existe na base de dados, 
        // caso ainda não exista é cadastrado
        if (this.aut.autenticar(this, pup_) == null) {
            Random rand = new Random();
            pup_.setUniqueID(String.valueOf(rand.nextInt(100)));
            pup_.setFaixa(new Faixa("branca"));
            pup_.setImage(new Image("/imgs/user.png"));
            
            pup_.setSenseiName("Não tem");
            this.users.addUser(pup_);
        }
    }
    
    public void addSensei(Sensei sen_) {
        
        // Verifica se o usuário já existe na base de dados, 
        // caso ainda não exista é cadastrado
        if (this.aut.autenticar(this, sen_) == null) {
            Random rand = new Random();
            sen_.setUniqueID(String.valueOf(rand.nextInt(100)));
            sen_.setImage(new Image("/imgs/user.png"));
            this.users.addUser(sen_);
        }
    }

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.users.getUsers();
    }
}
