package JigSaw;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class JigSawDataNode <T>{
    private T payload;
    
    
    private JigSawDataNode next;
    private JigSawDataNode prev;
    private JigSawDataNode<T> me;
    
    
    
    public JigSawDataNode(){
        this(null, null, null);
    }
    public JigSawDataNode(JigSawDataNode _prev, JigSawDataNode _next){
        this(null, _prev, _next);
    }
    public JigSawDataNode(T _payload, JigSawDataNode _prev, JigSawDataNode _next){
        payload = _payload;
        prev = _prev;
        next = _next;
        me = this;
    }
    public JigSawDataNode(@NotNull JigSawDataNode<T> node){
        /**
         * Creates a data node as a shallow copy of the one it's given.
         */
        this(node.getData(), node.getPrev(), node.getNext());
    }
    
    public JigSawDataNode(T data){
        payload = data;
    }
    
    public T getData(){
        return payload;
    }
    public void setData(T data){
        payload = data;
    }
    
    public JigSawDataNode getNext(){
        return next;
    }
    public JigSawDataNode setNext(JigSawDataNode node){
        next = node;
    }
    public JigSawDataNode getPrev(){
        return prev;
    }
    public JigSawDataNode setPrev(JigSawDataNode node){
        prev = node;
    }
    
    public void insertBefore(@NotNull JigSawDataNode node){
        if (prev != null){
            JigSawDataNode oprev = prev;
            me.setPrev(node);
            node.setPrev(oprev);
            node.setNext(me);
            oprev.setNext(node);
        }
        else{
            prev = node;
            node.setNext(me);
        }
    }
    private void fullInsertBefore(@NotNull JigSawDataNode node){
        //// TODO: 8/18/16 full insert of all before 
    }
    private void fullInsertAfter(@NotNull JigSawDataNode node){
        //// TODO: 8/18/16 full insert of all after
    }
    public void insertAfter(@NotNull JigSawDataNode node){
        if (next != null) {
            JigSawDataNode onext = next;
            me.setNext(node);
            node.setPrev(me);
            node.setNext(onext);
            onext.setPrev(node);
        }
        else{
            next = node;
            node.setPrev(me);
        }
    }
    
    public Iterator getIter(){
        return new Iterator<JigSawDataNode>() {
            JigSawDataNode curr;
            
            @Override
            public boolean hasNext() {
                return curr.getNext() != null;
            }
    
            @Override
            public JigSawDataNode next() {
                curr = curr.getNext();
                return curr;
            }
            public Iterator init(JigSawDataNode node){
                curr = node;
                return this;
            }
        }.init(me);
    }
    
    /**
     * goes backwards
     * @return an iterator that just goes backwards
     */
    public Iterator getRevIter(){
        return new Iterator<JigSawDataNode>() {
            JigSawDataNode curr;
        
            @Override
            public boolean hasNext() {
                return curr.getPrev() != null;
            }
        
            @Override
            public JigSawDataNode next() {
                curr = (curr != null) ? curr.getPrev() : null;
                return curr;
            }
            public Iterator init(JigSawDataNode node){
                curr = node;
                return this;
            }
        }.init(me);
    }
    
    
}
