package ITU.Baovola.Gucci.DTO;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {
    String error;
    List<Object> data=new ArrayList();

    public void addData(Object obj){
        this.data.add(obj);
    }

    public List<Object> getData() {
        return data;
    }
    public void setData(List data) {
        this.data = data;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
