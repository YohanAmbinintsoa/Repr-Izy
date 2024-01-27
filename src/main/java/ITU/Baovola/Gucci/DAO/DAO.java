package ITU.Baovola.Gucci.DAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DAO {
    String username;
    String password;
    String database;
    String host;
    String sgbd;
    TemplateReader reader;
    
    public DAO(String username, String password, String host,String database, String sgbd){
        this.username = username;
        this.password = password;
        this.database = database;
        this.sgbd = sgbd;
        this.host=host;
        try {
            this.reader=new TemplateReader(sgbd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DAO() {}

    public Connection connect() throws Exception{
        String connectionString=this.reader.getCredentials().get("connection_string").replace("{database}", this.database);
        connectionString=connectionString.replace("{host}", this.host);
        Class.forName(this.reader.getCredentials().get("driver"));
        Connection con=DriverManager.getConnection(connectionString, this.username, this.password);
        con.setAutoCommit(false);
        return con;
    }

    /* CRUD METHODS START */
    public <T> T insert(Connection con, T inserted) throws Exception {
        boolean opened = false;
        if (con == null) {
            con = this.connect();
            opened = true;
        }
        T last = null;
        TableMetadata metadata=new TableMetadata(this.reader, inserted);
        String insertSyntax = metadata.generateInsertQuery(con);
        System.out.println(insertSyntax);
        Statement state = con.createStatement();
        state.execute(insertSyntax);
        if (metadata.getId() != null) {
            last = getLastInsertedRow(con, metadata,inserted);
        } else {
            last = inserted;
        }
        if (opened) {
            con.commit();
            con.close();
        } 
        return last;
    }

        public <T> List<T> select(Connection con, Object inserted) throws Exception{
            boolean opened=false;
            if (con==null) {
                con=this.connect();
                opened=true;
            }
            List<T> all=new ArrayList<>();
            TableMetadata metadata=new TableMetadata(this.reader, inserted);
            String select_query=this.reader.getSyntax().get("select").replace("{table}", metadata.getTableName());
            select_query=metadata.getFieldPredicates(select_query,false);
            select_query=select_query.replace("{join}",  metadata.addJoin());
            System.out.println(select_query);
            Statement state=con.createStatement();
            ResultSet res=state.executeQuery(select_query);
            while (res.next()) {
                Object newObj=metadata.fillObject(res,inserted);
                all.add((T)newObj);
            }
            if (opened==true) {
                con.close();
            }
            return all;
        }

    public <T> T update(Connection con, Object id,Object subject) throws Exception{
        boolean opened=false;
        if (con==null) {
            con=this.connect();
            opened=true;
        }
        TableMetadata metadata=new TableMetadata(this.reader, subject);
        String updateSyntax=this.reader.getSyntax().get("update").replace("{table}", metadata.getTableName());
        updateSyntax=metadata.generateUpdateString(updateSyntax, id);
        System.out.println(updateSyntax);
        Statement state=con.createStatement();
        state.execute(updateSyntax);
        T updated=(T)this.select(con, metadata.createObjectById(id)).get(0);
        if (opened==true) {
            con.commit();
            con.close();
        }
        return updated;
    }

    public void delete(Connection con, Object id, Object subject) throws Exception{
        boolean opened=false;
        if (con==null) {
            con=this.connect();
            opened=true;
        }
        TableMetadata metadata=new TableMetadata(reader, subject);
        String deleteSyntax=this.reader.getSyntax().get("delete").replace("{table}", metadata.getTableName());
        deleteSyntax=metadata.getDeletePredicate(id, deleteSyntax);
        System.out.println(deleteSyntax);
        Statement state=con.createStatement();
        state.execute(deleteSyntax);
        if (opened==true) {
            con.commit();
            con.close();
        }
    }

    public <T> List<T> selectLimit(Connection con, Object inserted, int min, int max) throws Exception{
            boolean opened=false;
            if (con==null) {
                con=this.connect();
                opened=true;
            }
            List<T> all=new ArrayList<>();
            TableMetadata metadata=new TableMetadata(this.reader, inserted);
            String select_query=this.reader.getSyntax().get("select_limit").replace("{table}", metadata.getTableName());
            select_query=metadata.getFieldPredicates(select_query,true);
            select_query=select_query.replace("{min}", String.valueOf(min));
            select_query=select_query.replace("{max}", String.valueOf(max));
            System.out.println(select_query);
            Statement state=con.createStatement();
            ResultSet res=state.executeQuery(select_query);
            while (res.next()) {
                Object newObj=metadata.fillObject(res,inserted);
                all.add((T)newObj);
            }
            if (opened==true) {
                con.close();
            }
            return all;
        }

    /* CRUD METHODS END */
    
    public <T> T getLastInsertedRow(Connection con, TableMetadata metadata, Object inserted) throws Exception{
        boolean opened=false;
        if (con==null) {
            con=this.connect();
            opened=true;
        }
        Object obj=null;
        String query="select * from "+metadata.getTableName()+" order by "+metadata.getId().getColumnName()+" DESC LIMIT 1";
        Statement state=con.createStatement();
        ResultSet res=state.executeQuery(query);
        if (res.next()) {
            obj=metadata.fillObject(res,inserted);
        }
        if (opened==true) {
            con.close();
        }
        return (T)obj;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getSgbd() {
        return sgbd;
    }

    public void setSgbd(String sgbd) {
        this.sgbd = sgbd;
    }

    public TemplateReader getReader() {
        return reader;
    }

    public void setReader(TemplateReader reader) {
        this.reader = reader;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

   
}
