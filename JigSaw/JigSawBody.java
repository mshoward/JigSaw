package JigSaw;

import JigSaw.iJigs.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by mason on 8/1/16.
 * //// TODO: 8/17/16 implement JigSaw.iJigs.iJigSaw
 * //// TODO: 8/17/16 JigSaw.JigSaw IO
 * // // TODO: 8/17/16 JigSaw.JigSaw conf
 * //// TODO: 8/17/16 JigSaw.JigSaw talker
 * //// TODO: 8/17/16 Jigsaw thinker
 */





public class JigSawBody implements JigSaw.iJigSaw {
    private String name;
    private String addr;
    private Properties properties;
    private HttpServer server;
    private HttpHandler handler;
    private JigSawRunner threadManager;



    public List<String> addrs;

    public JigSawBody(String _name, String _address, Properties _properties, HttpHandler _handler)
            throws IOException
    {
        threadManager = new JigSawRunner();
        
        this.name = _name;
        this.addr = _address;
        this.properties = _properties;
        this.server = HttpServer.create(new InetSocketAddress(InetAddress.getLocalHost(),
                Integer.parseInt(properties.getProperty("port"))),
                Integer.parseInt(properties.getProperty("backlogSize")));
        this.handler = _handler;
        server.createContext("some", handler);
        server.setExecutor(threadManager);
        


    }
    public String GetName() {return this.name;}
    public void SetName(String val){this.name = val;}
    

    public List<String> GetAddrs(){return null;}
    public void SetAddrs(List<String> stringList){}

    public void testme() throws IOException{
        java.net.ServerSocket socket = new ServerSocket();

    }
    
    
    @Override
    public void Connect(JigSaw.iJigSaw piece) {
        
    }
    
    @Override
    public void Connect(Iterable<JigSaw.iJigSaw> iterablePieces) {
        
    }
    
    @Override
    public void ConnectOut(JigSaw.iJigSaw piece) {
        
    }
    
    @Override
    public void ConnectOut(Iterable<JigSaw.iJigSaw> iterablePieces) {
        
    }
    
    @Override
    public void ConnectIn(JigSaw.iJigSaw piece) {
        
    }
    
    @Override
    public void ConnectIn(Iterable<JigSaw.iJigSaw> iterablePieces) {
        
    }
    
    @Override
    public void StartTalking() {
        
    }
    
    @Override
    public void StopTalking() {
        
    }
    
    @Override
    public void StartListening() {
        
    }
    
    @Override
    public void StopListening() {
        
    }
    
    @Override
    public void StartThinking() {
        
    }
    
    @Override
    public void StopThinking() {
        
    }
    
    @Override
    public void ReceiveData(JigSawDataNode D) {
        
    }
    
    @Override
    public JigSawDataNode SendData() {
        return null;
    }
}
