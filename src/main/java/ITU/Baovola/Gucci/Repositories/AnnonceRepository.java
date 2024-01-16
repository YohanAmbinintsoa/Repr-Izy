package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ITU.Baovola.Gucci.Models.Annonce;
import ITU.Baovola.Gucci.Models.User;

import java.util.List;


public interface AnnonceRepository extends MongoRepository<Annonce, String>{
    List<Annonce> findByUser(User user);
    List<Annonce> findByEtatAnnonce(int etatAnnonce);
}
