import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args){
        ConnectionFactory cf=new ConnectionFactory();
        try{

            Connection con=cf.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from eventos where codigo=?");
            stmt.setInt(1,1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                    System.out.printf("%d - %s\n",rs.getLong("codigo"),rs.getString("nome"));
                }



        }catch(Exception e){
            e.printStackTrace();

        }


    }
}
