package kakao.sollyj.streamex;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        File file = new File("config.properties");
        try(FileInputStream fis = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fis);
            System.out.println(properties.getProperty("url"));
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
