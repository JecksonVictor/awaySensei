package core;

import java.io.Serializable;

public class Usuario extends Visitante implements Serializable{

	private String uniqueID;
	private String nomeDeUsuario;
	private String senha;
	private String nome;
	private String sobrenome;
	private String bio;
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	//Utilizando o padrão de projeto Strategy, o tipo de atualização vai variar de acordo com as
	//regras e opções na tela. Para cada tipo de atualização, uma estratégia diferente.
	
	public void atualizarPerfil(String tipo_) {
		AtualizadorDePerfil att;
		if(tipo_ == "Atualizar nome") {
			att = new AtualizarNome();
			att.atualizar(this);
		}
		
		if(tipo_ ==  "Atualizar bio") {
			att = new AtualizarBio();
			att.atualizar(this);
		}
	}
	
}
