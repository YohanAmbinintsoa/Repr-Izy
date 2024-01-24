package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.DAO.*;
import ITU.Baovola.Gucci.Security.MyContext;

@Table(name = "parametrages")
public class Parametrages {
    @Id(name = "idparametre",idtype = Generation.AUTO)
    String id;
    @Column(name = "prixmin")
    Float prixmin;
    @Column(name = "primax")
    Float prixmax;
    @Column(name = "pourcentage")
    Float pourcentage;

    public Parametrages(Float prixmin, Float prixmax, Float pourcentage) {
        this.prixmin = prixmin;
        this.prixmax = prixmax;
        this.pourcentage = pourcentage;
    }

    public static Parametrages getParametrages(float prix) throws Exception{
        Connection con=MyContext.getRequester().connect();
        Parametrages param=null;
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from parametrages where prixmin<="+prix+" and prixmax>="+prix);
        if (res.next()) {
            param=new Parametrages();
            param.setPrixmax(res.getFloat("prixmax"));
            param.setPrixmin(res.getFloat("prixmin"));
            param.setPourcentage(res.getFloat("pourcentage"));
            param.setId(res.getString("idparametre"));
        }
        return param;
    }
    
    public Parametrages() {}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Float getPrixmin() {
        return prixmin;
    }
    public void setPrixmin(Float prixmin) {
        this.prixmin = prixmin;
    }
    public Float getPrixmax() {
        return prixmax;
    }
    public void setPrixmax(Float prixmax) {
        this.prixmax = prixmax;
    }
    public Float getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

}
