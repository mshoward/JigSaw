package JigSaw;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mason on 8/17/16.
 * //// TODO: 8/17/16 Switch Conf file operations to JigSawIO file ops
 */
public class JigSawConf {
    private Properties properties;
    private JigSawFile jigConf;
    private JigSawFileIO jigio;
    private FileReader reader;
    private static String confFile = "JigSaw.JigSaw.conf";
    
    @NeverFail
    public JigSawConf(){
        JigSawErrorHandling.AddUsage();
        boolean fileFailure = false;
        jigConf = new JigSawFile(confFile);
        try {
            reader = new FileReader(jigConf);
        }
        catch (IOException e){
            JigSawErrorHandling.LogIt(e, "Failure to open default config File for reading\n");
            reader = null;
            fileFailure = true;
            this.loadPropertyDefaults();
        }
        if (!fileFailure){
            properties = new Properties();
            try {
                properties.load(reader);
            } catch (IOException e) {
                JigSawErrorHandling.LogIt(e, "Failure reading default config file\n");
                e.printStackTrace();
                this.loadPropertyDefaults();
            }
            finally {
                JigSawErrorHandling.ReleaseUsage();
            }
        }
        
    }//end default constructor
    
    private void loadPropertyDefaults() {
        //// TODO: 8/17/16 default properties here
    }
    
    
    public JigSawConf(Properties props){
        this();
        properties = props;
    }
    
    public String getCustomProp(String name){
        return properties.getProperty(name);
    }
    public void setCustomProp(String name, String val){
        //// TODO: 8/17/16 Open file, write, store key/value;
        
    }
    
    
}
