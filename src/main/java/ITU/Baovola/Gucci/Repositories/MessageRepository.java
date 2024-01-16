package ITU.Baovola.Gucci.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ITU.Baovola.Gucci.Models.Message;
import java.util.List;


@Repository
public interface MessageRepository extends MongoRepository<Message,String>{
    List<Message> findByIdConversation(String idConversation);
}
