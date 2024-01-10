package ITU.Baovola.Gucci.Models;

import yohx.annotations.Column;
import yohx.annotations.Generation;
import yohx.annotations.Id;
import yohx.annotations.Table;

@Table(name = "categorie")
public class Categorie {
    @Id(name = "idcategorie", idtype = Generation.AUTO)
    String id;
    @Column(name = "nomcategorie")
    String nom;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
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
}
