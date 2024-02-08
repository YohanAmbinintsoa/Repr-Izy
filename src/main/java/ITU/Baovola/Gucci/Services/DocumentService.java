package ITU.Baovola.Gucci.Services;

import java.util.Enumeration;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Query query = new Query();
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String element = names.nextElement();
            String value = req.getParameter(element);
            if (element != null && !element.equals("")) {
                System.out.println("Misy le izy="+value);
                try {
                    ObjectId objectId = new ObjectId(value);
                    Criteria criteria = Criteria.where(element+"._id").is(value);
                    query.addCriteria(criteria);
                } catch (IllegalArgumentException e) {
                    System.out.println("Fq tsy neyty ehhh");
                    // Si ce n'est pas un ObjectId, traitez-le comme une valeur normale
                    Criteria criteria = Criteria.where(element).is(value);
                    query.addCriteria(criteria);
                }
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

    public List<Annonce> getAnnoncesUser(String userid){
        Criteria  criteria=Criteria.where("user._id").is(userid);
        Query query=new Query(criteria);
        return mongoTemplate.find(query, Annonce.class);
    }

    public List<Annonce> findProductsWithPriceRange(float min, float max) {
        Query query = new Query();
        query.addCriteria(Criteria.where("prix").gt(min).lt(max));
        query.with(Sort.by(Sort.Direction.ASC, "prix"));

        return mongoTemplate.find(query, Annonce.class);
    }
}
