package nishabot.operaciones.utilidades;

import java.util.ArrayList;

import nishabot.entidades.Negro;

public class ListaNegrosSort {

    public static void sort(ArrayList<Negro> listaNegros) {
        // Select sort
        int mayor = 0;
        int longitud = listaNegros.size();
        Negro temp;
        for(int i = 0; i < longitud - 1; i++) {
            mayor = i;
            for(int j = i + 1; j < longitud; j++) {
                if(listaNegros.get(j).getTiempo() > listaNegros.get(mayor).getTiempo()) {
                    mayor = j;
                }
            }
            if(mayor != i) {
                temp = listaNegros.get(i);
                listaNegros.set(i, listaNegros.get(mayor));
                listaNegros.set(mayor, temp);
            }
        }
    }
}
