/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.yesOrNo;
import controlador.Controlador;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.err;
import static java.lang.System.out;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Alex Sánchez
 */
public class Vista {
    
    Controlador c = new Controlador();
    
    /**
     * Ejecuta el menu principal del programa.
     * Las opciones son los métodos Arranque(),
     * Archivos(), ActPeliculas(), ActDirectores(), 
     * ActActores(), ActListados(), salida()
     * @param menu Opciones visibles en el menu
     * @throws Exception 
     */
    public void runMenu(String menu) throws Exception{
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4", "5", "6", "q"};
        do{
            option = readString(menu, availableOptions);
            switch(option){
                case "1": Arranque() ; break;
                case "2": Archivos(); break;
                case "3": ActPeliculas(); break;
                case "4": ActDirectores(); break;
                case "5": ActActores(); break;
                case "6": ActListados(); break;
                case "q":
                    salir = yesOrNo("¿Desea salir de la aplicación? Y/N");
                    if (salir == true)
                        salida();
                    break;
                default:
                    out.printf("\nOpción incorrecta");
                    break;
            }
        } while (!salir);
    }    

    /**
     * Llama a la función en controlador
     * que se encarga de cargar los datos
     * de los ficheros.
     */
    private void Arranque() throws EOFException, IOException, FileNotFoundException, ClassNotFoundException, ParseException {
        c.openFiles();
    }

    /**
     * Abre submenu de la opcion 2 del menu principal
     * Las opciones son los metodos ExpDirectores() y 
     * ExpPeliculas().
     * @throws Exception 
     */
    private void Archivos() throws Exception{
        String option;
        String menu = ("\n1) Exportar directores" + "\n2) Exportar películas" + "\n3) Volver atrás");
        String[] availableOptions = {"1", "2","3"};
        option = readString(menu, availableOptions);
        switch(option){
            case "1": ExpDirectores() ; break;
            case "2": ExpPeliculas(); break;
            case "3": break;
            default: break;
        }
    }

    /**
     * Abre submenu de la opcion 3 del menu principal
     * Las opciones son los metodos 
     * AltaPeliculas(), BajaPeliculas(), ModPeliculas().
     * @throws Exception
     */
    private void ActPeliculas() throws Exception{
        String option;
        String menu = ("\n1) Dar de alta una película"
                     + "\n2) Dar de baja una película"
                     + "\n3) Modificar una película"
                     + "\n4) Consultar una película"
                     + "\n5) Volver atrás");
        String[] availableOptions = {"1", "2", "3","4","5"};
        option = readString(menu, availableOptions);
        switch(option){
            case "1": AltaPeliculas(); break;
            case "2": BajaPeliculas(); break;
            case "3": ModPeliculas(); break;
            case "4": ConsultaPeliculas(); break;
            case "5": break;
            default: break;
        }
    }

    /**
     * Exporta las películas existentes en la
     * filmoteca en un archivo HTML.
     * @throws IOException 
     */
    private void ExpPeliculas() throws IOException {
        c.createHTML();
    }

    /**
     * Llama al controlador para insertar un objeto
     * película dentro de la filmoteca.
     */
    private void AltaPeliculas() {
        c.addPelicula();
    }

    /**
     * Llama al controlador para eliminar un objeto
     * película existente en la filmoteca.
     */
    private void BajaPeliculas() {
        Scanner sc = new Scanner(System.in);
        String tmp_string,tmp_string2;
        out.println("\nIntroduzca el título de la película a eliminar: ");
        tmp_string = sc.nextLine();
        c.removePelicula(tmp_string);
    }

    /**
     * Llama al controlador para hacer una modificación
     * de un objeto película existente en la filmoteca.
     */
    private void ModPeliculas() {
        Scanner sc = new Scanner(System.in);
        String tmp;
        out.println("Introduce el título de la película a modificar: ");
        tmp = sc.nextLine();
        c.modifyPelicula(tmp);
    }

    /**
     * Llama a la función en el controlador
     * Guarda los datos en los ficheros
     * actores.bin, peliculas.bin, directores.bin
     * Antes del salir del programa.
     */
    private void salida() throws IOException {
        c.saveFiles();
    }

    /**
     * Llama al controlador para imprimir las colecciones existentes
     * en la Filmoteca de Películas, actores y directores:
     * Películas la ordenará por Título alfabéticamente,
     * Directores la ordenará por Nacionalidad y año de nacimiento,
     * Actores la ordenará por Año de Debut, y nombre.
     */
    private void ActListados() {
        out.println("\n=========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================");
        out.println("                                                                                                   PELICULAS");
        out.println("=========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================");
        out.println(" TITULO                                                  DURACIÓN   AÑO     PAIS                     DIRECCIÓN                                GUIÓN                                                   MÚSICA                                  FOTOGRAFÍA                                 REPARTO                                                                                PRODUCTORA                                                                                                      GENERO                             SINOPSIS ");
        c.printHashMap("película");
        out.println("\n==================================================================================================================================================================================================================================================================");
        out.println("                                                           DIRECTORES");
        out.println("===================================================================================================================================================================================================================================================================");
        out.println(" NOMBRE Y APELLIDOS           NACIONALIDAD                FECHA DE NACIMIENTO                OCUPACIÓN                                                              PELÍCULAS");
        c.printHashMap("director");
        out.println("\n==================================================================================================================================================================================================================================================================");
        out.println("                                                           ACTORES");
        out.println("====================================================================================================================================================================================================================================================================");
        out.println(" NOMBRE Y APELLIDOS             NACIONALIDAD                   PELÍCULAS                                                                                                                                            DEBUT                 FECHA DE NACIMIENTO");
        c.printHashMap("actor");
    }

