package JigSaw;

import JigSaw.iJigs.iJigSaw;

/**
 * Created by mason on 8/18/16.
 */
public class JigSawMain implements iJigSaw, Runnable{
    
    private JigSawConf conf;
    
    public JigSawMain(){
        conf = new JigSawConf();
        
    }
    
    
    @Override
    public void Connect(iJigSaw piece) {
        
    }
    
    @Override
    public void Connect(Iterable<iJigSaw> iterablePieces) {
        
    }
    
    @Override
    public void ConnectOut(iJigSaw piece) {
        
    }
    
    @Override
    public void ConnectOut(Iterable<iJigSaw> iterablePieces) {
        
    }
    
    @Override
    public void ConnectIn(iJigSaw piece) {
        
    }
    
    @Override
    public void ConnectIn(Iterable<iJigSaw> iterablePieces) {
        
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
    public void run() {
        
    }
}
