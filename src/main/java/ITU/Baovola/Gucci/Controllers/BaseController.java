package ITU.Baovola.Gucci.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import ITU.Baovola.Gucci.DAO.DAO;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Services.ImageService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BaseController {
    // DAO requester=new DAO("YohanAmbinintsoa", "u4ixcVJHQOD3", "ep-shrill-voice-12867324.us-east-2.aws.neon.tech","final", "postgresql");
    DAO requester;
    @Autowired
    ImageService imageService;
    public BaseController(){
        this.requester=MyContext.getRequester();
    }
}
