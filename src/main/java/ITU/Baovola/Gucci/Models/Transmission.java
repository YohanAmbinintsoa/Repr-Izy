package ITU.Baovola.Gucci.Models;

import ITU.Baovola.Gucci.DAO.*;

@Table(name = "transmission")
public class Transmission {
    @Id(name = "idTransmission", idtype = Generation.AUTO)
    String id;
    @Column(name = "nomtransmission")
    String nom;
    
    public Transmission(String nom) {
        this.nom = nom;
    }
    
    public Transmission() {
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
