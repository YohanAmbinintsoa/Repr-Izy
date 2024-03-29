package ITU.Baovola.Gucci.Models;

import java.sql.Connection;


public class Dashboard {
    float chiffre;
    long nonvalide;
    int client;
    int vente;
    
    public Dashboard() {
    }

    public Dashboard(Connection con) throws Exception{
        this.chiffre=Transactions.getChiffreAffaire(con);
        this.client=User.countClient(con);
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
}
