package ITU.Baovola.Gucci.Security;

import ITU.Baovola.Gucci.DAO.DAO;
import ITU.Baovola.Gucci.Models.User;

public class MyContext {
    private static final ThreadLocal<User> user=new ThreadLocal<>();
    private static final DAO requester=new DAO("postgres", "root", "localhost","final", "postgresql");
    // private static DAO requester=new DAO("YohanAmbinintsoa", "u4ixcVJHQOD3", "ep-shrill-voice-12867324.us-east-2.aws.neon.tech","final", "postgresql");

    public static User getUser(){
        return user.get();
    }

    public static DAO getRequester(){
        return requester;
    }

    public static void setUser(User person){
        user.set(person);
    }
}
