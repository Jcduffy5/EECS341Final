/**
 * Created by John on 12/5/2016.
 */
import java.sql.*;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class targetConnection {
    Statement stmt;
    ResultSet rs;
    Connection con;

    public static void main(String args[]) {



    }

    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/target?autoReconnect=true&useSSL=false", "root", "jrgreen");



        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void executeQuery(String query) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

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