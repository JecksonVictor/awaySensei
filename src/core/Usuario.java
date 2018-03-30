package core;

import java.io.Serializable;

public class Usuario extends Visitante implements Serializable{

	private String uniqueID;
	private String nomeDeUsuario;
	private String senha;
	
	public Usuario() {		
	}
	
	public Usuario(String nomeDeUsuario_,String senha_) {
            this.nomeDeUsuario = nomeDeUsuario_;
            this.senha = senha_;
	}

	public String getUniqueID() {
            return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
            this.uniqueID = uniqueID;
	}

	public String getNomeDeUsuario() {
            return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
            this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getSenha() {
            return senha;
	}

	public void setSenha(String senha) {
            this.senha = senha;
	}
}
