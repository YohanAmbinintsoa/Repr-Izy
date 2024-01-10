package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Modele;
import jakarta.servlet.http.HttpServletRequest;
import yohx.DAO.DAO;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/Modeles")
public class ModeleController {
    DAO requester = new DAO("postgres", "root", "final", "postgresql");
    
    @GetMapping
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Modele> allGammes = requester.select(null, new Modele());
            data.addData(allGammes);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping
    public ResponseData insert(HttpServletRequest req) {
        ResponseData data = new ResponseData();
        try {
            Modele gamme = new Modele(req.getParameter("gamme"),req.getParameter("idmarque"));
            data.addData(requester.insert(null, gamme));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("/{id}")
    public ResponseData getById(@PathVariable("id") String id) {
        ResponseData data = new ResponseData();
        try {
            Modele gamme = new Modele();
            gamme.setId(id);
            List<Modele> allGammes = requester.select(null, gamme);
            data.addData(allGammes.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PutMapping("/{id}")
    public ResponseData update(@PathVariable("id") String id, HttpServletRequest req) {
        ResponseData data = new ResponseData();
        try {
            Modele modele = new Modele(req.getParameter("modele"), req.getParameter("marque"));
            modele = this.requester.update(null, id, modele);
            data.addData(modele);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable("id") String id) {
        ResponseData data = new ResponseData();
        try {
            Modele modele = new Modele();
            this.requester.delete(null, id, modele);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}
