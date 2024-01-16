package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ITU.Baovola.Gucci.Models.FavoriteAnnonce;

public interface FavoriteRepository extends MongoRepository<FavoriteAnnonce,String>{
    
}
