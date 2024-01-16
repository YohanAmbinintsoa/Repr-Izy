package ITU.Baovola.Gucci.Models;

import yohx.annotations.Column;
import yohx.annotations.Generation;
import yohx.annotations.Id;
import yohx.annotations.Table;

@Table(name = "parametrages")
public class Parametrages {
    @Id(name = "idparametre",idtype = Generation.AUTO)
    String id;
    @Column(name = "prixmin")
    Float prixmin;
    @Column(name = "primax")
    Float prixmax;
    @Column(name = "pourcentage")
    Float pourcentage;

    public Parametrages(Float prixmin, Float prixmax, Float pourcentage) {
        this.prixmin = prixmin;
        this.prixmax = prixmax;
        this.pourcentage = pourcentage;
    }
    
    public Parametrages() {}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Float getPrixmin() {
        return prixmin;
    }
    public void setPrixmin(Float prixmin) {
        this.prixmin = prixmin;
    }
    public Float getPrixmax() {
        return prixmax;
    }
    public void setPrixmax(Float prixmax) {
        this.prixmax = prixmax;
    }
    public Float getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

}