    /**
     * Abre submenu de la opcion 5 del menu principal
     * Las opciones son los metodos 
     * AltaActores(), BajaActores(), ModActores().
     * @throws Exception
     */
    private void ActActores() throws Exception{
        String option;
        String menu = ("\n1) Dar de alta un/a actor/actriz"
                     + "\n2) Dar de baja un/a actor/actriz"
                     + "\n3) Modificar un/a actor/actriz"
                     + "\n4) Mostrar películas actor/actriz"
                     + "\n5) Volver atrás");
        String[] availableOptions = {"1", "2", "3", "4" ,"5"};
        option = readString(menu, availableOptions);
        switch(option){
            case "1": AltaActor() ; break;
            case "2": BajaActor(); break;
            case "3": ModActor(); break;
            case "4": MostrarPeliculas(); break;
            case "5": break;
            default: break;
        }
    }

    /**
     * Abre submenu de la opcion 4 del menu principal
     * Las opciones son los metodos 
     * AltaDirectores(), BajaDirectores(), ModDirectores().
     * @throws Exception
     */
    private void ActDirectores() throws Exception{
        String option;
        String menu = ("\n1) Dar de alta un/a director/a"
                     + "\n2) Dar de baja un/a director/a"
                     + "\n3) Modificar un/a director/a"
                     + "\n4) Volver atrás");
        String[] availableOptions = {"1", "2", "3","4"};
        option = readString(menu, availableOptions);
        switch(option){
            case "1": AltaDirector() ; break;
            case "2": BajaDirector(); break;
            case "3": ModDirector(); break;
            case "4": break;
            default: break;
        }
    }

    private void ExpDirectores() throws IOException {
        c.exportDirector();
    }

    /**
     * Llama al controlador para crear un objeto Actor
     * y lo añadirá a la colección existente en Filmoteca.
     */
    private void AltaActor() {
        c.addActor();
    }

    /**
     * Llama al controlador para eliminar un objeto Actor
     * existente en la colección de la Filmoteca.
     */
    private void BajaActor() {
        Scanner sc = new Scanner(System.in);
        String tmp_string,tmp_string2;
        out.println("\nIntroduzca el nombre del actor a eliminar: ");
        tmp_string = sc.nextLine();
        c.removeActor(tmp_string);
    }

    /** 
     * Llama al controlador para modificar un objeto Actor
     * existente en la colección de la Filmoteca.
     */
    private void ModActor() {
        Scanner sc = new Scanner(System.in);
        String tmp;
        out.println("Introduce el nombre del Actor/actriz a modificar: ");
        tmp = sc.nextLine();
        c.modifyActor(tmp);
    }

    /**
     * Llama al controlador para crear un objeto Director
     * y añadirlo a la colección de la Filmoteca.
     */
    private void AltaDirector() {
        c.addDirector();
    }

    /** 
     * Llama al controlador para eliminar un objeto Director
     * existente en la colección de la Filmoteca.
     */
    private void BajaDirector() {
        Scanner sc = new Scanner(System.in);
        String tmp_string,tmp_string2;
        out.println("\nIntroduzca el nombre del director a eliminar: ");
        tmp_string = sc.nextLine();
        c.removeDirector(tmp_string);
    }

    /**
     * Llama al controlador para modificar un objeto Director 
     * existente en la colección de la Filmoteca.
     */
    private void ModDirector() {
        Scanner sc = new Scanner(System.in);
        String tmp;
        out.println("Introduce el nombre del Director/a a modificar: ");
        tmp = sc.nextLine();
        c.modifyDirector(tmp);
    }

    private void ConsultaPeliculas() {
        out.printf ("Introduce el Título de la película a consultar:\n");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        if (c.searchByTitle("película", titulo) == true){
            out.println("TITULO  DURACIÓN   AÑO    PAIS   DIRECCIÓN    GUIÓN   MÚSICA   FOTOGRAFÍA   REPARTO  PRODUCTORA  GENERO  SINOPSIS ");
            c.printElement("película",titulo);
        } else err.println("Elemento no encontrado");
    }

    private void MostrarPeliculas(){
        Scanner sc = new Scanner(System.in);
        out.println("Introduce el nombre del actor/actriz: ");
        String nombre = sc.nextLine();
        c.showFilms(nombre);
    }
}