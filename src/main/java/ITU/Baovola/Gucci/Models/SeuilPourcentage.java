package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.DAO.Column;
import ITU.Baovola.Gucci.DAO.Table;

@Table(name = "seuilpourcentage")
public class SeuilPourcentage {
    @Column(name = "seuil")
    Float seuil;
    @Column(name = "date")
    Date date;
    @Column(name = "pourcentage")
    Float pourcentage;
    
    public SeuilPourcentage() {
    }

    public static SeuilPourcentage getPourcentage(Connection con) throws Exception{
        SeuilPourcentage pourcentage=new SeuilPourcentage();
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("SELECT * FROM seuilpourcentage where date=(select max(date) from seuilpourcentage)");
        if (res.next()) {
           pourcentage.setPourcentage(res.getFloat("pourcentage"));
           pourcentage.setSeuil(res.getFloat("seuil"));
           pourcentage.setDate(res.getDate("date"));
        }
        return pourcentage;
    }

    public Float getSeuil() {
        return seuil;
    }
    public void setSeuil(Float seuil) {
        this.seuil = seuil;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

    
}
