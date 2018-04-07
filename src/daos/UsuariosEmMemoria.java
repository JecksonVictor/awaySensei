package daos;
import java.util.ArrayList;
import java.util.Iterator;

import core.Usuario;

public class UsuariosEmMemoria implements UsuarioDAO{
	private ArrayList<Usuario> DatabaseDeUsuarios;

	@Override
	public void cadastrar(Usuario usuario_){
		DatabaseDeUsuarios.add(usuario_);
	}

	@Override
	public void remover(Usuario usuario_) {
		DatabaseDeUsuarios.remove(usuario_);
		
	}

	@Override
	public boolean procurarUsuario(String usuario_) {
		 Iterator<Usuario> itr = this.DatabaseDeUsuarios.iterator();
		    while (itr.hasNext()) {
		      if(itr.next().getNomeDeUsuario() == usuario_);
		      	return true;
		    }
		return false;
	}

	@Override
	public ArrayList<Usuario> listaDeUsuarios() {
		return this.DatabaseDeUsuarios;		
	}

}
