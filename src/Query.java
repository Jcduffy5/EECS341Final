/**
 * Created by John on 12/5/2016.
 */
public class Query {

    public static void main(String args[]) {
        Query test = new Query();
        test.findProduct("Lights");


    }
    public void findProduct(String product){
        String query = "SELECT product.prodName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0 " +
                "WHERE product.prodID= r0.prodID AND product.prodName LIKE '%"+product+"%'";
        this.establishConnection(query);

    }

    public void establishConnection( String query){
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeQuery(query);

    }

}
