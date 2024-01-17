package ITU.Baovola.Gucci.DAO;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;


public class FieldData {
    String fieldName;
    String columnName;
    Object value;
    Class<?> type;

    public FieldData(Field field, Object subject) throws Exception{
        field.setAccessible(true);
        if (field.isAnnotationPresent(Column.class)) {
            Column col=field.getAnnotation(Column.class);
            String colname=col.name();
            if (colname.equals("")) {
                colname=field.getName();
            }
            this.columnName=colname;
        } else if (field.isAnnotationPresent(Id.class)) {
            Id id=field.getAnnotation(Id.class);
            String colname=id.name();
            if (colname.equals("")) {
                colname=field.getName();
            }
            this.columnName=colname;
        }
        this.fieldName=field.getName();
        this.value=field.get(subject);
        this.type=field.getType();
    }

    public String toFormattedForm(){
        if (type.equals(String.class)||type.equals(Date.class)||type.equals(java.util.Date.class)||type.equals(Timestamp.class)) {
            return "'"+this.getValue().toString()+"'";
        } else {
            return this.getValue().toString();
        }
    }

    public static String toFormattedFormObject(Object obj){
        if (obj.getClass().equals(String.class)||obj.getClass().equals(Date.class)||obj.getClass().equals(java.util.Date.class)||obj.getClass().equals(Timestamp.class)) {
            return "'"+obj.toString()+"'";
        } else {
            return obj.toString();
        }
    }

    public String getKeyValue(){
        return this.columnName+"="+this.toFormattedForm();
    }

    public Object getValue() {
        return value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

}
