package img.imaginary.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import img.imaginary.exception.ReadFileException;

public class ConfigReader {

    private final Properties properties = new Properties();
    
    public ConfigReader(String configFileName) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream(configFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ReadFileException("the configuration file " + configFileName + " was not read", e);
        }
    }
        
    public String getProperty(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name of property can't be null");
        }
        return properties.getProperty(name);
    }
}
