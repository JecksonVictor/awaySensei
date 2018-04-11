package core;

public class Video {
    private String path;
    private String descricao;
    
    public Video(String path, String descricao){
        this.path = path;
        this.descricao = descricao;
    }

<<<<<<< HEAD
	public void executarVideo() {
            System.out.println("Executando video...");
	}
=======
    public void executarVideo() {
	System.out.println("Executando video...");
    }

    public String getPath() {
        return path;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
>>>>>>> master
}
