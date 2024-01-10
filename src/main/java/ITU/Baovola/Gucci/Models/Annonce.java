package ITU.Baovola.Gucci.Models;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "annonce")
public class Annonce {
    @Id
    String id;
    User user;
    Marque marque;
    Modele modele;
    float prix;
    int annee;
    String description;
    Type type;
    int place;
    float kilometrique;
    Etat etatVehicule;
    Transmission transmission;
    Energie energie;
    float cylindre;
    float puissance;
    float nbrCylindre;
    int etatAnnonce;
    Date date;
    List<String> images;

    public Annonce(User user, Marque marque, Modele modele, float prix, int annee, String description, Type type,
            int place, float kilometrique, Etat etatVehicule, Transmission transmission, Energie energie,
            float cylindre, float puissance, float nbrCylindre, int etatAnnonce, Date date, List<String> images) {
        this.user = user;
        this.marque = marque;
        this.modele = modele;
        this.prix = prix;
        this.annee = annee;
        this.description = description;
        this.type = type;
        this.place = place;
        this.kilometrique = kilometrique;
        this.etatVehicule = etatVehicule;
        this.transmission = transmission;
        this.energie = energie;
        this.cylindre = cylindre;
        this.puissance = puissance;
        this.nbrCylindre = nbrCylindre;
        this.etatAnnonce = etatAnnonce;
        this.date = date;
        this.images = images;
    }

    public Annonce() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Marque getMarque() {
        return marque;
    }
    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    public Modele getModele() {
        return modele;
    }
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public int getPlace() {
        return place;
    }
    public void setPlace(int place) {
        this.place = place;
    }
    public float getKilometrique() {
        return kilometrique;
    }
    public void setKilometrique(float kilometrique) {
        this.kilometrique = kilometrique;
    }
    public Etat getEtatVehicule() {
        return etatVehicule;
    }
    public void setEtatVehicule(Etat etatVehicule) {
        this.etatVehicule = etatVehicule;
    }
    public Transmission getTransmission() {
        return transmission;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
    public Energie getEnergie() {
        return energie;
    }
    public void setEnergie(Energie energie) {
        this.energie = energie;
    }
    public float getCylindre() {
        return cylindre;
    }
    public void setCylindre(float cylindre) {
        this.cylindre = cylindre;
    }
    public float getPuissance() {
        return puissance;
    }
    public void setPuissance(float puissance) {
        this.puissance = puissance;
    }
    public float getNbrCylindre() {
        return nbrCylindre;
    }
    public void setNbrCylindre(float nbrCylindre) {
        this.nbrCylindre = nbrCylindre;
    }
    public int getEtatAnnonce() {
        return etatAnnonce;
    }
    public void setEtatAnnonce(int etatAnnonce) {
        this.etatAnnonce = etatAnnonce;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    
}
