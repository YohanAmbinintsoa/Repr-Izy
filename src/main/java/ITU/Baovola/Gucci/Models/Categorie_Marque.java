package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "Categorie_marque")
public class Categorie_Marque {
    @Column(name = "fk_marque")
    String idMarque;
    @Column(name = "fk_categorie")
    String idCategorie;
    @Referenced(pk = "idcategorie",fk = "fk_categorie")
    Categorie cat;
    @Referenced(pk = "idmarque",fk = "fk_marque")
    Marque marque;
    
    public Categorie_Marque(String idMarque, String idCategorie) {
        this.idMarque = idMarque;
        this.idCategorie = idCategorie;
    }

    public Categorie_Marque() {}


    public String getIdMarque() {
        return idMarque;
    }
    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }
    public String getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    

}
