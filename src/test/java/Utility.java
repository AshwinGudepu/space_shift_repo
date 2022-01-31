import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility {
    public static String readProperty(String propertyName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(currentDirectory+"/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(propertyName);
    }
}
