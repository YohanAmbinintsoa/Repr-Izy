package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "categorie")
public class Categorie {
    @Id(name = "idcategorie", idtype = Generation.AUTO)
    String id;
    @Column(name = "nomcategrorie")
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
