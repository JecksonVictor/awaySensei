package tools;
import core.Usuario;
import java.util.ArrayList;

/**
 *
 * @author proae
 */
public class Autenticador {
    public Autenticador(){
    }

    public String autenticar(ControleDeUsuarios controlador, Usuario usuario){
        ArrayList<Usuario> listaUsuarios = controlador.getListaDeUsuarios();
        
        for (Usuario it : listaUsuarios) {
            if (usuario.getNomeDeUsuario().equals(it.getNomeDeUsuario())){
                if (usuario.getSenha().equals(it.getSenha())){
                    return it.getUniqueID();
                }
                return "SI";
            }
        }
        
        return null;
    }
}