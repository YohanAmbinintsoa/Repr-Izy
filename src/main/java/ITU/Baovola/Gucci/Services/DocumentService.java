package ITU.Baovola.Gucci.Services;

import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import ITU.Baovola.Gucci.Models.Annonce;
import ITU.Baovola.Gucci.Models.User;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class DocumentService {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongo){
        this.mongoTemplate=mongo;
    }

    public void updateFieldById(String id, int newFieldValue) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("etatAnnonce", newFieldValue);
        mongoTemplate.updateFirst(query, update, Annonce.class);
    }

    public List<Annonce> search(HttpServletRequest req) {
        Query query=new Query();
        Enumeration<String> names=req.getParameterNames();
        while (names.hasMoreElements()) {
            String element=names.nextElement();
            if (element!=null&&!element.equals("")) {
                Criteria critere=Criteria.where(element).is(req.getParameter(element));
                query.addCriteria(critere);
            }
        }
        return mongoTemplate.find(query, Annonce.class);
    }

    public List<Annonce> getValidatedUserAnnonce(User user){
        Query query=new Query();
        query.addCriteria(Criteria.where("user").is(user).and("etatannonce").is(10));
        return mongoTemplate.find(query, Annonce.class);
    }


    public long countNonValide(){
        Criteria criteria=Criteria.where("etatAnnonce").is("0");
        Query query = new Query(criteria);
        return mongoTemplate.count(query, "Annonce");
    }

    public long countUserAnnonce(String userid){
        Criteria  criteria=Criteria.where("user._id").is(userid);
        Query query=new Query(criteria);
        return mongoTemplate.count(query, "Annonce");
    }
}
