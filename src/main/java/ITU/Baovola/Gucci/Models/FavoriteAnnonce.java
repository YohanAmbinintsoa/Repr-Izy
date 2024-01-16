package ITU.Baovola.Gucci.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorites")
public class FavoriteAnnonce {
    @Id
    String id;
    Annonce annonce;
    User user;
    
    public FavoriteAnnonce(Annonce annonce, User user) {
        this.annonce = annonce;
        this.user = user;
    }

    public FavoriteAnnonce() {
    }

    public Annonce getAnnonce() {
        return annonce;
    }
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
