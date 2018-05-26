package nishabot.entidades.comandos;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

public class Vote {

    public static void reaccionar(Message message, Guild guild) {
        message.addReaction("⬆").queue();
        message.addReaction("⬇").queue();
        message.addReaction(guild.getEmotesByName("esvastica", true).get(0)).queue();
    }
}
