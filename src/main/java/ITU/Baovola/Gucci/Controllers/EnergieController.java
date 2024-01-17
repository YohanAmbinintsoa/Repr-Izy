package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Energie;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Energies")
public class EnergieController extends BaseController{

    @GetMapping
    @Authority(role = Role.ADMIN)
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Energie> allEnergies = requester.select(null, new Energie());
            data.addData(allEnergies);
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
            Energie energie=new Energie(req.getParameter("energie"));
            data.addData(requester.insert(null, energie));
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
            Energie energie = new Energie();
            energie.setId(id);
            List<Energie> allEnergies = requester.select(null, energie);
            data.addData(allEnergies.get(0));
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
            Energie energie=new Energie(req.getParameter("energie"));
            energie=this.requester.update(null, id, energie);
            data.addData(energie);
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
            Energie etat=new Energie();
            this.requester.delete(null, id, etat);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

}

