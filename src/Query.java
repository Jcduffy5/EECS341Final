/**
 * Created by John on 12/5/2016.
 */
public class Query {

    public static void main(String args[]) {
        Query test = new Query();
        test.findProduct("Lights");
        test.compareProduct("lights");


    }
    public void findProduct(String product){
        String query = "SELECT product.prodName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0 " +
                "WHERE product.prodID= r0.prodID AND product.prodName LIKE '%"+product+"%'";
        this.establishConnection(query);

    }

    public void compareProduct(String prod1, String prod2){
        String query = "SELECT product.prodName, product.specs " +
                "FROM product " +
                "WHERE product.prodName LIKE '%"+prod1+"%' OR product.prodName LIKE '%"+prod1+"%'";
        this.establishConnection(query);
    }

    public void findEmpProd(String product){
        String query = "SELECT employee.empName, employee.phone" +
                "FROM product, r0, employee " +
                "WHERE product.prodID= r0.prodID AND r0.empID = employee.empID AND employee.onClock = '1' AND product.prodName LIKE '%"+product+"%'";
        this.establishConnection(query);
    }
    public void inStock(String product, int zip ){
        String query = "SELECT product.prodName, store.StoreName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0, store" +
                "WHERE product.prodID= r0.prodID AND r0.storeID = store.storeID AND store.zipCode ="+zip+" product.prodName LIKE '%"+product+"%'";
        this.establishConnection(query);
    }

    public void deptSearch( String dept){
        String query = "SELECT department.departmentName,r0.aisle, r0.bay" +
                "FROM  r0, department " +
                "WHERE  r0.deptID = department.deptID AND department.deptName LIKE '%"+dept+"%'";
        this.establishConnection(query);
    }




    public void establishConnection( String query){
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeQuery(query);

    }

}
