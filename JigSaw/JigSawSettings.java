package JigSaw;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.Properties;

/**
 * Created by mason on 8/2/16.
 */
public class JigSawSettings {
    private static String FilePath = System.getProperty("user.dir");
    private static String PieceName = "localhost";
    private static String PieceType = "Talker";
    public Properties properties;

    public JigSawSettings() throws IOException {
        properties = new Properties();
        InitDefaultProperties();


    }

    private void InitDefaultProperties(){
        properties.setProperty("FilePath", FilePath);
        properties.setProperty("PieceName", PieceName);
        properties.setProperty("PieceType", PieceType);

    }

    private FileReader getConfFile() throws IOException {
        return null;
    }
    private boolean mkConfFile() {
        return false;
    }

}
