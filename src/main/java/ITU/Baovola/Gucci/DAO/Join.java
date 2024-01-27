package ITU.Baovola.Gucci.DAO;

import java.lang.reflect.Field;


public class Join {
    
    public Join(String pk, String fk, Field field) {
        this.pk = pk;
        this.fk = fk;
        this.field = field;
        this.tableFields=field.getType().getDeclaredFields();
    }

    public Join() {
    }

    public String generateJoinQuery(){
        String tableName=field.getType().getName();
        if (field.getType().isAnnotationPresent(Table.class)) {
            tableName=field.getType().getAnnotation(Table.class).name();
        }
        return " JOIN "+tableName+" ON "+this.pk +"="+this.fk;
    }

    String pk;
    String fk;
    Field field;
    Field[] tableFields;
    public String getPk() {
        return pk;
    }
    public void setPk(String pk) {
        this.pk = pk;
    }
    public String getFk() {
        return fk;
    }
    public void setFk(String fk) {
        this.fk = fk;
    }
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }

    public Field[] getTableFields() {
        return tableFields;
    }

    public void setTableFields(Field[] tableFields) {
        this.tableFields = tableFields;
    }
}
