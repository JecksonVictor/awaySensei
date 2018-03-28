package awaysensei.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import awaysensei.core.Usuario;

public class ControleDeUsuarios {

	private static ArrayList<Usuario> listaDeUsuarios;
	private static ArrayList<String> listaDeUsuariosOnline;
	
	public ControleDeUsuarios() {
		listaDeUsuarios = new ArrayList<Usuario>();
	}
	
	public void addUsuario(Usuario usuario_) {
		Random rand = new Random();
		usuario_.setUniqueID(String.valueOf(rand.nextInt(100)));
		listaDeUsuarios.add(usuario_);
	}

	public ArrayList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(ArrayList<Usuario> listaDeUsuarios) {
		listaDeUsuarios = listaDeUsuarios;
	}
	
	public void listarUsuarios() {		
		for (Iterator<Usuario> iterator = listaDeUsuarios.iterator(); iterator.hasNext(); ) {  
			Usuario u = iterator.next();  
			System.out.println (u.getNomeDeUsuario() + "-" + u.getSenha());
		}
	}

	public void setUsuarioOnline(String usuarioID){
		listaDeUsuariosOnline.add(usuarioID);
	}

	public void setUsuarioOffline(String usuarioID){
		for (String it : listaDeUsuariosOnline){
			if (usuarioID.equals(it)){
				listaDeUsuariosOnline.remove(usuarioID);
				break;
			}			
		}
	}
}
