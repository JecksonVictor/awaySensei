package daos;

import java.util.ArrayList;

import core.Usuario;

public interface UsuarioDAO {

	public void cadastrar(Usuario usuario_);
	public void remover(Usuario usuario_);
	public boolean procurarUsuario(String usuario_);
	public ArrayList<Usuario> listaDeUsuarios();
}
