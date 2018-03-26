package awaysensei.core;

public class Visitante {

		private String id;
		private boolean estaCadastrado;
		
		public Visitante(){
			this.estaCadastrado = false;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public boolean isEstaCadastrado() {
			return estaCadastrado;
		}
		public void setEstaCadastrado(boolean estaCadastrado) {
			this.estaCadastrado = estaCadastrado;
		}
		public Usuario cadastrarUsuario(String nomeDeUsuario, String senha) {
			return new Usuario(nomeDeUsuario, senha);
		}
}
