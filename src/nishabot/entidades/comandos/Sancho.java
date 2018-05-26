package nishabot.entidades.comandos;

import net.dv8tion.jda.core.entities.MessageChannel;

import java.io.File;

public class Sancho {

    public static void enviarMensaje(MessageChannel mc) {
        mc.sendFile(new File("res/imagenes/random/sancho.jpg")).queue();
    }

}