package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "Categorie_marque")
public class Categorie_Marque {
    @Column(name = "fk_marque")
    String idMarque;
    @Column(name = "fk_categorie")
    String idCategorie;
    
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
    

}
