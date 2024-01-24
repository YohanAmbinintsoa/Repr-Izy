package ITU.Baovola.Gucci.Models;

import java.sql.Timestamp;

import ITU.Baovola.Gucci.DAO.Column;
import ITU.Baovola.Gucci.DAO.Generation;
import ITU.Baovola.Gucci.DAO.Id;
import ITU.Baovola.Gucci.DAO.Table;

@Table(name = "transactions")
public class Transactions {
    @Id(name = "idtransaction",idtype = Generation.AUTO)
    String id;
    @Column(name = "entree")
    Float entree;
    @Column(name = "sortie")
    Float sortie;
    @Column(name = "datetransaction")
    Timestamp datetransaction;
    public Transactions(Float entree, Float sortie, Timestamp datetransaction) {
        this.entree = entree;
        this.sortie = sortie;
        this.datetransaction = datetransaction;
    }
    public Transactions() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Float getEntree() {
        return entree;
    }
    public void setEntree(Float entree) {
        this.entree = entree;
    }
    public Float getSortie() {
        return sortie;
    }
    public void setSortie(Float sortie) {
        this.sortie = sortie;
    }
    public Timestamp getDatetransaction() {
        return datetransaction;
    }
    public void setDatetransaction(Timestamp datetransaction) {
        this.datetransaction = datetransaction;
    }
    
}
