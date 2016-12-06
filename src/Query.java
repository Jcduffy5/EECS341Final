/**
 * Created by John on 12/5/2016.
 */
public class Query {

    public static void main(String args[]) {
        Query test = new Query();
        test.findProduct("Lights");
        test.compareProduct("lights", "timex");
        test.findEmp();
        test.inStock("battlefield",44109 );
        test.deptSearch("electronics");


    }
    public Object[] findProduct(String product){
        String query = "SELECT product.prodName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0 " +
                "WHERE product.prodID= r0.prodID AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Aisle", "Bay", "Shelf"};
        Object[] result = {headers,this.establishConnection(query)};
        return result;



    }

    public Object[] compareProduct(String prod1, String prod2){
        String query = "SELECT product.prodName, product.specs " +
                "FROM product " +
                "WHERE product.prodName LIKE '%"+prod1+"%' OR product.prodName LIKE '%"+prod2+"%'";
        String[] headers = {"Product Name", "Specifications"};
        Object[] result = {headers,this.establishConnection(query)};
        return result;
    }

    public Object[] findEmp(){
        String query = "SELECT employee.empName, employee.phone FROM employee WHERE employee.onClock";

        String[] headers = {"Name", "Work Phone Number"};
        Object[] result = {headers,this.establishConnection(query)};
        return result;
    }
    public Object[] inStock(String product, int zip ){
        String query = "SELECT product.prodName, store.storeName, store.street, store.city, store.state, store.ZipCode FROM product, r0, store WHERE product.prodID= r0.prodID AND r0.storeID = store.storeID AND store.zipCode = '"+zip+"' AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Store", "Street", "City", "State", "Zip Code"};
        Object[] result = {headers,this.establishConnection(query)};
        return result;
    }

    public Object[] deptSearch( String dept){
        String query = "SELECT department.deptName,r0.aisle, r0.bay " +
                "FROM  r0, department " +
                "WHERE  r0.deptID = department.deptID AND department.deptName LIKE '%"+dept+"%'";
        String[] headers = {"Department", "Aisle", "Bay"};
        Object[] result = {headers,this.establishConnection(query)};
        return result;
    }




    public String[][] establishConnection( String query){
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeQuery(query);
        return connection.processResults();

    }

}
