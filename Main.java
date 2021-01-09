
import java.sql.SQLException;

import org.apache.xmlrpc.WebServer;

public class Main {
    public static void main(String[] args) throws SQLException{
        WebServer ws  = new WebServer(3000);
        ws.addHandler("serverRPC", new Crud());
        ws.start();
        System.out.println("Server RPC Aktif");
    }
}
