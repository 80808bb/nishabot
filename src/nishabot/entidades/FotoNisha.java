package nishabot.entidades;

import java.io.File;

public class FotoNisha {

    private Integer numero;
    private File foto;

    public FotoNisha(Integer numero) {
        this.numero = numero;
        foto = new File("res/imagenes/nishafotos/" + numero + ".jpg");
    }

    public Integer getNumero() {
        return numero;
    }

    public File getFoto() {
        return foto;
    }

}
