package nishabot.entidades.comandos;

import java.util.ArrayList;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import nishabot.entidades.Negro;
import nishabot.operaciones.IOFicherosNegro;
import nishabot.operaciones.utilidades.MsADias;

public class Ntop {

    public static void enviarMensaje(MessageChannel mc, Guild guild, String iconUrlNishabot) {
        ArrayList<Negro> listaNegros = IOFicherosNegro.leerRank();

        int longitud = listaNegros.size();

        if(longitud == 0) {
            mc.sendMessage("Actualmenete no hay negros en el top.").queue();
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(java.awt.Color.YELLOW);
        eb.setTitle("TOP NEGROS 👑");

        for(int i = 0; i < longitud && i < 10; i++) {
            eb.addField((i + 1) + ". " + guild.getMemberById(listaNegros.get(i).getIdUser()).getEffectiveName(),
                    MsADias.convertir(listaNegros.get(i).getTiempo()), false);
        }

        eb.setFooter("Se actualiza cuando le quitan el negro a alguien.", iconUrlNishabot);
        mc.sendMessage(eb.build()).queue();

    }

}
