package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Categorie;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/Categories")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategorieController extends BaseController{
   
    @GetMapping
    @Authority(role = Role.ADMIN)
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
    @Authority(role = Role.ADMIN)
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
    @Authority(role = Role.ADMIN)
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
    @Authority(role = Role.ADMIN)
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
    @Authority(role = Role.ADMIN)
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
