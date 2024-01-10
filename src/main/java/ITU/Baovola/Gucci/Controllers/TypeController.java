package ITU.Baovola.Gucci.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Type;
import yohx.DAO.DAO;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Types")
public class TypeController {

    private final DAO requester = new DAO("postgres", "root", "final", "postgresql");

    @GetMapping
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Type> allTypes = requester.select(null, new Type());
            data.addData(allTypes);
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
            Type type=new Type(req.getParameter("type"), req.getParameter("idcategorie"));
            data.addData(requester.insert(null, type));
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
            Type type = new Type();
            type.setId(id);
            List<Type> allTypes = requester.select(null, type);
            data.addData(allTypes.get(0));
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
            Type type = new Type(req.getParameter("nom"), req.getParameter("fk_categorie"));
            type = this.requester.update(null, id, type);
            data.addData(type);
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
            Type type = new Type();
            this.requester.delete(null, id, type);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}

