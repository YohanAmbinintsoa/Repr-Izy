package ITU.Baovola.Gucci.Models;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;

import ITU.Baovola.Gucci.Services.DocumentService;

public class Dashboard {
    private DocumentService mongo;
    
    float chiffre;
    long nonvalide;
    int client;
    int vente;
    
    public Dashboard() {
    }

    public Dashboard(Connection con) throws Exception{
        this.chiffre=Transactions.getChiffreAffaire(con);
        this.client=User.countClient(con);
        this.nonvalide=mongo.countNonValide();
        this.vente=Vente.countVente(con);
    }
    
    public float getChiffre() {
        return chiffre;
    }
    public void setChiffre(float chiffre) {
        this.chiffre = chiffre;
    }
    public int getClient() {
        return client;
    }
    public void setClient(int client) {
        this.client = client;
    }
    public int getVente() {
        return vente;
    }
    public void setVente(int vente) {
        this.vente = vente;
    }

    public long getNonvalide() {
        return nonvalide;
    }

    public void setNonvalide(long nonvalide) {
        this.nonvalide = nonvalide;
    }
    @Autowired
    public void setMongo(DocumentService mongo) {
        this.mongo = mongo;
    }
}
