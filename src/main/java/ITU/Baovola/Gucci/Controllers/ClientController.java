package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1/Clients")
public class ClientController extends BaseController{

    @GetMapping()
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
        try {
            User user=new User();
            user.setId(iduser);
            data.addData(this.requester.select(null, user));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        }
        return data;
    }
    
    
}
