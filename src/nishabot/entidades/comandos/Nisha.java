package nishabot.entidades.comandos;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.GuildController;
import nishabot.entidades.FotoNisha;
import nishabot.entidades.Negro;
import nishabot.operaciones.IOFicherosNegro;
import nishabot.operaciones.spam.ComprobadorSpam;
import nishabot.operaciones.utilidades.CargadorConfig;

public class Nisha {

    private Integer numero;
    private FotoNisha fotoNisha;
    private static Negro negro = IOFicherosNegro.leerNegro();

    private static final Properties configNishafotos = CargadorConfig.cargar("res/ficheros/config/configNishafotos.xml");
    private static final Integer cantidadFotos = Integer.parseInt(configNishafotos.getProperty("cantidadFotos"));
    private static final Integer numeroNegro = Integer.parseInt(configNishafotos.getProperty("numeroNegro"));
    private static final Long msSpam = Long.parseLong(configNishafotos.getProperty("msSpam"));
    private static final ComprobadorSpam antispam = new ComprobadorSpam(msSpam);

    public Nisha() {
        numero = generarNumero();
        fotoNisha = new FotoNisha(numero);
    }

    private int generarNumero() {
       return new Random().nextInt((cantidadFotos - 1) + 1) + 1;
    }

    public void enviarFoto(MessageChannel mc, Message message, User user) {
        if(!antispam.comprobar(user, System.currentTimeMillis())) {
            message.addReaction(message.getGuild().getEmotesByName("thonkang", true).get(0)).queue();
            return;
        }
        mc.sendFile(fotoNisha.getFoto()).queue();

        if(fotoNisha.getNumero().equals(numeroNegro)) {
            darNegro(user, message.getGuild(), mc);
        }
    }

    public static Negro getNegro() {
        if(negro == null) {
            negro = IOFicherosNegro.leerNegro();
        }
        return negro;
    }

    public static void darNegro(User user, Guild guild, MessageChannel mc) {
        if(esNegro(user)) return;

        if(getNegro() != null) {
            IOFicherosNegro.escribirRank(negro);
        }

        quitarTodosNegros(guild);

        guild.getController().addSingleRoleToMember(guild.getMember(user),
                guild.getRolesByName("campeón del negro", true).get(0)).queue();

        negro = new Negro(user.getId(), System.currentTimeMillis());
        IOFicherosNegro.escribirNegro(negro);

        String mensajeNuevoNegro = guild.getMember(user).getAsMention() + " es ahora el negro.";
        mc.sendMessage(mensajeNuevoNegro).queue();
    }

    public static void quitarTodosNegros(Guild guild) {
        // Quita el rol del negro a cualquier miembro que lo tenga
        Role rolNegro = guild.getRolesByName("campeón del negro", true).get(0);
        List<Member> listaNegros = guild.getMembersWithRoles(rolNegro);
        GuildController gc = guild.getController();
        for(Member negro : listaNegros) {
            gc.removeSingleRoleFromMember(negro, rolNegro).queue();
        }

        // Vacía el fichero del negro
        IOFicherosNegro.vaciarNegro();

    }

    public static boolean esNegro(User user) {
        if(getNegro() == null) {
           return false;
        }
        return user.getId().equals(getNegro().getIdUser());
    }


}
