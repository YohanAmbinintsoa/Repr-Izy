package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.DAO.*;
import ITU.Baovola.Gucci.Security.Role;

@Table(name = "utilisateur")
public class User {
    @Id(name = "idutilisateur",idtype = Generation.AUTO)
    String id;
    @Column(name = "nom")
    String nom;
    @Column(name = "prenom")
    String prenom;
    @Column(name="dtn")
    Date dtn;
    @Column(name = "CIN")
    String CIN;
    @Column(name = "role")
    String role;
    @Column(name = "dateinscription")
    Date dateInscription;
    @Column(name = "nomutilisateur")
    String nomUser;
    @Column(name = "mdp")
    String mdp;
    @Column(name = "image")
    String image;

    Integer vente;
    Long annonce;

    public static User login(String username,String password,DAO dao) throws Exception{
        User user=null;
        Connection con=dao.connect();
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery("select * from utilisateur where nomutilisateur='"+username+"' and mdp='"+password+"'");
        if (res.next()) {
            user=new User();
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setId(res.getString("idutilisateur"));
            user.setCIN(res.getString("cin"));
            user.setDateInscription(res.getDate("dateinscription"));
            user.setDtn(res.getDate("dtn"));
            user.setRole(res.getString("role"));
            user.setNomUser(username);
            user.setImage(res.getString("image"));
        }
        return user;
    }

    public User() {
    }

    public static int countClient(Connection con) throws Exception{
        int nombre=0;
        Statement statement=con.createStatement();
        ResultSet res=statement.executeQuery("select COUNT(*) as count from utilisateur where role='user'");
        if (res.next()) {
            nombre=res.getInt("count");
        }
        return nombre;
    }

    public int countVente(Connection con) throws Exception{
        Statement statement=con.createStatement();
        ResultSet res=statement.executeQuery("SELECT COUNT(*) AS nbre FROM vente WHERE vendeur='"+this.getId()+"'");
        int nbre=0;
        if (res.next()) {
            nbre=res.getInt("nbre");
        }
        return nbre;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String cIN) {
        CIN = cIN;
    }

    public Role getRole() {
        if (this.role.equals("ADMIN")) {
            return Role.ADMIN;
        }
        return Role.USER;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getVente() {
        return vente;
    }

    public void setVente(Integer vente) {
        this.vente = vente;
    }

    public Long getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Long annonce) {
        this.annonce = annonce;
    }


}
