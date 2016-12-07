/**
 * Created by John on 12/6/2016.
 */
public class JtableHelper {
    private String[] headers;
    private String[][] data;



    public JtableHelper(String[] headers, String[][] data){
        this.data = data;
        this.headers = headers;
    }
    public String[] getHeaders() {
        return headers;
    }
    public String[][] getData(){
        return data;
    }
}
