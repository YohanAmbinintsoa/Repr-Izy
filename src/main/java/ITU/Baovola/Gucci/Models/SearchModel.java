package ITU.Baovola.Gucci.Models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import jakarta.servlet.http.HttpServletRequest;

public class SearchModel {
    String idcategorie;
    String idmarque;
    float prixMin;
    float prixMax;
    int annee;
    String libelle;
    String description;
    String idtype;
    int place;
    int kilometrique;
    String idetat;
    String idtransmission;
    String idenergie;
    float cylindre;
    float puissance;
    float nbrCylindre;

    public SearchModel() {

    }

    public Query setThemAll(HttpServletRequest req) throws Exception{
        Query query=new Query();
        Field[] fields=this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String value=req.getParameter(fields[i].getName());
            if (value!=null&&!value.equals("")) {
                Method setter= this.getClass().getMethod("set"+SearchModel.capitalizeFirstLetter(fields[i].getName()), fields[i].getType());
                if (fields[i].getType().equals(Float.class)) {
                    setter.invoke(this, Float.parseFloat(value));
                } else {
                    setter.invoke(this, value);
                }
                Method getter=this.getClass().getMethod("get"+SearchModel.capitalizeFirstLetter(fields[i].getName()));
                Criteria crit=(Criteria)getter.invoke(this);
                query.addCriteria(crit);
            }
        }
        return query;
    }

    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; 
        }
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

    public Criteria getIdcategorie() {
        return Criteria.where("cat._id").is(this.idcategorie);
    }
    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }
    public Criteria getIdmarque() {
        return Criteria.where("marque._id").is(this.idmarque);
    }
    public void setIdmarque(String idmarque) {
        this.idmarque = idmarque;
    }
    public Criteria getPrixMin() {
        return Criteria.where("prix").gt(this.prixMin);
    }
    public void setPrixMin(float prixMin) {
        this.prixMin = prixMin;
    }
    public Criteria getPrixMax() {
        return Criteria.where("prix").lt(this.prixMax);
    }
    public void setPrixMax(float prixMax) {
        this.prixMax = prixMax;
    }
    public Criteria getAnnee() {

        return Criteria.where("annee").is(this.annee);
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public Criteria getLibelle() {
        return Criteria.where("libelle").regex(this.libelle);
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Criteria getDescription() {
        return Criteria.where("description").regex(this.description);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Criteria getIdtype() {
        return Criteria.where("type._id").is(this.idtype);
    }
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }
    public Criteria getPlace() {
        return Criteria.where("place").is(this.place);
    }
    public void setPlace(int place) {
        this.place = place;
    }
    public Criteria getKilometrique() {
        return Criteria.where("kilometrique").is(this.kilometrique);
    }
    public void setKilometrique(int kilometrique) {
        this.kilometrique = kilometrique;
    }
    public Criteria getIdetat() {
        return Criteria.where("etatVehicule._id").is(this.idetat);
    }
    public void setIdetat(String idetat) {
        this.idetat = idetat;
    }
    public Criteria getIdtransmission() {
        return Criteria.where("transmission._id").is(this.idtransmission);
    }
    public void setIdtransmission(String idtransmission) {
        this.idtransmission = idtransmission;
    }
    public Criteria getIdenergie() {
        return Criteria.where("energie._id").is(this.idenergie);
    }
    public void setIdenergie(String idenergie) {
        this.idenergie = idenergie;
    }
    public Criteria getCylindre() {
        return Criteria.where("cylindre").is(this.cylindre);
    }
    public void setCylindre(float cylindre) {
        this.cylindre = cylindre;
    }
    public Criteria getPuissance() {
        return Criteria.where("puissance._id").is(this.puissance);
    }
    public void setPuissance(float puissance) {
        this.puissance = puissance;
    }
    public Criteria getNbrCylindre() {
        return Criteria.where("nbrCylindre").is(this.nbrCylindre);
    }
    public void setNbrCylindre(float nbrCylindre) {
        this.nbrCylindre = nbrCylindre;
    }
    
}
