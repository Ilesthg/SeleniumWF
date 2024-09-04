package utilities;

import java.sql.*;
import java.util.HashMap;

public class DBConnection {

    public synchronized HashMap<String, String> returnDB(String sql){
        HashMap<String, String> hm = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_qdmp?serverTimezone=UTC","Hugo","");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData rsMD = rs.getMetaData();


            while (rs.next()){
                for (int i = 1; i < rsMD.getColumnCount(); i++) {
                    System.out.println(rsMD.getColumnName(i));
                    System.out.println("-----------");
                    System.out.println(rs.getString(i));
                    hm.put(rsMD.getColumnName(i), rs.getString(i));
                }
            }
            con.close();
        } catch (Exception e) {
           System.out.println(e);
        }
        return hm;
    }
}
