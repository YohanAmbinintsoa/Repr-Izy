package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ITU.Baovola.Gucci.Models.FavoriteAnnonce;
import ITU.Baovola.Gucci.Models.User;

import java.util.List;


@Repository
public interface FavoriteRepository extends MongoRepository<FavoriteAnnonce,String>{
    List<FavoriteAnnonce> findByUser(User user);
}
