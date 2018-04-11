package core;

public class Treino {

	private Video video;
	private String descricao;
	
	public Treino() {	
	}
	
	public Treino(Video video_, String descricao_) {
            this.video = video_;
            this.descricao = descricao_;
	}
	
	public void exibirTreino() {
            this.video.executarVideo();
	}

	public Video getVideo() {
            return video;
	}

	public void setVideo(Video video) {
            this.video = video;
	}

	public String getDescricao() {
            return descricao;
	}

	public void setDescricao(String descricao) {
            this.descricao = descricao;
	}
}
