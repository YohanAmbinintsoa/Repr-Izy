package ITU.Baovola.Gucci.Controllers;

import ITU.Baovola.Gucci.Security.MyContext;
import yohx.DAO.DAO;

public class BaseController {
    // DAO requester=new DAO("YohanAmbinintsoa", "u4ixcVJHQOD3", "ep-shrill-voice-12867324.us-east-2.aws.neon.tech","final", "postgresql");
    DAO requester;
    public BaseController(){
        this.requester=MyContext.getRequester();
    }
}