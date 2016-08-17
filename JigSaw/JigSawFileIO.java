package JigSaw;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mason on 8/17/16.
 */
public class JigSawFileIO implements iJigSaw {
    /**
     * Fields
     */
    private JigSawFile currFile;
    private ArrayList<JigSawFile> fileArrayList;
    
    public JigSawFileIO(){
        currFile = null;
        fileArrayList = new ArrayList<JigSawFile>();
    }
    public JigSawFileIO(File file){
        this();
        try {
            currFile = (JigSawFile) file;
            fileArrayList.add((JigSawFile) file);
        }
        catch(ClassCastException e){
            currFile = null;
        }
    }
    public JigSawFileIO(Iterator<File> fileIterator){
        this();
        while (fileIterator.hasNext())
            fileArrayList.add((JigSawFile)fileIterator.next());
        currFile = (fileArrayList.isEmpty()) ? null : fileArrayList.get(0);
    }
    public JigSawFile getBaseFile(){
        return this.currFile;
    }
    public ArrayList<JigSawFile> getBaseFileContainer(){
        return this.fileArrayList;
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
