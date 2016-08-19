package JigSaw.iJigs;

import JigSaw.JigSawDataNode;

import java.util.ArrayList;

/**
 * Created by mason on 8/19/16.
 */
public interface iThinkable<T, R> {
    void InputData(ArrayList<JigSawDataNode<T>> lists);
    void PushData(JigSawDataNode<T> node);
    ArrayList<JigSawDataNode<R>> OutputData();
    
    ArrayList<iThinkable> getOutputChannels();
    ArrayList<iThinkable> getInputChannels();
    
    void setOutputChannels(ArrayList<iThinkable> outs);
    void setInputChannels(ArrayList<iThinkable> ins);
    void Think();
    
    
    ArrayList<JigSawDataNode<T>> getCurrData();
    void setCurrData(ArrayList<JigSawDataNode<T>> data);
    
    
}
