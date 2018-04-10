package tools;

import DAO.UsersDao;
import DAO.UsersMemoryDao;
import core.Pupilo;
import core.Sensei;
import java.util.ArrayList;
import java.util.Random;

import core.Usuario;

public class ControleDeUsuarios {
    private UsersDao users;

    public ControleDeUsuarios() {
        this.users = new UsersMemoryDao();
        
        Sensei sen1 = new Sensei("sen1", "123");
        Sensei sen2 = new Sensei("sen2", "123");
        
        this.users.addUser(sen1);
        this.users.addUser(sen2);
    }

    public void addPupilo(Pupilo usuario_) {
        Random rand = new Random();
        usuario_.setUniqueID(String.valueOf(rand.nextInt(100)));
        usuario_.setSenseiName("fulano");
        this.users.addUser(usuario_);
    }
    
    

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.users.getUsers();
    }
    
//    public void cadastrarAula (Pupilo pup) {
//        
//    }
}
