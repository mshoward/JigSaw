package JigSaw;

import JigSaw.iJigs.iThinkable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mason on 8/19/16.
 */
public abstract class abstractJigSawCell<T, R>
        extends JigSawThread<T,R>
        implements iThinkable<T,R>
{
    private ArrayList<JigSawDataNode<T>> inDatas;
    private ArrayList<JigSawDataNode<R>> outDatas;
    private ArrayList<iThinkable> IN;
    private ArrayList<iThinkable> OUT;
    
    
     public abstractJigSawCell(){
         super();
     }
    
    public void setJob(Method method, int argc, T[] _args){
        this.setRun(method);
        this.setArgCount(argc);
        this.setArgs(_args);
    }
    
    @Override
    public void InputData(ArrayList<JigSawDataNode<T>> lists) {
        inDatas = lists;
    }
    
    @Override
    public void PushData(JigSawDataNode<T> node) {
        inDatas.add(node);
    }
    
    @Override
    public ArrayList<JigSawDataNode<R>> OutputData() {
        return outDatas;
    }
    
    @Override
    public ArrayList<iThinkable> getOutputChannels() {
        return OUT;
    }
    
    @Override
    public ArrayList<iThinkable> getInputChannels() {
        return null;
    }
    
    @Override
    public void setOutputChannels(ArrayList<iThinkable> outs) {
        
    }
    
    @Override
    public void setInputChannels(ArrayList<iThinkable> ins) {
        
    }
    
    @Override
    public ArrayList<JigSawDataNode<T>> getCurrData() {
        return null;
    }
    
    @Override
    public void setCurrData(ArrayList<JigSawDataNode<T>> data) {
        
    }
    
    /**
     * @apiNote unless overridden, Think() acts as the identity function.
     */
    @Override
    public void Think(){
        Iterator<JigSawDataNode<T>> it = inDatas.iterator();
        try{
            while (it.hasNext()){
                outDatas.add((JigSawDataNode<R>) it.next());
            }
        }catch (ClassCastException e){
            e.printStackTrace();
            outDatas = null;
        }
    }
}
