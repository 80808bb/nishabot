package nishabot.operaciones.utilidades;

import java.util.concurrent.TimeUnit;

public class MsADias {

    public static String convertir(long ms) {
        long dias = TimeUnit.MILLISECONDS.toDays(ms);
        ms -= TimeUnit.DAYS.toMillis(dias);
        long horas = TimeUnit.MILLISECONDS.toHours(ms);
        ms -= TimeUnit.HOURS.toMillis(horas);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(ms);
        ms -= TimeUnit.MINUTES.toMillis(minutos);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(ms);
        ms -= TimeUnit.SECONDS.toMillis(segundos);
        String salida = "";

        if(dias > 0) {
            salida += dias;
            if(dias == 1) {
                salida += " día";
            }else {
                salida += " días";
            }
        }
        if(horas > 0) {
            if(dias != 0) {
                salida += ", ";
            }
            salida += horas;
            if(horas == 1) {
                salida += " hora";
            }else {
                salida += " horas";
            }
        }
        if(minutos > 0) {
            if(dias != 0 || horas != 0) {
                salida += ", ";
            }
            salida += minutos;
            if(minutos == 1) {
                salida += " minuto";
            }else {
                salida += " minutos";
            }
        }
        if(segundos > 0) {
            if(dias != 0 || horas != 0 || minutos != 0) {
                salida += " y ";
            }
            salida += segundos;
            if(segundos == 1) {
                salida += " segundo";
            }else {
                salida += " segundos";
            }
        }
        return salida;
    }
}
