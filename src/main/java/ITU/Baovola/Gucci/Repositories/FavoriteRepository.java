package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ITU.Baovola.Gucci.Models.FavoriteAnnonce;

@Repository
public interface FavoriteRepository extends MongoRepository<FavoriteAnnonce,String>{
    
}
