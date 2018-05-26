package nishabot.entidades.comandos;

import java.io.File;

import net.dv8tion.jda.core.entities.MessageChannel;

public class Nisa {

    public static void enviarFoto(MessageChannel mc) {
        mc.sendFile(new File("res/imagenes/random/nisa.jpg")).queue();
    }
}
