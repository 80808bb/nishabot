package nishabot.operaciones.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CargadorConfig {

    public static Properties cargar(String ruta) {
        Properties config = new Properties();
        try {
            config.loadFromXML(new FileInputStream(ruta));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

}
