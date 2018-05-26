package nishabot.operaciones;

import java.io.File;
import java.util.ArrayList;

import nishabot.entidades.Negro;
import nishabot.operaciones.utilidades.IOFicheros;
import nishabot.operaciones.utilidades.ListaNegrosSort;

public class IOFicherosNegro {

    private static final String RUTA_NEGRO = "res/ficheros/negro/ultimoNegro.txt";
    private static final String RUTA_RANK = "res/ficheros/negro/rankNegros.txt";
    private static final Character CHAR_DIVISOR = ' ';

    public static void vaciarNegro() {
        IOFicheros.escribirFichero(RUTA_NEGRO, "");
    }

    public static void escribirNegro(Negro negro) {
        String[] arr = new String[] {negro.getIdUser(), Long.toString(negro.getMsInicio())};
        String texto = arr[0] + CHAR_DIVISOR + arr[1];
        IOFicheros.escribirFichero(RUTA_NEGRO, texto);
    }

    public static Negro leerNegro() {
        if(!new File(RUTA_NEGRO).exists()) {
            System.out.println("El fichero ultimoNegro.txt no existe");
            return null;
        }
        String strFicheroNegro = IOFicheros.leerFichero(RUTA_NEGRO);

        if(strFicheroNegro.length() == 0) {
            System.out.println("El fichero ultimoNegro.txt esta vacío");
            return null;
        }

        String[] arrFicheroNegro = strFicheroNegro.split(CHAR_DIVISOR.toString());
        return new Negro(arrFicheroNegro[0], Long.parseLong(arrFicheroNegro[1]));

    }

    public static void escribirRank(Negro negro) {
        ArrayList<Negro> listaNegros = leerRank();
        boolean estaEnLaLista = false;

        for(Negro negroLista : listaNegros) {
            if(negroLista.getIdUser().equals(negro.getIdUser())) {
                negroLista.setTiempo(negroLista.getTiempo() + System.currentTimeMillis() - negro.getMsInicio());
                estaEnLaLista = true;
            }
        }

        if(!estaEnLaLista) {
            negro.setTiempo(negro.getTiempo() + System.currentTimeMillis() - negro.getMsInicio());
            listaNegros.add(negro);
        }

        ListaNegrosSort.sort(listaNegros);

        String salida = "";
        for(Negro negroLista : listaNegros) {
            salida += negroLista.getIdUser() +
                    CHAR_DIVISOR +
                    negroLista.getMsInicio() +
                    CHAR_DIVISOR +
                    negroLista.getTiempo() +
                    CHAR_DIVISOR;
        }
        IOFicheros.escribirFichero(RUTA_RANK, salida);

    }

    public static ArrayList<Negro> leerRank() {
        if(!new File(RUTA_RANK).exists()) {
            System.out.println("El fichero rankNegros.txt no existe");
            return new ArrayList<Negro>();
        }

        String strFicheroRank = IOFicheros.leerFichero(RUTA_RANK);

        if(strFicheroRank.length() == 0) {
            System.out.println("El fichero rankNegros.txt esta vacío");
            return new ArrayList<Negro>();
        }

        ArrayList<Negro> listaNegros = new ArrayList<Negro>();
        String[] arrFichero = strFicheroRank.split(CHAR_DIVISOR.toString());
        for(int i = 0; i < arrFichero.length; i += 3) {
            listaNegros.add(new Negro(arrFichero[i], Long.parseLong(arrFichero[i + 1]), Long.parseLong(arrFichero[i + 2])));
        }

        return listaNegros;
    }

    public static String getRutaNegro() {
        return RUTA_NEGRO;
    }

    public static String getRutaRank() {
        return RUTA_RANK;
    }

    public static Character getCharDivisor() {
        return CHAR_DIVISOR;
    }
}
