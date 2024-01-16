package ITU.Baovola.Gucci.Models;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import ITU.Baovola.Gucci.Security.Role;
import yohx.DAO.DAO;
import yohx.annotations.Column;
import yohx.annotations.Generation;
import yohx.annotations.Id;
import yohx.annotations.Table;

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
        }
        return user;
    }

    public User() {
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
}
