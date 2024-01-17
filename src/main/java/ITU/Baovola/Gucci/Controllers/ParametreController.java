package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Parametrages;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.Role;
import jakarta.servlet.http.HttpServletRequest;
import yohx.DAO.DAO;

import org.springframework.web.bind.annotation.PostMapping;

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
            // TODO: handle exception
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
}