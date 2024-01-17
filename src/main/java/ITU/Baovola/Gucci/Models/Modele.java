package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "modele")
public class Modele {
    @Id(name = "idmodele",idtype = Generation.AUTO)
    String id;
    @Column(name = "nommodele")
    String nom;
    @Column(name = "fk_marque")
    String idmarque;
    
    public Modele(String nom, String idmarque) {
        this.nom = nom;
        this.idmarque = idmarque;
    }
    public Modele() {
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
    public String getIdmarque() {
        return idmarque;
    }
    public void setIdmarque(String idmarque) {
        this.idmarque = idmarque;
    }
    
}
