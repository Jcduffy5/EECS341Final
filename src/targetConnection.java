/**
 * Created by John on 12/5/2016.
 */
import java.sql.*;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class targetConnection {
    //fields
    Statement stmt;
    ResultSet rs;
    Connection con;

    public static void main(String args[]) {



    }

    /**
     * establishes the connection to the database
     * assumption: database is available
     */
    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/target?autoReconnect=true&useSSL=false", "root", "jrgreen");



        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * runs the query
     * @param query the query to be run in a string format
     */
    public void executeQuery(String query) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    /**
     * used for updates
     * @param query contains the update statement in a string
     */
    public void executeUpdate(String query) {

        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    /**
     * processes the results of the query
     * places results in string matrix
     * closes the connection
     * @return string matrix of all results
     */
    public String[][] processResults() {
        int nCol;
        List<String[]> table = new ArrayList<>();
        try {
            nCol = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] row = new String[nCol];
                for (int iCol = 0; iCol < nCol; iCol++) {
                    String obj = rs.getString(iCol + 1);
                    row[iCol] = obj;
                }
                table.add(row);
                for( String s : row){

                }

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] results = new String[table.size()][];
        for(int x = 0; x < table.size(); x++){
            results[x] = table.get(x);
        }
       /* for(int x = 0; x< results.length; x++) {
            for (int y = 0; y < results[x].length; y++) {
                System.out.print(results[x][y] + "                         ");
            }
            System.out.println();
        }*/

        return  results;




    }
}