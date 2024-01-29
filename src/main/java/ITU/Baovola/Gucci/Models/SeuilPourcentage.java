package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.DAO.Column;
import ITU.Baovola.Gucci.DAO.Table;

@Table(name = "seuilpourcentage")
public class SeuilPourcentage {
    @Column(name = "seuilmin")
    Float seuilmin;
    @Column(name = "date")
    Date date;
    @Column(name = "pourcentagemin")
    Float pourcentagemin;
    @Column(name = "seuilmax")
    Float seuilmax;
    @Column(name = "pourcentagemax")
    Float pourcentagemax;
    
    public SeuilPourcentage() {
    }

    public static SeuilPourcentage getPourcentage(Connection con) throws Exception{
        SeuilPourcentage pourcentage=new SeuilPourcentage();
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("SELECT * FROM seuilpourcentage where date=(select max(date) from seuilpourcentage)");
        if (res.next()) {
           pourcentage.setPourcentagemax(res.getFloat("pourcentagemax"));
           pourcentage.setPourcentagemin(res.getFloat("pourcentagemin"));
           pourcentage.setSeuilmax(res.getFloat("seuilmax"));
           pourcentage.setSeuilmin(res.getFloat("seuilmin"));
           pourcentage.setDate(res.getDate("date"));
        }
        return pourcentage;
    }

    public Float getSeuilmin() {
        return seuilmin;
    }

    public void setSeuilmin(Float seuilmin) {
        this.seuilmin = seuilmin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPourcentagemin() {
        return pourcentagemin;
    }

    public void setPourcentagemin(Float pourcentagemin) {
        this.pourcentagemin = pourcentagemin;
    }

    public Float getSeuilmax() {
        return seuilmax;
    }

    public void setSeuilmax(Float seuilmax) {
        this.seuilmax = seuilmax;
    }

    public Float getPourcentagemax() {
        return pourcentagemax;
    }

    public void setPourcentagemax(Float pourcentagemax) {
        this.pourcentagemax = pourcentagemax;
    }

    
}
