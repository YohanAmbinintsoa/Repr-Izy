package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Categorie_Marque;
import ITU.Baovola.Gucci.Models.Marque;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;
import ITU.Baovola.Gucci.Services.Photo;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/Marques")
public class MarqueController extends BaseController{
     
    @GetMapping
    @Authority(role = Role.ADMIN)
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Marque> allMarques = requester.select(null, new Marque());
            data.addData(allMarques);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @Authority(role = Role.ADMIN)
    public ResponseData insert(HttpServletRequest req, @RequestParam("image") String file) {
        ResponseData data = new ResponseData();
        try {
            Marque marque = new Marque(req.getParameter("marque"), req.getParameter("idpays"));
            Photo photo=new Photo(file, "marque.png");
            String base64 = this.imageService.upload(photo);
            marque.setPath(base64);
            data.addData(requester.insert(null, marque));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping("addCategorie")
    @Authority(role = Role.ADMIN)
    public ResponseData postMethodName(HttpServletRequest req, @RequestParam("marque") String id) {
        ResponseData data=new ResponseData();
        try {
            String[] idCat=req.getParameterValues("categorie");
            for (int i = 0; i < idCat.length; i++) {
                Categorie_Marque mq=new Categorie_Marque(id, idCat[i]);
                this.requester.insert(null, mq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    

    @GetMapping("/{id}")
    @Authority(role = Role.ADMIN)
    public ResponseData getById(@PathVariable("id") String id) {
        ResponseData data = new ResponseData();
        try {
            Marque marque = new Marque();
            marque.setId(id);
            List<Marque> allMarques = requester.select(null, marque);
            data.addData(allMarques.get(0));
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
            Marque marque = new Marque(req.getParameter("marque"), req.getParameter("pays"));
            marque = this.requester.update(null, id, marque);
            data.addData(marque);
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
            Marque marque = new Marque();
            this.requester.delete(null, id, marque);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}
