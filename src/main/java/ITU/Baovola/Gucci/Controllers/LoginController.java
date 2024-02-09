package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.JwtResponse;
import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.User;
import ITU.Baovola.Gucci.Security.JwtUtils;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Services.DocumentService;
import ITU.Baovola.Gucci.Services.Photo;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/v1/auth")
public class LoginController extends BaseController{
    @Autowired
    JwtUtils utils;
    @Autowired
    private DocumentService service;
    
    @PostMapping("/login")
    public ResponseData loginUser(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            User user=User.login(req.getParameter("username"), req.getParameter("mdp"), this.requester);
            if (user==null) {
                data.setError("Verifier vos identifiants!");
                return data;
            }
            String token=utils.generateJwtToken(user);
            user.setMdp(null);
            user.countVente(con);
            user.setAnnonce(service.countUserAnnonce(user.getId()));
            data.addData(user);
            data.addData(new JwtResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return data;
    }

    @PostMapping("/register")
    public ResponseData register(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            User user=new User();
            user.setNom(req.getParameter("nom"));
            user.setPrenom(req.getParameter("prenom"));
            user.setCIN(req.getParameter("cin"));
            if (req.getParameter("dtn")!=null&&!req.getParameter("dtn").equals("")) {
                user.setDtn(Date.valueOf(req.getParameter("dtn")));
            }
            user.setNomUser(req.getParameter("username"));
            user.setMdp(req.getParameter("mdp"));
            user.setRole("USER");
            System.out.println(user.getRole());
            user.setDateInscription(new Date(System.currentTimeMillis()));
            String image=this.imageService.upload(new Photo(req.getParameter("image"), "user.png"));
            user.setImage(image);
            user=this.requester.insert(null, user);
            String token=utils.generateJwtToken(user);
            user.setMdp(null);
            user.setVente(user.countVente(con));
            user.setAnnonce(service.countUserAnnonce(user.getId()));
            data.addData(user);
            data.addData(new JwtResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            data.setError(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return data;
    }
    
}
