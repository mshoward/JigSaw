package JigSaw;

import java.io.*;
import java.util.ArrayDeque;
import java.util.concurrent.ExecutionException;

/**
 * Created by mason on 8/16/16.
 */
public class JigSawErrorHandling {
    private static PrintWriter pw;
    public static volatile ArrayDeque<JigSawPair<Exception, String>> errors;
    public static volatile int accessors;
    private static volatile boolean didInit = false;
    private static File file;
    
    public static void AddUsage(){
        accessors++;
    }
    public static void ReleaseUsage(){
        accessors--;
    }
    
    private static void QueueIt(JigSawPair<Exception, String> pair){
        errors.addFirst(pair);
    }
    
    public static Runnable GetNewLoggingRunner(){
        return new Runnable() {
            @Override
            public void run() {
                JigSawErrorHandling.processQueue();
            }
        };
    }
    
    public static void processQueue(){
        while(accessors > 0){
            if (errors.peekFirst() != null){
                JigSawPair<Exception, String> pair = errors.removeFirst();
                PrintIt(pair.one, pair.two);
            }
        }
    }
    
    private static void setFile(String string){
        file = new File(string);
    }
    
    public static void init() throws ExecutionException
    {
        init("log.txt");
    }
    
    public static void init(String filename) throws ExecutionException
    {
        if(!didInit) {
            accessors = 0;
            AddUsage();
            try {
                errors = new ArrayDeque<JigSawPair<Exception, String>>();
                setFile(filename);
                pw = new PrintWriter(file);
            } catch (Exception e) {
                LogIt(e, "Failure");
                throw new ExecutionException("Failure.", e);
            } finally {
                ReleaseUsage();
            }
            didInit = true;
        }
    }
    
    public static void LogIt(Exception e){
        LogIt(new JigSawPair<Exception, String>(e, ""));
        //QueueIt(e);
    }
    public static void LogIt(Exception e, String msg){
        LogIt(new JigSawPair<Exception, String>(e,msg));
    }
    public static void LogIt(JigSawPair<Exception, String> pair){
        QueueIt(pair);
    }
    public static void PrintIt(Exception e, String msg){
        pw.append("\n\n     +++++  Exception is being logged  +++++\n\n");
        pw.append(msg);
        pw.append("\n");
        pw.append(e.getMessage());
        pw.append("\n");
        pw.append(e.toString());
        e.printStackTrace(pw);
        flush();
    }
    
    public static void flush(){
        pw.flush();
    }
}
