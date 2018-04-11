package core;

public class AtualizarNome implements AtualizadorDePerfil{

	@Override
	public void atualizar(Usuario u_) {
		String nome_ = null; //Vai receber dado da tela
		String sobreNome_ = null; //Vai receber dado da tela
		u_.setNome(nome_);
		u_.setSobrenome(sobreNome_);	
	}

}
