package nishabot.operaciones.spam;

public class ComprobadorSpamSimple {

    private Long msLimite;
    private Long msUltimoMensaje;

    public ComprobadorSpamSimple(Long msLimite) {
        this.msLimite = msLimite;
        msUltimoMensaje = 0L;
    }

    public boolean comprobar(Long msNuevoMensaje) {
        if(msNuevoMensaje - msUltimoMensaje >= msLimite) {
            msUltimoMensaje = msNuevoMensaje;
            return true;
        }
        return false;
    }

}
