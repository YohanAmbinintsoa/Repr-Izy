package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ITU.Baovola.Gucci.Models.Conversation;
import java.util.List;
import ITU.Baovola.Gucci.Models.User;


@Repository
public interface ConversationRepository extends MongoRepository<Conversation,String>{
   
}
