package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Parametrages;
import ITU.Baovola.Gucci.Models.SeuilPourcentage;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/Parametres")
public class ParametreController extends BaseController{  
  
    @PostMapping
    @Authority(role = Role.ADMIN)
    public ResponseData insert(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
            Float min=Float.parseFloat(req.getParameter("min"));
            Float max=Float.parseFloat(req.getParameter("max"));
            Float pourcentage=Float.parseFloat(req.getParameter("pourcentage"));
            Parametrages param=new Parametrages(min, max, pourcentage);
            data.addData(requester.insert(null, param));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping
    @Authority(role = Role.ADMIN)
    public ResponseData getParams() {
        ResponseData data=new ResponseData();
        try {
            data.addData(this.requester.select(null, new Parametrages()));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @PostMapping("/seuil")
    @Authority(role = Role.ADMIN)
    public ResponseData postMethodName(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
            float seuil=Float.parseFloat(req.getParameter("seuil"));
            float pourcentage=Float.parseFloat(req.getParameter("pourcentage"));
            SeuilPourcentage p=new SeuilPourcentage();
            p.setSeuil(seuil);
            p.setPourcentage(pourcentage);
            p.setDate(new Date(System.currentTimeMillis()));
            data.addData(this.requester.insert(null, p));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
    
}
