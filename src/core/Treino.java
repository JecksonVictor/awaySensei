package core; 
 
import java.util.ArrayList; 
 
public class Treino { 
 
    private ArrayList<Video> videos;
    private String descricao; 

    public Treino() {   
    }
        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}