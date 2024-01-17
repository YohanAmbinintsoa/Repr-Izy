package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "pays")
public class Pays {

    public Pays(String nom) {
        this.nom = nom;
    }
    public Pays() {
    }

    @Id(name="idPays",idtype = Generation.AUTO)
    String id;
    @Column(name = "nompays")
    String nom;
    @Column(name = "path")
    String path;

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
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
