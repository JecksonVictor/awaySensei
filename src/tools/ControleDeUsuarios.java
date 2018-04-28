package tools;

import DAO.UsersDao;
import DAO.UsersMemoryDao;
import core.Faixa;
import core.Pupilo;
import core.Sensei;
import core.Treino;
import java.util.ArrayList;
import java.util.Random;

import core.Usuario;
import javafx.scene.image.Image;

public class ControleDeUsuarios {
    private final UsersDao users;
    private final Autenticador aut;
    private Usuario usuarioLogado;
    
    
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
    
    public void updateUser (Usuario user) {
        this.users.updateUser(user);
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.users.getUsers();
    }
    
    public ArrayList<Sensei> getListaDeSenseis() {
        ArrayList<Sensei> senseis = new ArrayList<>();
        for (Usuario user : this.users.getUsers()) {
            if (user instanceof Sensei) {
                senseis.add((Sensei) user);
            }
        }
        
        return senseis;
    }
    
    public ArrayList<Pupilo> getListaDePupilos() {
        ArrayList<Pupilo> pupilos = new ArrayList<>();
        for (Usuario user : this.users.getUsers()) {
            if (user instanceof Pupilo) {
                pupilos.add((Pupilo) user);
            }
        }
        
        return pupilos;
    }
    
    public void addTreino(String name, Treino treino) {
        for (Usuario user : this.users.getUsers()) {
            if (user instanceof Pupilo) {
                if (user.getNomeDeUsuario() == name) {
                    ((Pupilo)user).addTreino(treino);
                    return;
                }
            }
        }
    }
}
