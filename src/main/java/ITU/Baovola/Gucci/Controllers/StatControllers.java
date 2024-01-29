package ITU.Baovola.Gucci.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITU.Baovola.Gucci.DTO.ResponseData;
import ITU.Baovola.Gucci.Models.Dashboard;
import ITU.Baovola.Gucci.Models.UserStatistics;
import ITU.Baovola.Gucci.Security.Authority;
import ITU.Baovola.Gucci.Security.MyContext;
import ITU.Baovola.Gucci.Security.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/stats")
public class StatControllers extends BaseController{
    
    @GetMapping("user")
    @Authority(role = Role.ADMIN)
    public ResponseData userStat(HttpServletRequest req) {
        ResponseData data=new ResponseData();
        try {
            data.addData(UserStatistics.getMonthlyStatistics(req.getParameter("year")));
        } catch (Exception e) {
            e.printStackTrace();
           data.setError(e.getMessage());
        }
        return data;
    }

    @GetMapping("dashboard")
    public ResponseData dashboard(){
        ResponseData data=new ResponseData();
        Connection con=null;
        try {
            con=MyContext.getRequester().connect();
            data.addData(new Dashboard(con));
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
