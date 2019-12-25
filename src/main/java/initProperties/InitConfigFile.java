package initProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class InitConfigFile {
    private static InitConfigFile singleConfigInstance = null;
    private Properties properties;


    private InitConfigFile() throws IOException {
        String configFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("config.properties")).getFile();
        properties = new Properties();
        InputStream inputStream = new FileInputStream(configFilePath);
        properties.load(inputStream);
    }

    public static InitConfigFile setConfigFile() throws IOException {
        if (singleConfigInstance == null) {
            singleConfigInstance = new InitConfigFile();
        }
        return singleConfigInstance;
    }

    public String getProductionUrl(){
        return properties.getProperty("production");
    }

    public String getWatchListDirectory(){
        return properties.getProperty("dir.watchlist");
    }
}