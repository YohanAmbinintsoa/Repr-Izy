package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "marque")
public class Marque {
    @Id(name = "idmarque",idtype = Generation.AUTO)
    String id;
    @Column(name = "nommarque")
    String nom;
    @Column(name = "fk_pays")
    String idpays;
    @Referenced(pk="idpays",fk="fk_pays")
    Pays pays;
    @Column(name = "path")
    String path;
    
    public Marque(String nom, String idpays) {
        this.nom = nom;
        this.idpays = idpays;
    }

    public Marque() {
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getIdpays() {
        return idpays;
    }
    public void setIdpays(String idpays) {
        this.idpays = idpays;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}
