package nishabot.operaciones.spam;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class ComprobadorSpam {

    private Long msLimite;
    private ArrayList<ContenedorSpam> lista;
    private int contadorMensajes; // Se usa para limpiar la lista cada X mensajes
    private Long tiempoMinimoPurga;
    private int mensajesParaPurga;

    public ComprobadorSpam(Long msLimite) {
        this.msLimite = msLimite;
        lista = new ArrayList<ContenedorSpam>();
        contadorMensajes = 0;

        tiempoMinimoPurga = 60000L;
        mensajesParaPurga = 200;
    }

    public boolean comprobar(User usuario, Long msMensajeNuevo) {
        contadorMensajes++;
        for(ContenedorSpam contenedor : lista) {
            if (contenedor.getIdUsuario() == usuario.getIdLong()) {
                if (msMensajeNuevo - contenedor.getMsUltimoMensaje() < msLimite) {
                    return false;
                } else {
                    contenedor.setMsUltimoMensaje(msMensajeNuevo);
                    return true;
                }
            }
        }
        if(comprobarPurga()) {
            purga();
        }
        lista.add(new ContenedorSpam(usuario.getIdLong(), msMensajeNuevo));
        return true;
    }

    private boolean comprobarPurga() {
        if(contadorMensajes > mensajesParaPurga) {
            contadorMensajes = 0;
            return true;
        }
        return false;
    }

    private void purga() {
        ArrayList<ContenedorSpam> listaNueva = new ArrayList<ContenedorSpam>();
        Long tiempoActual = System.currentTimeMillis();

        for(ContenedorSpam contenedor : lista) {
            if(tiempoActual - contenedor.getMsUltimoMensaje() < tiempoMinimoPurga) {
                // Si el usuario ha enviado un mensaje hace menos de X tiempo: mantenerlo en la lista
                listaNueva.add(contenedor);
            }
        }
        lista = listaNueva;
    }

}
