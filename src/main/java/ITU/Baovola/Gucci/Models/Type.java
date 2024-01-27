package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "type")
public class Type {
    @Id(name = "idtype", idtype = Generation.AUTO)
    String id;
    @Column(name = "nomtype")
    String nom;
    @Column(name = "fk_categorie")
    String categorie;
    @Referenced(pk = "idcategorie",fk="fk_categorie")
    Categorie cat;
    
    public Type(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    public Type() {
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
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }
}
