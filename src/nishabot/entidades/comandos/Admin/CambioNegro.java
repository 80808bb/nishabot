package nishabot.entidades.comandos.Admin;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import nishabot.entidades.comandos.Nisha;

public class CambioNegro extends ComandoAdmin {

    public static void cambiarNegro(User usuarioAdmin, Guild guild, MessageChannel mc, String idUsuarioNegro) {
        if(!comprobarAdmin(usuarioAdmin)) return;
        Nisha.darNegro(guild.getMemberById(idUsuarioNegro).getUser(), guild, mc);
    }
}
