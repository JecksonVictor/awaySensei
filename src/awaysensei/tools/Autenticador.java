package awaysensei.tools;

import awaysensei.core.Usuario;
import awaysensei.tools.ControleDeUsuarios;

import java.util.ArrayList;
import java.lang.*;

public class Autenticador {
    public String autenticar(ControleDeUsuarios controlador, Usuario usuario){
        ArrayList<Usuario> listaUsuarios = controlador.getListaDeUsuarios();
        for (Usuario it : listaUsuarios) {
            if (equals(usuario.getNomeDeUsuario(), it.getNomeDeUsuario())){
                if (equals(usuario.getSenha(), it.getSenha())){
                    return (usuario.getUniqueID());
                }
                break;
            }
        }
        return null;
    }
}