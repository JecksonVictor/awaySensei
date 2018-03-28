package awaysensei.core;

public class Pupilo extends Usuario{

	//Testar com 1 treino, mas aqui será um array de treinos
	public Treino treino1_;

	public Pupilo(String usuario, String senha) {
		super(usuario, senha);
	}
	public Treino getTreino1_() {
		return treino1_;
	}

	public void setTreino1_(Treino treino1_) {
		this.treino1_ = treino1_;
	}
}
