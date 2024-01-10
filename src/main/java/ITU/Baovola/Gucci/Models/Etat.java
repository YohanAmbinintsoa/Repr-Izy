package ITU.Baovola.Gucci.Models;

import yohx.annotations.Column;
import yohx.annotations.Generation;
import yohx.annotations.Id;
import yohx.annotations.Table;

@Table(name = "etat")
public class Etat {
    @Id(name = "idEtat",idtype = Generation.AUTO)
    String id;
    @Column(name = "nomEtat")
    String nom;

    public Etat(String nom) {
        this.nom = nom;
    }

    public Etat() {
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
