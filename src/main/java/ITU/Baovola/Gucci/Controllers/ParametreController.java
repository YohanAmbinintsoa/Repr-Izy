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
            float seuilmin=Float.parseFloat(req.getParameter("seuilmin"));
            float pourcentagemin=Float.parseFloat(req.getParameter("pourcentagemin"));
            float seuilmax=Float.parseFloat(req.getParameter("seuilmax"));
            float pourcentagemax=Float.parseFloat(req.getParameter("pourcentagemax"));
            SeuilPourcentage p=new SeuilPourcentage();
            p.setSeuilmin(seuilmin);
            p.setSeuilmax(seuilmax);
            p.setPourcentagemin(pourcentagemin);
            p.setPourcentagemax(pourcentagemax);
            p.setDate(new Date(System.currentTimeMillis()));
            data.addData(this.requester.insert(null, p));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
    
}
