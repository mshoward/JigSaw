package JigSaw;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * Created by mason on 8/16/16.
 * JigSaw.JigSawRunner is the thread manager.
 * It keeps track of thread creation, thread failures, and running jobs.
 *
 */
public class JigSawRunner implements Executor{
    private static volatile ArrayList<Thread> pool = new ArrayList<Thread>();
    private static volatile ArrayList<Boolean> living = new ArrayList<Boolean>();
    private static volatile SimpleMutex simpleMutex = new SimpleMutex();
    private static Thread.UncaughtExceptionHandler onThreadMurder = new ThreadKiller();
    private static volatile boolean failure = false;
    
    public void startThread(int index){
        if (!living.get(index)){
            pool.get(index).run();
            living.set(index, pool.get(index).isAlive());
        }
    }
    public Iterator<Thread> getThreadIterator(){
        return pool.iterator();
    }
    public int size(){
        return pool.size();
    }
    
    public int countLiving(){
        int ret = -1;
        try{
            JigSawErrorHandling.AddUsage();
            simpleMutex.acquire();
            Iterator<Boolean> iterator = living.iterator();
            ret = 0;
            while (iterator.hasNext()){
                if (iterator.next())  ret++;
            }
        }
        catch (Exception e){
            ret = -1;
            JigSawErrorHandling.LogIt(e, "Error trying to count living strings");
        }
        finally {
            JigSawErrorHandling.ReleaseUsage();
            simpleMutex.release();
        }
        return ret;
    }
    public boolean getFailure(){
        return failure;
    }
    private static void setFailure(boolean val){
        failure = (val && failure);
    }
    
    public JigSawRunner(){
        //// TODO: 8/17/16 Not sure what should go here
    }
    
    public JigSawRunner(@NotNull Runnable runnable) {
        this.execute(runnable);
    }
    public void execute(@NotNull Runnable command)
            throws NullPointerException,
            RejectedExecutionException
    {
        boolean success = false;
        if (command == null)
            throw new NullPointerException("Runnable was null.");
        try {
            addNew(command, true);
            success = true;
        }
        catch (InterruptedException e){
            success = false;
        }
        setFailure(success);
    }//end execute()
    
    @Contract("null, _ -> fail")
    private void addNew(Runnable runnable, boolean doExec) throws InterruptedException
    {
        JigSawErrorHandling.AddUsage();
        boolean doThrow = false;
        InterruptedException throwme = null;
        if (runnable== null)
            throw new NullPointerException("Null runnable was null");
        Thread thread = new Thread(runnable);
        try {
            simpleMutex.acquire();
            thread.setUncaughtExceptionHandler(onThreadMurder);
            pool.add(thread);
            if (doExec) thread.run();
            living.add(pool.get(pool.size() - 1).isAlive());
        }
        catch(InterruptedException e){
            throwme = e;
            JigSawErrorHandling.LogIt(e, "interrupted while trying to acquire SimpleMutex\n");
            doThrow = true;
        }
        finally {
            JigSawErrorHandling.ReleaseUsage();
            simpleMutex.release();
            if (doThrow) throw throwme;
        }
        
    }
    private static void notifyThreadDeath(Thread thread){
        try {
            JigSawErrorHandling.AddUsage();
            simpleMutex.acquire();
            int ret = pool.indexOf(thread);
            if (ret > -1)
                living.set(ret, false);
            //thread.
        }
        catch(InterruptedException e){
            JigSawErrorHandling.LogIt(e, "Error Acquiring mutex or finding thread that failed");
        }
        finally {
            simpleMutex.release();
            JigSawErrorHandling.ReleaseUsage();
        }
    }
    private static class SimpleMutex{
        private Semaphore semaphore;
        SimpleMutex(){
            semaphore = new Semaphore(1);
        }
        boolean acquire()
                throws InterruptedException
        {
            semaphore.acquire();
            return true;
        }
        boolean release(){
            if (semaphore.availablePermits() == 0){
                semaphore.release();
                return true;
            }
            else{
                return false;
            }
        }
        
        
    }
    private static class ThreadKiller implements Thread.UncaughtExceptionHandler
    {
        
        
        /**
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         *
         * @param t the thread
         * @param e the exception
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            JigSawErrorHandling.LogIt(new Exception(e),
                    "Uncaught Exception in a thread registerd to JigSaw.JigSawRunner -- logging it\n");
            JigSawRunner.notifyThreadDeath(t);
        }
    }
}
