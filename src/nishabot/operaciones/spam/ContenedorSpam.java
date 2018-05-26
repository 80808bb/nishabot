package nishabot.operaciones.spam;

public class ContenedorSpam {

    private Long idUsuario;
    private Long msUltimoMensaje;

    public ContenedorSpam(Long idUsuario, Long msUltimoMensaje) {
        this.idUsuario = idUsuario;
        this.msUltimoMensaje = msUltimoMensaje;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getMsUltimoMensaje() {
        return msUltimoMensaje;
    }

    public void setMsUltimoMensaje(Long msUltimoMensaje) {
        this.msUltimoMensaje = msUltimoMensaje;
    }

}
