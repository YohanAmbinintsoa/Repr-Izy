package ITU.Baovola.Gucci.DAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableMetadata {
    Object subject;
    String tableName;
    FieldData id;
    List<FieldData> columns=new ArrayList<>();
    int idDim;
    Generation idtype;
    TemplateReader reader;
    OrderBy order;
    HashMap<String,Join> joins=new HashMap<>();

    public TableMetadata(TemplateReader reader,Object subject) throws Exception{
        this.reader=reader;
        this.subject=subject;
        this.initialize();
    }

    public void initialize() throws Exception{
        String tableName=this.subject.getClass().getName();
        if (this.subject.getClass().isAnnotationPresent(Table.class)) {
            tableName=this.subject.getClass().getAnnotation(Table.class).name();
        }
        this.tableName=tableName;
        Field[] fields=this.subject.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Id.class)) {
                Id idAnnotation=fields[i].getAnnotation(Id.class);
                this.idDim=idAnnotation.idDim();
                this.idtype=idAnnotation.idtype();
                this.id=new FieldData(fields[i],this.subject);
            }
            if (fields[i].isAnnotationPresent(Column.class)) {
                this.columns.add(new FieldData(fields[i],this.subject));
            }
            if (fields[i].isAnnotationPresent(Order.class)) {
                this.order=new OrderBy(new FieldData(fields[i],this.subject),fields[i].getAnnotation(Order.class),this.reader);
            }
            if (fields[i].isAnnotationPresent(Referenced.class)) {
                Referenced ref=fields[i].getAnnotation(Referenced.class);
                Join join=new Join(ref.pk(),ref.fk(),fields[i]);
                FieldData data=new FieldData(fields[i],this.subject);
                this.joins.put(data.getFieldName(),join);
            }
        }
    }

    public String generateInsertQuery(Connection con) throws Exception{
        String insertSyntax=this.reader.getSyntax().get("insert");
        String cols="";
        String values="";
        if (this.id!=null) {
            this.generateId(con);
            cols+=this.id.getColumnName()+",";
            values+=this.id.toFormattedForm()+",";
        }
        for (int i = 0; i < this.columns.size(); i++) {
            String val="NULL";
            if (this.columns.get(i).getValue()!=null) {
                val=this.columns.get(i).toFormattedForm();
            }
            cols+=this.columns.get(i).getColumnName()+",";
            values+=val+",";
        }
        cols=cols.substring(0, cols.lastIndexOf(","));
        values=values.substring(0, values.lastIndexOf(","));
        insertSyntax = insertSyntax.replace("{table}", tableName);
        insertSyntax = insertSyntax.replace("{column}", cols);
        insertSyntax = insertSyntax.replace("{values}", values);
        return insertSyntax;
    }

    public void generateId(Connection con) throws Exception{
        String generatedid="";
        if (this.idtype==Generation.AUTO) {
            int value=getOrCreateSequence(con);
            String idName=tableName.toUpperCase();
            int dim=this.idDim-String.valueOf(value).length();
            for (int i = 0; i < dim; i++) {
                idName=idName+"0";
            }
            idName=idName+value;
            generatedid=idName;
        } else if (this.idtype==Generation.MANUAL) {
            generatedid=this.id.getValue().toString();
        } else {
            generatedid="NULL";
        }
        this.id.setValue(generatedid);
    }

    private int getOrCreateSequence(Connection con) throws Exception{
        int value=0;
        String sequence=this.reader.getSyntax().get("sequence");
        String createSequence=this.reader.getSyntax().get("create_sequence");
        String sequenceName=tableName+"_seq";
        sequence=sequence.replace("{sequence_name}", sequenceName);
        sequence=sequence.replace("{table}", tableName);
        Statement state=con.createStatement();
        try (ResultSet res=state.executeQuery(sequence);) {
            if (res.next()) {
                return res.getInt(1);
            }
        } catch (Exception e) {
            con.rollback();
            Statement state2=con.createStatement();
            createSequence=createSequence.replace("{sequence_name}", sequenceName);
            state2.executeUpdate(createSequence);
            ResultSet res2=state2.executeQuery(sequence);
            if (res2.next()) {
                value=res2.getInt(1);
            }   
            con.commit();
        }
        return value;
    }

    public String addJoin() throws Exception{
        String select="";
        for (Map.Entry<String, Join> entry : this.joins.entrySet()) {
           Join join=entry.getValue();
           select+=" "+join.generateJoinQuery();
        }
        return select;
    }

    public Object generateJoinField(FieldData data, ResultSet res) throws Exception{
        Object field=null;
        if (this.joins.containsKey(data.getFieldName())) {
            Join join=this.joins.get(data.getFieldName());
            Constructor<?> fieldConst=join.getField().getType().getConstructor();
            field=fieldConst.newInstance();
            for (int i = 0; i < join.getTableFields().length; i++) {
                System.out.println(join.getTableFields()[i].getName());
                FieldData fieldData=new FieldData(join.getTableFields()[i], field);
                if (fieldData.getColumnName()!=null) {
                    Object databaseObject=res.getObject(fieldData.getColumnName());
                    if (databaseObject!=null) {
                        databaseObject=MyParser.parse(databaseObject, fieldData.getType());
                        Method setter=field.getClass().getMethod("set"+MyParser.toUpperCase(join.getTableFields()[i].getName()), fieldData.getType());
                        setter.invoke(field, databaseObject);
                    }
                }
            }
        }
        return field;
    }

    public <T> T fillObject(ResultSet res, Object toCreate) throws Exception{
        Constructor<?> construct=toCreate.getClass().getConstructor();
        Object newObj=construct.newInstance();
        Field[] fields=toCreate.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            FieldData data=new FieldData(fields[i], toCreate);
            Object joinObject=this.generateJoinField(data, res);
            if (joinObject!=null) {
                Method setter=newObj.getClass().getMethod("set"+MyParser.toUpperCase(fields[i].getName()), data.type);
                setter.invoke(newObj, joinObject);
            } else {
                Object databaseObject=res.getObject(data.getColumnName());
                if (databaseObject!=null) {
                    databaseObject=MyParser.parse(databaseObject, data.type);
                    Method setter=newObj.getClass().getMethod("set"+MyParser.toUpperCase(fields[i].getName()), data.type);
                    setter.invoke(newObj, databaseObject);
                }
            }
        }
        return (T) newObj;
    }

    public String getFieldPredicates(String select_query, boolean limited) throws Exception{
        String predicate="";
        if (this.id!=null&&this.id.getValue()!=null) {
            predicate+=this.id.getKeyValue()+" and";
        }
        for (int i = 0; i < this.columns.size(); i++) {
            if (this.columns.get(i).getValue()!=null) {
                predicate+=this.columns.get(i).getKeyValue()+" and";
            }
        }
        if (predicate.equals("")) {
            predicate=" 1=1";
        } else {
            predicate=predicate.substring(0, predicate.lastIndexOf(" and"));
        }
        select_query=select_query.replace("{predicate}", predicate);
        if (this.order!=null&&limited==false) {
            select_query+=" "+this.order.getOrderByString();
        }
        return select_query;
    }

    public String generateUpdateString(String updateSyntax, Object id) throws Exception{
        String updateColumn="";
        for (int i = 0; i < this.columns.size(); i++) {
            if (this.columns.get(i).getValue()!=null) {
                updateColumn+=this.columns.get(i).getKeyValue()+",";
            }
        }
        System.out.println(updateColumn);
        updateColumn=updateColumn.substring(0, updateColumn.lastIndexOf(","));
        updateSyntax=updateSyntax.replace("{predicate}", updateColumn);
        String idName=this.getId().getColumnName()+"="+FieldData.toFormattedFormObject(id);
        updateSyntax=updateSyntax.replace("{idvalue}", idName);
        return updateSyntax;
    }

    public <T> T createObjectById(Object id) throws Exception{
        Constructor<?> construct=this.getSubject().getClass().getConstructor();
        Object newObj=construct.newInstance();
        Method setter=newObj.getClass().getMethod("set"+MyParser.toUpperCase(this.getId().getFieldName()), this.getId().getType());
        setter.invoke(newObj, id);
        return (T)newObj;
    }

    public String getDeletePredicate(Object id, String deleteSyntax){
        String predicate=this.id.getColumnName()+"="+FieldData.toFormattedFormObject(id);
        deleteSyntax=deleteSyntax.replace("{predicate}", predicate);
        return deleteSyntax;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public FieldData getId() {
        return id;
    }

    public void setId(FieldData id) {
        this.id = id;
    }

    public List<FieldData> getColumns() {
        return columns;
    }

    public void setColumns(List<FieldData> columns) {
        this.columns = columns;
    }

    public int getIdDim() {
        return idDim;
    }

    public void setIdDim(int idDim) {
        this.idDim = idDim;
    }

    public Generation getIdtype() {
        return idtype;
    }

    public void setIdtype(Generation idtype) {
        this.idtype = idtype;
    }

    public TemplateReader getReader() {
        return reader;
    }

    public void setReader(TemplateReader reader) {
        this.reader = reader;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public OrderBy getOrder() {
        return order;
    }

    public void setOrder(OrderBy order) {
        this.order = order;
    }

    public HashMap<String, Join> getJoins() {
        return joins;
    }

    public void setJoins(HashMap<String, Join> joins) {
        this.joins = joins;
    }
}
