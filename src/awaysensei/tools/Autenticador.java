package awaysensei.tools;

import awaysensei.core.Usuario;
import awaysensei.tools.ControleDeUsuarios;

import java.util.ArrayList;
import java.lang.*;

public class Autenticador {
    public Autenticador(){
    }

    public String autenticar(ControleDeUsuarios controlador, Usuario usuario){
        ArrayList<Usuario> listaUsuarios = controlador.getListaDeUsuarios();
        for (Usuario it : listaUsuarios) {
            if (usuario.getNomeDeUsuario().equals(it.getNomeDeUsuario())){
                if (usuario.getSenha().equals(it.getSenha())){
                    return (usuario.getUniqueID());
                }
                break;
            }
        }
        return null;
    }
}