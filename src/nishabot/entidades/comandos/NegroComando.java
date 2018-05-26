package nishabot.entidades.comandos;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import nishabot.entidades.Negro;
import nishabot.operaciones.utilidades.MsADias;

public class NegroComando {

    public static void enviarMensaje(MessageChannel mc, Guild guild) {
        Negro negro = Nisha.getNegro();
        if(negro == null) {
            mc.sendMessage("Actualmente no hay negro.").queue();
            return;
        }
        String[] arr = new String[] {negro.getIdUser(), Long.toString(negro.getMsInicio())};

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(java.awt.Color.YELLOW);
        eb.setTitle("EL NEGRO ACTUAL 📦");
        eb.setThumbnail(guild.getMemberById(arr[0]).getUser().getAvatarUrl());
        eb.addField(guild.getMemberById(arr[0]).getEffectiveName(),
                MsADias.convertir(System.currentTimeMillis() - Long.parseLong(arr[1])),
                false);

        mc.sendMessage(eb.build()).queue();

    }
}