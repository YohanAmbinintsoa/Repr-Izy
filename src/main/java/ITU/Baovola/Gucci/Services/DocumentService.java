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
import ITU.Baovola.Gucci.Models.FavoriteAnnonce;
import ITU.Baovola.Gucci.Models.SearchModel;
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

    public List<Annonce> search(Query query) {
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
        System.out.println("USERID="+userid);
        Criteria criteria=Criteria.where("user._id").is(userid);
        Query query=new Query(criteria);
        return mongoTemplate.find(query, Annonce.class);
    }

    public List<Annonce> findProductsWithPriceRange(float min, float max) {
        Query query = new Query();
        query.addCriteria(Criteria.where("prix").gt(min).lt(max));
        query.with(Sort.by(Sort.Direction.ASC, "prix"));

        return mongoTemplate.find(query, Annonce.class);
    }

    public FavoriteAnnonce verifyFavorite(Annonce annonce,User user){
        Query query=new Query();
        query.addCriteria(Criteria.where("annonce._id").is(annonce.getId()));
        query.addCriteria(Criteria.where("user._id").is(user.getId()));
        return mongoTemplate.findOne(query, FavoriteAnnonce.class);
    }
}
