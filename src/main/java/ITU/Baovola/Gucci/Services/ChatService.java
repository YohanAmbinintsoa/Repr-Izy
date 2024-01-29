package ITU.Baovola.Gucci.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import ITU.Baovola.Gucci.Models.Conversation;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Repositories.ConversationRepository;
import ITU.Baovola.Gucci.Repositories.MessageRepository;

@Service
public class ChatService {
    @Autowired
    MongoTemplate mongo;
    @Autowired
    ConversationRepository convRep;
    @Autowired
    MessageRepository messRepo;

    public List<Conversation> getAllConversation(User user){
        List<Conversation> conv=this.getCurrentUserConversations(user);
        for (Conversation conversation : conv) {
            conversation.setMessages(messRepo.findByIdConversation(conversation.getId())); 
        }
        return conv;
    }

    public List<Conversation> getCurrentUserConversations(User user){
        Query query=new Query();
        query.addCriteria(Criteria.where("utilisateurs").elemMatch(Criteria.where("_id").is(user.getId())));
        return mongo.find(query, Conversation.class);
    }
}
