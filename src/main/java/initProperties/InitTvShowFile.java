package initProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class InitTvShowFile {
    private static InitTvShowFile singleWatchListInstance = null;
    private Properties properties;

    private InitTvShowFile() throws IOException {
        String configFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("tvShows.properties")).getFile();
        properties = new Properties();
        InputStream inputStream = new FileInputStream(configFilePath);
        properties.load(inputStream);
    }

    public static InitTvShowFile setWatchList()throws IOException{
        if(singleWatchListInstance == null){
            singleWatchListInstance = new InitTvShowFile();
        }
        return singleWatchListInstance;
    }

    public double getRating(){
        return Double.parseDouble(properties.getProperty("rating"));
    }

    public String[] getTvShows(){
        return properties.getProperty("tv").split(",");
    }
}
