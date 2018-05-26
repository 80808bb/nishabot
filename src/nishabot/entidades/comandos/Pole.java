package nishabot.entidades.comandos;

import java.io.File;

import net.dv8tion.jda.core.entities.MessageChannel;

public class Pole {

    public static void enviarFoto(MessageChannel mc) {
        mc.sendFile(new File("res/imagenes/random/pole.jpg")).queue();
    }

}
