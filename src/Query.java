/**
 * Created by John on 12/5/2016.
 */
public class Query {
    Long startTime;

    /**
     * testing queries
     * @param args
     */
    public static void main(String args[]) {
        Query test = new Query();
        test.findProduct("Lights");
        test.compareProduct("lights", "timex");
        //test.findEmp(44109);
        test.inStock("battlefield",44109 );
        test.deptSearch("electronics");
        test.addproduct("mens Timex Weekender", 30.00, "an affordable watch", "accessories", 1,1,"c", 3,2 );



    }

    /**
     * generates a query that searches for a specific product
     * @param product a product name typed by the user
     * @return Jhelper containing results and column headers
     */
    public JtableHelper findProduct(String product){
         startTime = System.nanoTime();
        String query = "SELECT product.prodName, r0.aisle, r0.bay, r0.shelf " +
                "FROM product, r0 " +
                "WHERE product.prodID= r0.prodID AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Aisle", "Bay", "Shelf"};

                return new JtableHelper(headers,this.establishConnection(query));






    }

    /**
     * adds a product to the database
     * @param prodName the name of the product
     * @param price the price of the product
     * @param specs the specifications of the product
     * @param deptName the department the product is to be added to
     * @param empID the employee who is an expert in the product
     * @param storeID the store at which the product will be carried
     * @param aisle the aisle at which the product can be found
     * @param bay the bay at which the product can be found
     * @param shelf the shelf at which the product can be found
     */

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

    /**
     * generates a query comparing the specs of two products
     * @param prod1 the first user inputted product
     * @param prod2 the second user inputted product
     * @return jatablehelper object containing query results and column headers
     */

    public JtableHelper compareProduct(String prod1, String prod2){
        startTime = System.nanoTime();
        String query = "SELECT product.prodName, product.specs " +
                "FROM product " +
                "WHERE product.prodName LIKE '%"+prod1+"%' OR product.prodName LIKE '%"+prod2+"%'";
        String[] headers = {"Product Name", "Specifications"};

        return new JtableHelper(headers,this.establishConnection(query));
    }


    /**
     * creates a query to find available employees at a store
     * @param zip the zipcode of a store to search for employees
     * @return object containing results and column headers
     */

    public JtableHelper findEmp(String zip){
        startTime = System.nanoTime();
        Integer zipCode = Integer.parseInt(zip);
        String query = "SELECT DISTINCT employee.empName, store.storeName, employee.phone FROM employee, store, r0 WHERE employee.onClock AND r0.empID = employee.empID AND r0.storeID = store.storeID AND store.zipCode = "+zipCode;

        String[] headers = {"Name", "Store", "Work Phone Number"};

        return new JtableHelper(headers,this.establishConnection(query));
    }

    /**
     * generates a query to check if a product is stocked at a specific store
     * @param product user inputted product name
     * @param zip user inputted store zipcode
     * @return
     */
    public JtableHelper inStock(String product, int zip ){
       startTime = System.nanoTime();
        String query = "SELECT product.prodName, store.storeName, store.street, store.city, store.state, store.ZipCode FROM product, r0, store WHERE product.prodID= r0.prodID AND r0.storeID = store.storeID AND store.zipCode = '"+zip+"' AND product.prodName LIKE '%"+product+"%'";
        String[] headers = {"Product Name", "Store", "Street", "City", "State", "Zip Code"};

        return new JtableHelper(headers,this.establishConnection(query));
    }

    /**
     * generates a query returning all products within the department
     * @param dept user inputted department name
     * @return object containing results and column headers
     */
    public JtableHelper deptSearch( String dept){
        startTime = System.nanoTime();
        String query = "SELECT department.deptName, product.prodName,r0.aisle, r0.bay " +
                "FROM  r0, department, product " +
                "WHERE  product.prodID = r0.prodID AND r0.deptID = department.deptID AND department.deptName LIKE '%"+dept+"%'";
        String[] headers = {"Department","Product Name","Aisle", "Bay"};

        return new JtableHelper(headers,this.establishConnection(query));
    }


    /**
     * calls the targetConnection class to run the query
     * @param query query generated by the helper method
     * @return string array of the result
     */

    private String[][] establishConnection( String query){
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeQuery(query);
        System.out.println(System.nanoTime()-startTime);
        return connection.processResults();

    }

    /**
     * calls methods within targetConnection class to insert query
     * @param query generated by above helper methods
     */
    private void insert( String query) {
        targetConnection connection = new targetConnection();
        connection.establishConnection();
        connection.executeUpdate(query);
        System.out.println(System.nanoTime() - startTime);
    }
}
