package ITU.Baovola.Gucci.Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Annonce;
import ITU.Baovola.Gucci.Models.Categorie;
import ITU.Baovola.Gucci.Models.Energie;
import ITU.Baovola.Gucci.Models.Etat;
import ITU.Baovola.Gucci.Models.FavoriteAnnonce;
import ITU.Baovola.Gucci.Models.Marque;
import ITU.Baovola.Gucci.Models.Modele;
import ITU.Baovola.Gucci.Models.Transmission;
import ITU.Baovola.Gucci.Models.Type;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Repositories.AnnonceRepository;
import ITU.Baovola.Gucci.Repositories.FavoriteRepository;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Security.Role;
import ITU.Baovola.Gucci.Services.DocumentService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/Annonces")
public class AnnonceController extends BaseController{
    @Autowired
    private AnnonceRepository repo;

    @Autowired
    private DocumentService service;

    @Autowired
    private FavoriteRepository favRepo;


    @PostMapping()
    @Authority(role = Role.USER)
    public ResponseData insertAnnonce(HttpServletRequest req) {
        String[] files=req.getParameterValues("images");
        ResponseData data=new ResponseData();
        String categorie=req.getParameter("idcategorie");
        String marque=req.getParameter("idmarque");
        String energie=req.getParameter("idenergie");
        String etat=req.getParameter("idetat");
        String modele=req.getParameter("idmodele");
        String transmission=req.getParameter("idtransmission");
        String type=req.getParameter("idtype");
        String prix=req.getParameter("prix");
        String annee=req.getParameter("annee");
        String description=req.getParameter("description");
        String place=req.getParameter("place");
        String kilometrique=req.getParameter("kilometrique");
        String cylindre=req.getParameter("cylindre");
        String puissance=req.getParameter("puissance");
        String nbrCylindre=req.getParameter("nbrCylindre");
        String libelle=req.getParameter("libelle");
        Connection con=null;
        try {
            con=this.requester.connect();
            Annonce annonce=new Annonce(con,this.requester,categorie,marque,modele,prix,annee,description,type,place,
                            kilometrique,etat,transmission,energie,cylindre,puissance,nbrCylindre,libelle);
            User user=MyContext.getUser();
            annonce.setUser(user);
            annonce.setEtatAnnonce(0);
            annonce.setDate(new Date(System.currentTimeMillis()));
            List<String> str=this.imageService.upload(files);
            annonce.setImages(str);
            data.addData(repo.save(annonce));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            } 
        }
        return data;
    }

    @GetMapping()
    public ResponseData getAll() {
        ResponseData data=new ResponseData();
        try {
            data.addData(repo.findAll());
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        }        
        return data;
    }

    @GetMapping("/{id}")
    @Authority(role = {Role.ADMIN,Role.USER})
    public ResponseData findId(@PathVariable("id") String id) {
        ResponseData data=new ResponseData();
        try {
            data.addData(repo.findById(id));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        }        
        return data;
    }

    @GetMapping("validated")
    public ResponseData getValidatedAnnonce() {
        ResponseData data=new ResponseData();
        try {
            data.addData(repo.findByEtatAnnonce(10));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        }        
        return data;
    }

    @PutMapping("validate/{id}")
    @Authority(role = Role.ADMIN)
    public ResponseData validate(@PathVariable String id) {
         ResponseData data=new ResponseData();
        try {
            this.service.updateFieldById(id, 10);
            data.addData("Field Validated");
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        }        
        return data;
    }

    @PostMapping("/addFavorite/{id}")
    @Authority(role = Role.USER)
    public ResponseData addFavorite(@PathVariable("id") String id) {
        ResponseData data=new ResponseData();
        try {
           User currentUser=MyContext.getUser();
           Annonce annonce=repo.findById(id).get();
           FavoriteAnnonce fav=new FavoriteAnnonce(annonce, currentUser);
           data.addData(favRepo.save(fav));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        } 
        return data;
    }

    @GetMapping("/favorites")
    @Authority(role = Role.USER)
    public ResponseData getFavorites() {
        ResponseData data=new ResponseData();
        try {
            data.addData(favRepo.findByUser(MyContext.getUser()));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("/myAnnonces")
    @Authority(role = Role.USER)
    public ResponseData getMyAnnonces(){
        ResponseData data=new ResponseData();
        try {
           service.getAnnoncesUser(MyContext.getUser().getId());
        } catch (Exception e) {
           e.printStackTrace();
        }
        return data;
    }
    

    @GetMapping("newAnnonce")
    public ResponseData newAnnonce() {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=this.requester.connect();
            data.addData(requester.select(con, new Categorie()));
            data.addData(requester.select(con, new Marque()));
            data.addData(requester.select(con, new Etat()));
            data.addData(requester.select(con, new Energie()));
            data.addData(requester.select(con, new Modele()));
            data.addData(requester.select(con, new Transmission()));
            data.addData(requester.select(con, new Type()));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            } 
        }
        return data;
    }

    @GetMapping("/search/{iduser}")
    public ResponseData searchUser(@PathVariable("iduser") String id) {
        ResponseData data=new ResponseData();
        try {
           User user=new User();
           user.setId(id);
           user=(User)requester.select(null, user).get(0);
           data.addData(this.service.getValidatedUserAnnonce(user));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        } 
        return data;
    }

    @GetMapping("/client/{iduser}")
    @Authority(role = {Role.ADMIN,Role.USER})
    public ResponseData getClient(@PathVariable("iduser") String id) {
        ResponseData data=new ResponseData();
        try {
            data.addData(this.service.getAnnoncesUser(id));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping("/search")
    @Authority(role = {Role.ADMIN,Role.USER})
    public ResponseData search(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
           data.addData(this.service.search(req));
        } catch (Exception e) { 
            e.printStackTrace();
            data.setError(e.getMessage());
        } 
        return data;
    }
}
