package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Annonce;
import ITU.Baovola.Gucci.Models.Parametrages;
import ITU.Baovola.Gucci.Models.Transactions;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Models.Vente;
import ITU.Baovola.Gucci.Repositories.AnnonceRepository;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Security.Role;
import ITU.Baovola.Gucci.Services.DocumentService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1/Ventes")
public class VenteController extends BaseController{
    @Autowired
    private AnnonceRepository repo;

    @Autowired
    private DocumentService service;

    @PostMapping
    @Authority(role = Role.USER)
    public ResponseData insertVente(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            Annonce annonce=repo.findById(req.getParameter("idannonce")).get();
            User acheteur=new User();
            acheteur.setCIN(req.getParameter("cin"));
            acheteur=(User)MyContext.getRequester().select(null, acheteur).get(0);
            User vendeur=(User) MyContext.getUser();
            float prix=Float.parseFloat(req.getParameter("prix"));
            Parametrages params=Parametrages.getParametrages(annonce.getPrix(),prix);
            Vente vente=new Vente(annonce.getId(), vendeur.getId(), acheteur.getId());
            MyContext.getRequester().insert(con, vente);
            float commission=params.getPourcentage()*prix/100;
            Transactions trans=new Transactions(commission, 0f, new Timestamp(System.currentTimeMillis()));
            MyContext.getRequester().insert(con, trans);
            service.updateFieldById(annonce.getId(), 20);
            con.commit();
            data.addData("Vente Reussie!");
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
    @Authority(role = Role.ADMIN)
    public ResponseData geVente() {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            List<Vente> ventes=this.requester.select(null,new Vente());
            for (Vente vente : ventes) {
                User vendeur=new User();
                vendeur.setId(vente.getVendeur());
                User acheteur=new User();
                acheteur.setId(vente.getAcheteur());
                vente.setVendeurUser((User)this.requester.select(null, vendeur).get(0));
                vente.setAcheteurUser((User)this.requester.select(null, acheteur).get(0));
            }
            data.addData(ventes);
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
        
}
