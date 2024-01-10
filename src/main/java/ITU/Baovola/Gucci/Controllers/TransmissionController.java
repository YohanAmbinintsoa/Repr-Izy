package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Transmission;
import yohx.DAO.DAO;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Transmissions")
public class TransmissionController {

    private final DAO requester = new DAO("postgres", "root", "final", "postgresql");

    @GetMapping
    public ResponseData getAll() {
        ResponseData data = new ResponseData();
        try {
            List<Transmission> allTransmissions = requester.select(null, new Transmission());
            data.addData(allTransmissions);
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
            Transmission transmission=new Transmission(req.getParameter("transmission"));
            data.addData(requester.insert(null, transmission));
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
            Transmission transmission = new Transmission();
            transmission.setId(id);
            List<Transmission> allTransmissions = requester.select(null, transmission);
            data.addData(allTransmissions.get(0));
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
            Transmission transmission = new Transmission(req.getParameter("nom"));
            transmission = this.requester.update(null, id, transmission);
            data.addData(transmission);
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
            Transmission transmission = new Transmission();
            this.requester.delete(null, id, transmission);
            data.addData("Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
}

