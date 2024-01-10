package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Categorie;
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
@RequestMapping("/api/v1/Categories")
public class CategorieController {
    DAO requester=new DAO("postgres", "root", "final", "postgresql");
    
    @GetMapping
    public ResponseData getAll() {
        ResponseData data=new ResponseData();
        try {
            List<Categorie> allCat=requester.select(null, new Categorie());
            data.addData(allCat);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping
    public ResponseData insert(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
            Categorie cat=new Categorie(req.getParameter("categorie"));
            data.addData(requester.insert(null, cat));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("/{id}")
    public ResponseData getById(@PathVariable("id") String id) {
        ResponseData data=new ResponseData();
        try {
            Categorie cat=new Categorie();
            cat.setId(id);
            List<Categorie> allCat=requester.select(null, cat);
            data.addData(allCat.get(0));
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
            Categorie categorie = new Categorie(req.getParameter("categorie"));
            categorie = this.requester.update(null, id, categorie);
            data.addData(categorie);
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
            Categorie categorie = new Categorie();
            this.requester.delete(null, id, categorie);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}
