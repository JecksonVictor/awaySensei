package core;

public class Video {
        
    private String link;

    public Video(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public void executarVideo() {
        System.out.println("Executando video...");
    }
}
