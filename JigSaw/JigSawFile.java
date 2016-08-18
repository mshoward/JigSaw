package JigSaw;

import JigSaw.iJigs.iJigSaw;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.net.URI;

/**
 * Created by mason on 8/17/16.
 */
public class JigSawFile extends File
        implements iJigSaw
{
    public JigSawFile(@NonNls String pathname) {
        super(pathname);
    }
    
    public JigSawFile(String parent, @NonNls String child) {
        super(parent, child);
    }
    
    public JigSawFile(File parent, @NonNls String child) {
        super(parent, child);
    }
    
    public JigSawFile(URI uri) {
        super(uri);
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
}
