/**
 * Created by John on 12/5/2016.
 */
import java.sql.*;

public class targetConnection {

        public static void main(String args[]){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/target?autoReconnect=true&useSSL=false","root","jrgreen");
//here sonoo is database name, root is username and password
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select product.prodName, product.price from product where product.price= 6.49");
                while(rs.next())
                    System.out.println(rs.getString(1)+"  "+rs.getString(2));
                con.close();
            }catch(Exception e){ System.out.println(e);}
        }
    }