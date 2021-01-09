
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Client {
    Vector<String> vector = null;
    XmlRpcClient client = null;
    String serverUrl;

    public Client() throws MalformedURLException {
        serverUrl = "http://localhot:1999/RPC2";
  
            client = new XmlRpcClient(serverUrl);

        vector = new Vector<>();

    }

    void showData() throws IOException{
        String ar ="";
        try{
            ar =(String)client.execute("serverRPC.getData",vector);
        }catch(XmlRpcException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
        StringTokenizer st = new StringTokenizer(ar,"-");
        
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }

    private void insertData(){
        String ar="";

        try{
            vector.addElement("P");
            vector.addElement("P");
            vector.addElement("P");
            ar =(String)client.execute("serverRPC.insertData",vector);
        }catch(XmlRpcException | IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        System.out.println(ar);
    }

    public static void main(String[] args){
        
        try{
            Client c = new Client();
            c.showData();
            c.insertData();
        }catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
