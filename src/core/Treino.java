package core;

import java.util.ArrayList;

public class Treino {

	private ArrayList<Video> videos;
	private String descricao;
	
	public Treino() {	
	}
	
<<<<<<< HEAD
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
=======
	public Treino(ArrayList<Video> video_, String descricao_) {
		this.videos = video_;
		this.descricao = descricao_;
	}

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
>>>>>>> master

	public String getDescricao() {
            return descricao;
	}

	public void setDescricao(String descricao) {
            this.descricao = descricao;
	}
}
