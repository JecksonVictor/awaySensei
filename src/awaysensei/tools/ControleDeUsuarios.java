package awaysensei.tools;

import java.util.ArrayList;
import java.util.Iterator;
import awaysensei.core.Usuario;

public class ControleDeUsuarios {

	private ArrayList<Usuario> listaDeUsuarios;
	
	public void addUsuario(Usuario usuario_) {
		listaDeUsuarios.add(usuario_);
	}

	public ArrayList<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(ArrayList<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}
}
