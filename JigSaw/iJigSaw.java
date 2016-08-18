package JigSaw;

/**
 * Created by mason on 8/17/16.
 */
public interface iJigSaw {
    public void Connect(iJigSaw piece);
    public void Connect(Iterable<iJigSaw> iterablePieces);
    
    public void ConnectOut(iJigSaw piece);
    public void ConnectOut(Iterable<iJigSaw> iterablePieces);
    
    public void ConnectIn(iJigSaw piece);
    public void ConnectIn(Iterable<iJigSaw> iterablePieces);
    
    public void StartTalking();
    public void StopTalking();
    
    public void StartListening();
    public void StopListening();
    
    public void StartThinking();
    public void StopThinking();
}

