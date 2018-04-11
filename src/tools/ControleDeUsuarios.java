package tools;

import DAO.UsersDao;
import DAO.UsersMemoryDao;
import core.Pupilo;
import core.Sensei;
import java.util.ArrayList;
import java.util.Random;

import core.Usuario;
import java.util.Observable;
import java.util.Observer;
import vision.LoginFXMLController;

public class ControleDeUsuarios extends Observable implements Observer{
    private UsersDao users;

    private Autenticador aut;
    
    
    public ControleDeUsuarios() {
        this.users = new UsersMemoryDao();
        this.aut = new Autenticador();
    }

    public void addPupilo(Pupilo pup_) {
        // Verifica se o usuário já existe na base de dados, 
        // caso ainda não exista é cadastrado
        if (this.aut.autenticar(this, pup_) == null) {
            Random rand = new Random();
            pup_.setUniqueID(String.valueOf(rand.nextInt(100)));
            pup_.setSenseiName("Não tem");
            this.users.addUser(pup_);

            super.setChanged();
            super.notifyObservers(pup_);
        }
    }
    
    public void addSensei(Sensei sen_) {
        
        // Verifica se o usuário já existe na base de dados, 
        // caso ainda não exista é cadastrado
        if (this.aut.autenticar(this, sen_) == null) {
            Random rand = new Random();
            sen_.setUniqueID(String.valueOf(rand.nextInt(100)));
            this.users.addUser(sen_);

            super.setChanged();
            super.notifyObservers(sen_);
        }
    }

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.users.getUsers();
    }
    
    @Override
    public void update(Observable theObservable, Object arg) {
        if (theObservable instanceof LoginFXMLController) {
            if(arg instanceof Pupilo) {
                this.addPupilo((Pupilo) arg);
            }
        } 
    }
}
