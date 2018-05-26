package nishabot.main;

import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import nishabot.entidades.comandos.*;
import nishabot.entidades.comandos.Admin.*;
import nishabot.operaciones.utilidades.CargadorConfig;

public class NishabotMain extends ListenerAdapter {

    private static JDA objBot;

    public static void main(String[] args) throws LoginException, InterruptedException {
        Properties config = CargadorConfig.cargar("res/ficheros/config/config.xml");
        String token = config.getProperty("token");

        objBot = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        objBot.getPresence().setPresence(Game.listening("!nisha"), false);
        objBot.addEventListener(new NishabotMain());
    }

    public void onMessageReceived(MessageReceivedEvent ev) {
        if(!ev.getMessage().getContentRaw().startsWith("!")) return;
        if(ev.getAuthor().isBot()) return;

        String[] arrArgs = ev.getMessage().getContentRaw().substring(1).toLowerCase().split(" ");
        evaluarComando(arrArgs, ev);
    }

    private void evaluarComando(String[] arrArgs, MessageReceivedEvent ev) {
        String comando = arrArgs[0];

        switch(comando) {
            case "nisha":
                new Nisha().enviarFoto(ev.getChannel(), ev.getMessage(), ev.getAuthor());
                break;
            case "negro":
                NegroComando.enviarMensaje(ev.getChannel(), ev.getGuild());
                break;
            case "ntop":
                Ntop.enviarMensaje(ev.getChannel(), ev.getGuild(), objBot.getSelfUser().getAvatarUrl());
                break;
            case "nisa":
                Nisa.enviarFoto(ev.getChannel());
                break;
            case "pole":
                Pole.enviarFoto(ev.getChannel());
                break;
            case "vote":
                Vote.reaccionar(ev.getMessage(), ev.getGuild());
                break;
            case "sancho":
                Sancho.enviarMensaje(ev.getChannel());
                break;
            case "negrata":
                // Comando de admin
                CambioNegro.cambiarNegro(ev.getAuthor(), ev.getGuild(), ev.getChannel(), arrArgs[1]);
                break;
        }

    }

}
