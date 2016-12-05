/**
 * Created by John on 12/5/2016.
 */
import java.sql.*;

public class targetConnection {



        public static void main(String[] args)
        {
            try
            {
                // create our mysql database connection
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/target";
                Class.forName(myDriver).newInstance();
                Connection conn = DriverManager.getConnection(myUrl, "root", "jrgreen");

                // our SQL SELECT query.
                // if you only need a few columns, specify them by name instead of using "*"
                String query = "SELECT * FROM product";

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next())
                {
                    int prodID = rs.getInt("prodID");
                    String prodName = rs.getString("prodName");
                    String price = rs.getString("price");
                    String specs = rs.getString("specs");


                    // print the results
                    System.out.format("%s, %s, %s\n", prodID, price, specs);
                }
                st.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
    }

