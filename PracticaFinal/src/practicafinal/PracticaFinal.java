/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafinal;

import java.io.EOFException;
import vista.Vista;

/**
 *
 * @author Alex Sánchez
 */
public class PracticaFinal {

    /**
     * @param args the command line arguments
     * @throws java.io.EOFException
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws EOFException, Exception {
        Vista v = new Vista();
        v.runMenu("\n1.- Arranque "
               +  "\n2.- Archivos"
               +  "\n3.- Peliculas"
               +  "\n4.- Directores"
               +  "\n5.- Actores"
               +  "\n6.- Listados"
               +  "\nq.- Salir");
    }
    
}
