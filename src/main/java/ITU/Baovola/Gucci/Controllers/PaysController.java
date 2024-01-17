package ITU.Baovola.Gucci.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Pays;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Pays")
public class PaysController extends BaseController{

    @GetMapping
    @Authority(role = Role.ADMIN)
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Pays> allPays = requester.select(null, new Pays());
            data.addData(allPays);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping(consumes = { "multipart/form-data" })
    @Authority(role = Role.ADMIN)
    public ResponseData insert(HttpServletRequest req, @RequestParam("drapeau") MultipartFile file) {
        ResponseData data = new ResponseData();
        try {
            Pays pays=new Pays(req.getParameter("pays"));
            byte[] fileContent=file.getBytes();
            String base64=Base64.getUrlEncoder().encodeToString(fileContent);
            pays.setPath(base64);
            data.addData(requester.insert(null, pays));
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
            Pays pays = new Pays();
            pays.setId(id);
            List<Pays> allPays = requester.select(null, pays);
            data.addData(allPays.get(0));
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
            Pays pays = new Pays(req.getParameter("nom"));
            pays = this.requester.update(null, id, pays);
            data.addData(pays);
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
            Pays pays = new Pays();
            this.requester.delete(null, id, pays);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}

