package nishabot.entidades;

public class Negro {

    private String idUser;
    private Long msInicio;
    private Long tiempo;

    public Negro(String idUser, Long msInicio, Long tiempo) {
        this.idUser = idUser;
        this.msInicio = msInicio;
        this.tiempo = tiempo;
    }

    public Negro(String idUser, Long msInicio) {
        this.idUser = idUser;
        this.msInicio = msInicio;
        this.tiempo = 0l;
    }

    public String getIdUser() {
        return idUser;
    }

    public Long getMsInicio() {
        return msInicio;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
    }

}
