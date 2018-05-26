package nishabot.entidades.comandos.Admin;

import java.util.Properties;

import net.dv8tion.jda.core.entities.User;
import nishabot.operaciones.utilidades.CargadorConfig;

public class ComandoAdmin {

    private static final Properties config = CargadorConfig.cargar("res/ficheros/config/configAdmin.xml");

    public static boolean comprobarAdmin(User user) {
        for(String nombre: config.stringPropertyNames()) {
            if(user.getId().equals(config.getProperty(nombre))) {
                return true;
            }
        }
        return false;
    }
}
