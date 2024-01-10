package ITU.Baovola.Gucci.Models;

import yohx.annotations.Column;
import yohx.annotations.Generation;
import yohx.annotations.Id;
import yohx.annotations.Table;

@Table(name = "energie")
public class Energie {
    @Id(name = "idenergie",idtype = Generation.AUTO)
    String id;
    @Column(name = "nomenergie")
    String nom;
    
    public Energie(String nom) {
        this.nom = nom;
    }
    public Energie() {
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
