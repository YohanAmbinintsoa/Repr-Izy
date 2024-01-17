package ITU.Baovola.Gucci.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Etat;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/Etats")
public class EtatController extends BaseController{
    
    @GetMapping
    @Authority(role = Role.ADMIN)
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Etat> allEtats = requester.select(null, new Etat());
            data.addData(allEtats);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping
    @Authority(role = Role.ADMIN)
    public ResponseData insert(HttpServletRequest req) {
        ResponseData data = new ResponseData();
        try {
            Etat Etat=new Etat(req.getParameter("etat"));
            data.addData(requester.insert(null, Etat));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("/{id}")
    @Authority(role = Role.ADMIN)
    public ResponseData getById(@PathVariable("id") String id) {
        ResponseData data = new ResponseData();
        try {
            Etat Etat = new Etat();
            Etat.setId(id);
            List<Etat> allEtats = requester.select(null, Etat);
            data.addData(allEtats.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PutMapping("/{id}")
    @Authority(role = Role.ADMIN)
    public ResponseData update(@PathVariable("id") String id, HttpServletRequest req){
        ResponseData data = new ResponseData();
        try {
            Etat etat=new Etat(req.getParameter("etat"));
            etat=this.requester.update(null, id, etat);
            data.addData(etat);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @DeleteMapping("/{id}")
    @Authority(role = Role.ADMIN)
    public ResponseData delete(@PathVariable("id") String id){
        ResponseData data = new ResponseData();
        try {
            Etat etat=new Etat();
            this.requester.delete(null, id, etat);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

}
