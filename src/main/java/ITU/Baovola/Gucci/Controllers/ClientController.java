package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Security.Role;
import ITU.Baovola.Gucci.Services.DocumentService;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/Clients")
public class ClientController extends BaseController{
    @Autowired
    DocumentService service;

    @GetMapping()
    @Authority(role = Role.ADMIN)
    public ResponseData getAll() {
        ResponseData data=new ResponseData();
        try {
            User user=new User();
            user.setRole("USER");
            data.addData(this.requester.select(null, user));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("{id}")
    public ResponseData getById(@PathVariable("id") String iduser) {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            User user=new User();
            user.setId(iduser);
            User alefa=(User)this.requester.select(con, user).get(0);
            alefa.setVente(alefa.countVente(con));
            alefa.setAnnonce(service.countUserAnnonce(alefa.getId()));
            alefa.setMdp(null);
            data.addData(alefa);
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
    
}
