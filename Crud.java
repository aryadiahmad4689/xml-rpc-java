
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.util.logging.Level;
import java.sql.SQLException;

import java.util.logging.Logger;


public class Crud {
   private Connection connection;
   
   public Crud() throws SQLException{
       koneksi();
   }

   public void koneksi() throws SQLException{
       String db ="jdbc:mysql://localhost/rpc";
       String user = "root";
       String pass ="";

       try{
           DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           connection = (Connection) DriverManager.getConnection(db, user, pass);

           if(connection != null){
               System.out.println("koneksi db tidak berhasil");
           }
       }catch(SQLDataException ex){
        Logger.getLogger(Crud.class.getName()).log(Level.SEVERE,null,ex);
       }
    }

       public String getData(){
           String data ="";
           try{
               java.sql.Statement statement = connection.createStatement();
               java.sql.ResultSet rs = statement.executeQuery("Select * from biodata");

               while(rs.next()){
                   data += rs.getInt(1) + "," + rs.getString(2) + "," 
                   + rs.getString(3) +","+ rs.getString(4) + "-";

               }
           }catch(SQLException ex){
                Logger.getLogger(Crud.class.getName()).log(Level.SEVERE,null,ex);
           }
           return data;
       }

       public String insertData(String nama, String alamat, String status){
           String data="";
           String sql = "insert into biodata values(null,?,?,?)";

           try{
               java.sql.PreparedStatement ps = connection.prepareStatement(sql);
               ps.setString(1,nama);
               ps.setString(2,alamat);
               ps.setString(3,status);

               int kondisi = ps.executeUpdate();
               if (kondisi > 0){
                   data  = "berhasil";
               }else{
                   data = "gagal";
               }
            }catch(SQLException ex){
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE,null,ex);
         }
         return data;
    }
    
}
