package ITU.Baovola.Gucci.DAO;


public class OrderBy {
    FieldData field;
    String order;
    TemplateReader reader;

    public OrderBy(FieldData data, Order order,TemplateReader reader){
        this.field=data;
        this.order=order.order();
        this.reader=reader;
    }

    public String getOrderByString() throws Exception{
        String syntax=this.reader.getSyntax().get("order");
        syntax=syntax.replace("{column}", field.getColumnName());
        syntax=syntax.replace("{mode}", this.order);
        return syntax;
    }

    public FieldData getField() {
        return field;
    }

    public void setField(FieldData field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }



    public TemplateReader getReader() {
        return reader;
    }



    public void setReader(TemplateReader reader) {
        this.reader = reader;
    }
}
