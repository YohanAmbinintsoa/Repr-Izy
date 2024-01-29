package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.DAO.Column;
import ITU.Baovola.Gucci.DAO.Generation;
import ITU.Baovola.Gucci.DAO.Id;
import ITU.Baovola.Gucci.DAO.Table;

@Table(name = "vente")
public class Vente {
    @Id(name = "idvente",idtype = Generation.AUTO)
    String id;
    @Column(name = "idannonce")
    String idannonce;
    @Column(name = "vendeur")
    String vendeur;
    @Column(name = "acheteur")
    String acheteur;

    public Vente(String idannonce, String vendeur, String acheteur) {
        this.idannonce = idannonce;
        this.vendeur = vendeur;
        this.acheteur = acheteur;
    }
    public Vente() {
    }
    public static int countVente(Connection con) throws Exception{
        int nombre=0;
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select count(*) as count from vente");
        if (res.next()) {
            nombre=res.getInt("count");
        }
        return nombre;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdannonce() {
        return idannonce;
    }
    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }
    public String getVendeur() {
        return vendeur;
    }
    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }
    public String getAcheteur() {
        return acheteur;
    }
    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }
    
}
