package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;

import ITU.Baovola.Gucci.DAO.DAO;

@Document(collection = "Annonce")
public class Annonce {
    @Id
    String id;
    @NonNull
    User user;
    @NonNull
    Categorie cat;
    @NonNull
    Marque marque;
    @NonNull
    Modele modele;
    @NonNull
    float prix;
    @NonNull
    int annee;
    @NonNull
    String libelle;
    @NonNull
    String description;
    @NonNull
    Type type;
    @NonNull
    int place;
    float kilometrique;
    @NonNull
    Etat etatVehicule;
    @NonNull
    Transmission transmission;
    @NonNull
    Energie energie;
    float cylindre;
    float puissance;
    float nbrCylindre;
    int etatAnnonce;
    Date date;

    List<String> images;

    public Annonce(Connection con,DAO req, String cat, String marque, String modele, String prix, String annee, String description, String type,
            String place, String kilometrique, String etatVehicule, String transmission, String energie,
            String cylindre, String puissance, String nbrCylindre,String libelle) throws Exception{
        this.setAnnee(annee);
        this.setCategorie(cat, req, con);
        this.setCylindre(cylindre);
        this.setDescription(description);
        this.setEnergie(energie, req, con);
        this.setEtatVehicule(etatVehicule, req, con);
        this.setType(type,req,con);
        this.setImages(images);
        this.setModele(modele,req,con);
        this.setKilometrique(kilometrique);
        this.setMarque(marque, req, con);
        this.setNbrCylindre(nbrCylindre);
        this.setPlace(place);
        this.setPrix(prix);
        this.setPuissance(puissance);
        this.setTransmission(transmission, req, con);
        this.setLibelle(libelle);
    }

    public Annonce(User user, Categorie cat, Marque marque, Modele modele, float prix, int annee, String description,
            Type type, int place, float kilometrique, Etat etatVehicule, Transmission transmission, Energie energie,
            float cylindre, float puissance, float nbrCylindre, int etatAnnonce, Date date, List<String> images) throws Exception{
        this.user = user;
        this.cat = cat;
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

    public void setMarque(String marque, DAO req, Connection con) throws Exception{
        if (marque!=null&&!marque.equals("")) {
            Marque mk=new Marque();
            mk.setId(marque);
            this.marque=(Marque)req.select(con,mk).get(0);
        }
    }

    public void setModele(String modele, DAO req, Connection con) throws Exception{
        if (modele!=null&&!modele.equals("")) {
            Modele mk=new Modele();
            mk.setId(modele);
            this.modele=(Modele)req.select(con,mk).get(0);
        }
    }

    public void setCategorie(String categorie, DAO req, Connection con) throws Exception {
        if (categorie != null && !categorie.equals("")) {
            System.out.println(categorie);
            Categorie cat = new Categorie();
            cat.setId(categorie);
            this.cat = (Categorie) req.select(con, cat).get(0);
        }
    }

    public void setPrix(String prix) throws Exception{
        if (prix!=null&&!prix.equals("")) {
            this.prix = Float.parseFloat(prix);
        }
    }

    public void setAnnee(String annee) throws Exception{
        if (annee!=null&&!annee.equals("")) {
            this.annee = Integer.parseInt(annee);
        }
       
    }

    public void setType(String typeId, DAO req, Connection con) throws Exception {
        if (typeId != null && !typeId.equals("")) {
            Type type = new Type();
            type.setId(typeId);
            this.type = (Type) req.select(con, type).get(0);
        }
    }

    public void setPlace(String place) throws Exception{
        if (place!=null&&!place.equals("")) {
            this.place = Integer.parseInt(place);
        }
    }

    public void setKilometrique(String kilometrique) throws Exception{
        if (kilometrique!=null&&!kilometrique.equals("")) {
            this.kilometrique = Float.parseFloat(kilometrique);
        }
    }

    public void setEtatVehicule(String etatId, DAO req, Connection con) throws Exception {
        if (etatId != null && !etatId.equals("")) {
            Etat etat = new Etat();
            etat.setId(etatId);
            this.etatVehicule = (Etat) req.select(con, etat).get(0);
        }
    }

    public void setTransmission(String transmissionId, DAO req, Connection con) throws Exception {
        if (transmissionId != null && !transmissionId.equals("")) {
            Transmission transmission = new Transmission();
            transmission.setId(transmissionId);
            this.transmission = (Transmission) req.select(con, transmission).get(0);
        }
    }
    
    public void setEnergie(String energieId, DAO req, Connection con) throws Exception {
        if (energieId != null && !energieId.equals("")) {
            Energie energie = new Energie();
            energie.setId(energieId);
            this.energie = (Energie) req.select(con, energie).get(0);
        }
    }

    public void setCylindre(String cylindre) throws Exception{
        if (cylindre!=null&&!cylindre.equals("")) {
            this.cylindre = Float.parseFloat(cylindre);
        }
    }

    public void setPuissance(String puissance) throws Exception{
        if (puissance!=null&&!puissance.equals("")) {
            this.puissance = Float.parseFloat(puissance);
        }
    }

    public void setNbrCylindre(String nbrCylindre) throws Exception{
        if (nbrCylindre!=null&&!nbrCylindre.equals("")) {
            this.nbrCylindre = Float.parseFloat(nbrCylindre);
        }
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

    public Categorie getCat() {
        return cat;
    }

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
