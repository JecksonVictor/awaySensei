package tools;

import core.Sensei;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import core.Usuario;
import daos.SaveUser;

public class ControleDeUsuarios {

	private ArrayList<Usuario> listaDeUsuarios;
	private ArrayList<String> listaDeUsuariosOnline;
        private SaveUser saver;
	
	public ControleDeUsuarios() {
            this.listaDeUsuarios = new ArrayList<Usuario>();
            this.saver = new SaveUser();
            this.listaDeUsuarios = new ArrayList<Usuario>();
            ArrayList<Usuario> aux = this.saver.getList(); 
            if(aux != null){
                this.listaDeUsuarios = aux;
            }
            
            Sensei sen1 = new Sensei("sensei1", "123");
            Sensei sen2 = new Sensei("sensei2", "123");
            
            this.addUsuario(sen1);
            this.addUsuario(sen2);
            
            for(Usuario user: this.listaDeUsuarios){
                System.out.println("Nome: "+user.getNomeDeUsuario()+" Senha: "+user.getSenha() +" ID: "+user.getUniqueID());
            }
	}
	
	public void addUsuario(Usuario usuario_) {
            Random rand = new Random();
            usuario_.setUniqueID(String.valueOf(rand.nextInt(100)));
            this.listaDeUsuarios.add(usuario_);
            
            for(Usuario user: this.listaDeUsuarios){
                System.out.println("Nome: "+user.getNomeDeUsuario()+" Senha: "+user.getSenha() +" ID: "+user.getUniqueID());
            }
            
            this.saver.save(this.listaDeUsuarios);
	}

	public ArrayList<Usuario> getListaDeUsuarios() {
            return this.listaDeUsuarios;
	}

	public void setListaDeUsuarios(ArrayList<Usuario> listaDeUsuarios) {
            this.listaDeUsuarios = listaDeUsuarios;
	}
	
	public void listarUsuarios() {		
            for (Iterator<Usuario> iterator = this.listaDeUsuarios.iterator(); iterator.hasNext(); ) {  
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
