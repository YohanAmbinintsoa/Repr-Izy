package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;

import ITU.Baovola.Gucci.DAO.DAO;
import ITU.Baovola.Gucci.Security.MyContext;

@CrossOrigin
public class BaseController {
    // DAO requester=new DAO("YohanAmbinintsoa", "u4ixcVJHQOD3", "ep-shrill-voice-12867324.us-east-2.aws.neon.tech","final", "postgresql");
    DAO requester;
    public BaseController(){
        this.requester=MyContext.getRequester();
    }
}
