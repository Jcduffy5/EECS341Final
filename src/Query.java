/**
 * Created by John on 12/5/2016.
 */
public class Query {
    Long startTime;

    public static void main(String args[]) {
        Query test = new Query();
        test.findProduct("Lights");
        test.compareProduct("lights", "timex");
        test.findEmp();
        test.inStock("battlefield",44109 );
        test.deptSearch("electronics");
        test.addproduct("mens Timex Weekender", 30.00, "an affordable watch", "accessories", 1,1,"c", 3,2 );



    }
    public JtableHelper findProduct(String product){
         startTime = System.nanoTime();
        String query = "SELECT product.prodName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0 " +
                "WHERE product.prodID= r0.prodID AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Aisle", "Bay", "Shelf"};

                return new JtableHelper(headers,this.establishConnection(query));






    }

    public void addproduct(String prodName, double price, String specs,String deptName, int empID, int storeID, String aisle, int bay, int shelf){
        String query = "SELECT MAX(product.prodID) " +
                "FROM product";
        String[][] temp = this.establishConnection(query);
        Integer maxID = Integer.parseInt(temp[0][0]);
         query = "SELECT department.deptID "+
        "FROM department "+
        "WHERE department.deptName LIKE '%"+deptName+"%'";
         temp = this.establishConnection(query);
        Integer deptID = Integer.parseInt(temp[0][0]);
        maxID++;
        query = "SELECT * "+
                "FROM location "+
                "WHERE location.bay = "+bay+" AND location.aisle= '"+aisle+"' AND location.shelf=  "+ shelf;
        temp = this.establishConnection(query);
        query = "INSERT INTO product(prodID, prodName, price, specs) VALUES ("+maxID+", '"+prodName+"', "+price+", '"+specs+"')";
        this.insert(query);

        query = "INSERT INTO r0 (prodID, deptID, empID, storeID, aisle, bay, shelf) VALUES ("+maxID+", "+deptID+", "+empID+", "+storeID+", '"+aisle+"', "+bay+", "+shelf+")";

        this.insert(query);
        if(temp[0][0]== null){
            query = "INSERT INTO location ( bay,aisle, shelf) VALUES ("+bay+", '"+aisle+"'," +shelf+")";

            this.insert(query);
        }





    }

    public JtableHelper compareProduct(String prod1, String prod2){
        startTime = System.nanoTime();
        String query = "SELECT product.prodName, product.specs " +
                "FROM product " +
                "WHERE product.prodName LIKE '%"+prod1+"%' OR product.prodName LIKE '%"+prod2+"%'";
        String[] headers = {"Product Name", "Specifications"};

        return new JtableHelper(headers,this.establishConnection(query));
    }

    public JtableHelper findEmp(){
        startTime = System.nanoTime();
        String query = "SELECT employee.empName, employee.phone FROM employee WHERE employee.onClock";

        String[] headers = {"Name", "Work Phone Number"};

        return new JtableHelper(headers,this.establishConnection(query));
    }
    public JtableHelper inStock(String product, int zip ){
       startTime = System.nanoTime();
        String query = "SELECT product.prodName, store.storeName, store.street, store.city, store.state, store.ZipCode FROM product, r0, store WHERE product.prodID= r0.prodID AND r0.storeID = store.storeID AND store.zipCode = '"+zip+"' AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Store", "Street", "City", "State", "Zip Code"};

        return new JtableHelper(headers,this.establishConnection(query));
    }

    public JtableHelper deptSearch( String dept){
        startTime = System.nanoTime();
        String query = "SELECT department.deptName, product.prodName,r0.aisle, r0.bay " +
                "FROM  r0, department, product " +
                "WHERE  product.prodID = r0.prodID AND r0.deptID = department.deptID AND department.deptName LIKE '%"+dept+"%'";
        String[] headers = {"Department","Product Name","Aisle", "Bay"};

        return new JtableHelper(headers,this.establishConnection(query));
    }




    private String[][] establishConnection( String query){
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeQuery(query);
        System.out.println(System.nanoTime()-startTime);
        return connection.processResults();

    }
    private void insert( String query) {
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeUpdate(query);
        System.out.println(System.nanoTime() - startTime);
    }
}
