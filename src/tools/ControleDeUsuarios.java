package tools;

import DAO.UsersDao;
import DAO.UsersFileDao;
import core.Sensei;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import core.Usuario;

public class ControleDeUsuarios {

	private ArrayList<Usuario> listaDeUsuarios;
        private UsersDao saver;
	
	public ControleDeUsuarios() {
            this.listaDeUsuarios = new ArrayList<Usuario>();
            this.saver = new UsersFileDao();
            ArrayList<Usuario> aux = this.saver.getUsers();
            if(aux != null){
                this.listaDeUsuarios = aux;
            }
            
            Sensei sen1 = new Sensei("sensei1", "123");
            Sensei sen2 = new Sensei("sensei2", "123");
            
            this.addUsuario(sen1);
            this.addUsuario(sen2);
	}
	
	public void addUsuario(Usuario usuario_) {
            Random rand = new Random();
            usuario_.setUniqueID(String.valueOf(rand.nextInt(100)));
            this.listaDeUsuarios.add(usuario_);
            this.saver.addUser(this.listaDeUsuarios);
	}

	public ArrayList<Usuario> getListaDeUsuarios() {
            return this.listaDeUsuarios;
	}
}
