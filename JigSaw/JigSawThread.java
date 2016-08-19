package JigSaw;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mason on 8/18/16.
 */
public class JigSawThread<Arg, Ret> implements Runnable {
    private static String defaultMessageString =
            "This JigSawThread was not actually given something to run.";
    private Method defaultRunMe = null;
    private int argCount;
    private ArrayList<Arg> args;
    private Arg[] arga;
    private Ret ret;
    private boolean JobCompleted = false;
    private boolean JobFailed = false;
    private Method runMe;
    public static void defRun(){System.out.println(defaultMessageString);}
    
    public JigSawThread(){
        this(0, null, null);
    }
    
    public JigSawThread(int argc, Arg[] _args, Method method){
        setArgCount(argc);
        setArgs(_args);
        try {
            defaultRunMe = this.getClass().getMethod("defRun");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        runMe = method;
    }
    
    public void setRun(Method runnable)
    {
        runMe = runnable;
    }
    //public Runnable;
    
    public void setArgCount(int n){argCount = (n >= 0) ? n : 0;}
    public int getArgCount(){return argCount;}
    public void setArgs(Arg[] _args){
        args = new ArrayList<Arg>();
        args.addAll(Arrays.asList(_args));
    }
    public ArrayList<Arg> getArgs(){return args;}
    
    public Ret getReturnValue(){
        return ret;
    }
    public boolean isJobCompleted(){return JobCompleted;}
    public boolean isJobFailed(){return JobFailed;}
    
    @Override
    public void run() {
        if (runMe == null){
            try {
                runMe = this.getClass().getMethod("defRun");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        try {
            ret = (Ret) runMe.invoke(this, args.toArray());
        } catch (IllegalAccessException e) {
            JobFailed = true;
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            JobFailed = true;
        } catch (ClassCastException e){
            e.printStackTrace();
            JobFailed = true;
        } finally {
            JobCompleted = true;
        }
        
    }
}
