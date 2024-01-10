package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Categorie_Marque;
import ITU.Baovola.Gucci.Models.Marque;
import jakarta.servlet.http.HttpServletRequest;
import yohx.DAO.DAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/Marques")
public class MarqueController {
    DAO requester = new DAO("postgres", "root", "final", "postgresql");
    
    @GetMapping
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
    public ResponseData insert(HttpServletRequest req, @RequestParam("image") MultipartFile file) {
        ResponseData data = new ResponseData();
        try {
            Marque marque = new Marque(req.getParameter("marque"), req.getParameter("idpays"));
            byte[] fileContent = file.getBytes();
            String base64 = Base64.getUrlEncoder().encodeToString(fileContent);
            marque.setPath(base64);
            data.addData(requester.insert(null, marque));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping("addCategorie")
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
