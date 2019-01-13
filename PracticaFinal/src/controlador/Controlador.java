/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import modelo.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.err;
import static java.lang.System.out;
import java.util.ArrayList;
import java.io.StreamCorruptedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import modelo.Actor;
import modelo.Director;
import modelo.Película;


/**
 *
 * @author Alex Sánchez
 */
public class Controlador {
    
    Modelo m = new Modelo();
    File fileBin1 = null; File fileBin2 = null; File fileBin3 = null;
    boolean vacio1,vacio2,vacio3;
    File fileTxt1 = null; File fileTxt2 = null; File fileTxt3 = null;
    // Canales de entrada y salida de datos de cada archivo BINARIO
    FileInputStream fis1 = null; ObjectInputStream entrada1 = null; FileOutputStream fos1 = null; ObjectOutputStream salida1 = null;
    FileInputStream fis2 = null; ObjectInputStream entrada2 = null; FileOutputStream fos2 = null; ObjectOutputStream salida2 = null;
    FileInputStream fis3 = null; ObjectInputStream entrada3 = null; FileOutputStream fos3 = null; ObjectOutputStream salida3 = null;
    // Canales de entrada y salida de datos para los archivos de TEXTO
    FileReader fr1 = null; FileWriter fw1 = null; BufferedReader entrada4 = null; BufferedWriter salida4 = null;
    FileReader fr2 = null; FileWriter fw2 = null; BufferedReader entrada5 = null; BufferedWriter salida5 = null;
    FileReader fr3 = null; FileWriter fw3 = null; BufferedReader entrada6 = null; BufferedWriter salida6 = null;
    // Canales de salida para HTML
    File html1 = null;
    FileWriter fw4 = null; BufferedWriter salida7 = null;
    // Canales de salida para el archivo de Columnas
    FileWriter fw5 = null; BufferedWriter salida8 = null;

    /**
     * Intenta abrir los canales de Lectura/escritura de los diferentes ficheros,
     * en caso de no conseguir un archivo BINARIO LO CREA
     * @throws java.io.EOFException
     * @throws FileNotFoundException
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.text.ParseException 
     */
    public void openFiles() throws EOFException, FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        /* Cargamos los archivos en el modelo. Para ello, intentamos primero
        abrir ALGUNO de los archivos binarios. Si no se consigue, intentamos
        abrir ALGUNO de los archivos de texto.
         */
        // Abrimos el directorio. Si no existe, lo crea
        File directorio=new File(m.THE_FOLDER_PATH_AS_STRING); 
        directorio.mkdir(); 
        try{
            fos3 = new FileOutputStream(m.THE_PELICULAS_BIN_FILE_PATH_AS_STRING,true);
            salida3 = new ObjectOutputStream(fos3);
            fis3 = new FileInputStream(m.THE_PELICULAS_BIN_FILE_PATH_AS_STRING);
            entrada3 = new ObjectInputStream(fis3);
            fileBin3 = new File(m.THE_PELICULAS_BIN_FILE_PATH_AS_STRING);
            if (fileBin3.length() < 16){
                out.println("películas.bin está vacío. Creo películas.bin y abro películas.txt");
                vacio3 = true;
            }
            else{
                readBin("película",entrada3,fis3);
                System.out.println("peliculas.bin abierta correctamente");
                vacio3 = false;
            }
        } catch(IOException | ClassNotFoundException ef){
            err.println("Error: 'peliculas.bin' no ha podido abrirse.");
            vacio3 = true;
        }
        if (vacio3 == true){
            try{
                fw3 = new FileWriter(m.THE_PELICULAS_TXT_FILE_PATH_AS_STRING,true);
                fr3 = new FileReader(m.THE_PELICULAS_TXT_FILE_PATH_AS_STRING);
                entrada6 = new BufferedReader(fr3);
                salida6 = new BufferedWriter(fw3);
                readTxt("película",entrada6);
                System.out.println("peliculas.txt abierta correctamente");
            } catch(IOException | ClassNotFoundException ef2){
                err.println("Error: 'peliculas.txt' no ha podido abrirse.");
            }
        }
        try{
            // Intentamos abrir los binarios. Si no existen, los crean
            fos1 = new FileOutputStream(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING,true);
            salida1 = new ObjectOutputStream(fos1);
            fis1 = new FileInputStream(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING);
            entrada1 = new ObjectInputStream(fis1);
            fileBin1 = new File(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING);
            if (fileBin1.length() < 16){
                vacio1 = true;
                out.println("actores.bin está vacío. Lo creo y abro actores.txt");
            }
            else{
                readBin("actor",entrada1,fis1);
                System.out.println("actores.bin abierta correctamente");
                vacio1 = false;
            }
        } catch(IOException | ClassNotFoundException ef){
            err.println("Error: 'actores.bin' no ha podido abrirse.");
            vacio1 = true;
        }
        if (vacio1 == true){
            try{
                fw1 = new FileWriter(m.THE_ACTOR_TXT_FILE_PATH_AS_STRING,true);                
                fr1 = new FileReader(m.THE_ACTOR_TXT_FILE_PATH_AS_STRING);
                entrada4 = new BufferedReader(fr1);
                salida4 = new BufferedWriter(fw1);
                readTxt("actor",entrada4);
                
                System.out.println("actores.txt abierta correctamente");
            } catch(IOException | ClassNotFoundException ef2){
                err.println("Error: 'actores.txt' no ha podido abrirse.");
            }
        }
        try{
            fos2 = new FileOutputStream(m.THE_DIRECTOR_BIN_FILE_PATH_AS_STRING,true);
            salida2 = new ObjectOutputStream(fos2);
            fis2 = new FileInputStream(m.THE_DIRECTOR_BIN_FILE_PATH_AS_STRING);
            entrada2 = new ObjectInputStream(fis2);
            fileBin2 = new File(m.THE_DIRECTOR_BIN_FILE_PATH_AS_STRING);
            if (fileBin2.length() < 16){
                out.println("directores.bin está vacío. Creo directores.bin y abro directores.txt");
                vacio2 = true;
            }
            else{
                readBin("director",entrada2,fis2);
                System.out.println("directores.bin abierta correctamente");  
                vacio2 = false;
            }
        } catch(IOException | ClassNotFoundException ef){
            err.println("Error: 'directores.bin' no ha podido abrirse.");
            vacio2 = true;
        }
        if (vacio2 == true){
            try{
                fw2 = new FileWriter(m.THE_DIRECTOR_TXT_FILE_PATH_AS_STRING,true);
                fr2 = new FileReader(m.THE_DIRECTOR_TXT_FILE_PATH_AS_STRING);
                entrada5 = new BufferedReader(fr2);
                salida5 = new BufferedWriter(fw2);
                readTxt("director",entrada5);
                System.out.println("directores.txt abierta correctamente");
            } catch(IOException | ClassNotFoundException ef2){
                err.println("Error: 'directores.txt' no ha podido abrirse.");
            }
        }
    }

    /**
     * Guarda los archivos y cierra el programa
     * @throws IOException 
     */
    public void saveFiles() throws IOException {
        try{
            this.salida1.close(); this.fos1.close();
            File f = new File(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING);
            f.delete();
            try {
              f.createNewFile();
            } catch (IOException ioe) {
            }
            this.fos1 = new FileOutputStream(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING); this.salida1 = new ObjectOutputStream(fos1);
            this.salida1 = new ObjectOutputStream(this.fos1);
            HashMap<String,Actor> a = new HashMap<>();
            a = m.getActorSet();
            vacio1 = false;
            try{
                a.forEach((k,v)->{
                    try {
                        salida1.writeObject(v);
                        salida1.flush();
                    } catch (IOException ex) {
                        err.println("Error al guardar actores.bin.");
                        throw new IllegalArgumentException("Error");
                    }
                });
            }catch(IllegalArgumentException e){ vacio1 = true;}
            if (vacio1 == true){
                m.getActorSet().entrySet().forEach((Map.Entry<String, Actor> entry) -> {
                    try {
                        String[] elements = entry.getValue().toString().split("\t");
                        for (int i = 0; i < elements.length; i++){
                            salida4.write(elements[i]);
                            salida4.flush();
                        }
                        salida4.write('#');
                        salida4.flush();
                    } catch (IOException ex) {
                        err.println("Error al guardar actores.txt.");  
                    }
                });  
            }           
            this.salida1.close(); this.fos2.close();
            File f1 = new File(m.THE_DIRECTOR_BIN_FILE_PATH_AS_STRING);
            f1.delete();
            try {
              f1.createNewFile();
            } catch (IOException ioe) {
            }
            this.fos2 = new FileOutputStream(m.THE_DIRECTOR_BIN_FILE_PATH_AS_STRING);
            this.salida2 = new ObjectOutputStream(this.fos2);
            HashMap<String,Director> d = new HashMap<>();
            d = m.getDirectorSet();
            vacio2 = false;
            try{
                d.forEach((k,v)->{
                    try {
                        salida2.writeObject(v);
                        salida2.flush();
                    } catch (IOException ex) {
                        err.println("Error al guardar directores.bin.");
                        throw new IllegalArgumentException("Error");
                    }
                });
            }catch(IllegalArgumentException e){ vacio2 = true;}
            if (vacio2 == true){
                m.getDirectorSet().entrySet().forEach((Map.Entry<String, Director> entry) -> {
                    try {
                        String[] elements = entry.getValue().toString().split("\t");
                        for (int i = 0; i < elements.length; i++){
                            salida5.write(elements[i]);
                            salida5.flush();
                        }
                        salida5.write('#');
                        salida5.flush();
                    } catch (IOException ex) {
                        err.println("Error al guardar directores.txt.");
                    }
                });          
            }
            this.salida3.close();
            this.fos3.close();
            File f3 = new File(m.THE_ACTOR_BIN_FILE_PATH_AS_STRING);
            f3.delete();
            try {
              f3.createNewFile();
            } catch (IOException ioe) {
            }
            this.fos3 = new FileOutputStream(m.THE_PELICULAS_BIN_FILE_PATH_AS_STRING);
            this.salida3 = new ObjectOutputStream(this.fos3);
            HashMap<String,Película> p = new HashMap<>();
            p = m.getPeliculaSet();
            vacio3 = false;
            try{
                p.forEach((k,v)->{
                    try {
                        salida3.writeObject(v);
                    } catch (IOException ex) {
                        err.println("Error al guardar peliculas.bin.");
                        throw new IllegalArgumentException("Error");
                    }
                });
            }catch(IllegalArgumentException e){ vacio3 = true;}
            if (vacio3 == true){
                m.getPeliculaSet().entrySet().forEach((Map.Entry<String, Película> entry) -> {
                    try {
                        String[] elements = entry.getValue().toString().split("\t");
                        for (int i = 0; i < elements.length; i++){
                            salida6.write(elements[i]);
                            salida6.flush();
                        }
                        salida6.write('#');
                        salida6.flush();
                    } catch (IOException ex) {
                        err.println("Error al guardar película.txt."); 
                    }
                });  
                   
            }
            if (fr1!=null) fr1.close(); if (fr2!=null) fr2.close(); if (fr3!=null) fr3.close();
            if (fw1!=null) fw1.close(); if (fw2!=null) fw2.close(); if (fw3!=null) fw3.close();
            if (entrada1!=null) entrada1.close(); if (entrada2!=null) entrada2.close(); if (entrada3!=null) entrada3.close();
            if (entrada4!=null) entrada4.close(); if (entrada5!=null) entrada5.close(); if (entrada6!=null) entrada6.close();
            if (salida1!=null) salida1.close(); if (salida2!=null) salida2.close(); if (salida3!=null) salida3.close();
            if (salida4!=null) salida4.close(); if (salida5!=null) salida5.close(); if (salida6!=null) salida6.close();
            if (fis1!=null) fis1.close(); if (fis2!=null) fis2.close(); if (fis3!=null) fis3.close();
            if (fos1!=null) fos1.close(); if (fos2!=null) fos2.close(); if (fos3!=null) fos3.close();
            if(salida7!=null) salida7.close(); if (fw4!=null) fw4.close();
            System.out.println("Archivos cerrados con éxito!");
        }catch(IOException e){
            System.out.println("Error: no se han cerrado correctamente.");
        }
    }

    /**
     * Crea el código HTML 
     * @throws IOException 
     */
    public void createHTML() throws IOException {
        try{
            if (html1==null) html1 = new File(m.THE_PELICULAS_HTML_FILE_PATH_AS_STRING);
            if (html1.exists()){ 
                if(fw4==null) fw4 = new FileWriter(html1);
                if(salida7==null) salida7 = new BufferedWriter(fw4);
                fw4.write("");
                addNewHTMLHeadCode();
            }
            else{
                if(fw4==null) fw4 = new FileWriter(html1);
                if(salida7==null) salida7 = new BufferedWriter(fw4);
                addNewHTMLHeadCode();
            }
            addHTML();
            addNewHTMLEndCode();
        } catch(FileNotFoundException e){
            out.println("No se pudo abrir el archivo peliculas.html");
        }  
        
    }
    
    /**
     * Añade la cabecera del código HTML
     * @throws IOException 
     */
    private void addNewHTMLHeadCode() throws IOException {
        salida7.write(m.HTML_HEAD);
    }
    
    /**
     * Añade la finalización del código HTML
     * @throws IOException 
     */
    private void addNewHTMLEndCode() throws IOException{
        salida7.write(m.HTML_END);
    }

    /**
     * Añade una colección de películas al HTML
     * @throws IOException 
     */
    private void addHTML() throws IOException {
        HashMap<String,Película> cPeliculas = m.getPeliculaSet();
        try{
            cPeliculas.forEach((k,v) -> {
                String[] elements = m.getCollection("película",k);
                    if (!"".equals(elements[0])){
                        try{
                        salida7.write("  <tr>\n");
                            for (String element : elements) {
                                salida7.write("    <td>" + element + "</td>\n");
                            }
                        salida7.write("  </tr>\n");
                        elements = m.getCollection("película",k);  
                        } catch (IOException e){
                            err.println("Fallo de película");
                        }
                    }
            });
            out.println("HTML exportado correctamente.");
        } catch(Exception e){
            err.println("Error. No se pudo exportar a HTML");
        }
    }
    
    /**
     * Añade película a la filmoteca
     */
    public void addPelicula() {  
        Scanner sc = new Scanner(System.in);
        
        String tmp_string, tmp_string2;
        float tmp_float = 0;
        int tmp_int = 0;        
        String[] values = new String[9];
        ArrayList<String> direccion = new ArrayList<>();  
        ArrayList<String> reparto = new ArrayList<>();
        boolean introducido = false;
        boolean salir = false;
        
        out.println("\n TITULO: ");
        do{
            values[0] = sc.nextLine();
            if (m.existsInFilmoteca("película", values[0]) == true){
                err.println("Ya existe en filmoteca. Introduce otro título.");
                salir = false;
            } else salir = true;
        }while (salir!=true);
        out.println("\n DURACION: ");
            boolean isValid = true;
            Calendar cal= Calendar.getInstance();
            do{
                try{
                    tmp_float = Float.parseFloat(tmp_string = sc.nextLine()); 
                    isValid = true;
                } catch(NumberFormatException e){
                    isValid = false;
                    err.println("No es una cifra. Inténtalo de nuevo");
                }
            } while(isValid!= true);
        out.println("\n AÑO: ");
            do{
                try{
                    do{
                        tmp_int = Integer.parseInt(tmp_string = sc.nextLine());
                        isValid = true;
                    } while(tmp_int < 1900 || tmp_int > cal.get(Calendar.YEAR));
                } catch(NumberFormatException e){
                    isValid = false;
                    err.println("No es una cifra. Inténtalo de nuevo");
                }
            } while(isValid!= true);
        out.println("\n PAIS: ");
        values[1] = sc.nextLine();
        out.println("\n DIRECCIÓN: ");
        
        do{
            out.println ("\nIntroduce el nombre del director/a: ");
            tmp_string = sc.nextLine();
            if (this.searchByTitle("director",tmp_string) == true){
                introducido = direccion.add(tmp_string);
                m.addFilmTo("director", tmp_string, values[0]);
                if( introducido == false) err.println("Error de introducción del director/a");
            }
            else{
                out.println("\nEl director/a no existe en la base de datos. ¿Desea crearlo? S/N.");
                tmp_string2 = sc.nextLine().toLowerCase();
                if (tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes")){
                    ArrayList<String> cPeliculas = new ArrayList<>();
                    cPeliculas.add(values[0]);
                    m.setDirector(tmp_string, null, null, null, cPeliculas);
                    introducido = direccion.add(tmp_string);
                    if( introducido == false) err.println("Error de introducción del director/a");
                }
            }
            out.println("\n¿Desea introducir más directores/as?: S/N");
            tmp_string2 = sc.nextLine().toLowerCase();
            salir = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
        } while(salir != true);
        out.println("\n MÚSICA: ");
        values[2] = sc.nextLine();
        out.println("\n GUIÓN: ");
        values[3] = sc.nextLine();
        out.println("\n FOTOGRAFÍA: ");
        values[4] = sc.nextLine();
        out.println("\n REPARTO: ");
        // ESTA FUNCION BUSCA SI EXISTE EL ACTOR EN LA FILMOTECA, Y SI ESTÄ LO AÑADE.
        // SI NO ESTA, SE AÑADIRÁ SOLO SUS NOMBRE, DEJANDO EL RESTO DE CAMPOS VACIOS.
        do{
            out.println ("\nIntroduce el nombre del actor/actriz: ");
            tmp_string = sc.nextLine();
            if (this.searchByTitle("actor",tmp_string) == true){
                m.addFilmTo("actor", tmp_string, values[0]);
                introducido = reparto.add(tmp_string);
                if( introducido == false) err.println("Error de introducción de actor/actriz");
            }
            else{
                out.println("\nEl actor/actriz no existe en la base de datos. ¿Desea crearlo? S/N.");
                tmp_string2 = sc.nextLine().toLowerCase();
                if (tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes")){
                    ArrayList<String> cPeliculas = new ArrayList<>();
                    cPeliculas.add(values[0]);
                    m.setActor(tmp_string, null, cPeliculas, 0, null);
                    introducido = reparto.add(tmp_string);
                    if( introducido == false) err.println("Error de introducción de actor/actriz");
                }
            }
            out.println("\n¿Desea introducir más actores/actrices?: S/N");
            tmp_string2 = sc.nextLine().toLowerCase();
            salir = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
        } while(salir != true);
        
        out.println("\n PRODUCTORA: ");
        values[5] = sc.nextLine();
        out.println("\n GENERO: ");
        values[6] = sc.nextLine();
        out.println("\n SINOPSIS: ");
        values[7] = sc.nextLine();
        m.setPelicula(values[0], tmp_float, tmp_int, values[1], direccion, values[2], values[3], values[4], reparto, values[5], values[6], values[7]);
    }

    /**
     * Añade un actor a la filmoteca
     */
    public void addActor() {
        Scanner sc = new Scanner(System.in);
        boolean salida;
        String tmp_1;
        String tmp_2;
        String tmp_string,tmp_string2;
        ArrayList<String> peliculas = new ArrayList<>();
        int debut = 0;
        String[] date_tmp = new String[3];
        int date0 = 0, date1 = 0, date2 = 0;
        
        out.println("Introduce los siguientes datos: ");
        out.println("\n NOMBRE: ");
        tmp_1 = sc.nextLine();
        out.println("\n NACIONALIDAD: ");
        tmp_2 = sc.nextLine();
        out.println("\n PELÍCULAS: ");
        do{
            out.println ("\nIntroduce el título de la película: ");
            tmp_string = sc.nextLine();
                if (m.existsInFilmoteca("película", tmp_string) == true){
                    Película p = m.getPelicula(tmp_string);
                    peliculas.add(tmp_string);
                }
                else peliculas.add(tmp_string);
            out.println("\n¿Desea introducir más títulos?: S/N");
            tmp_string2 = sc.nextLine().toLowerCase();
            salida = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
        } while(salida != true);
        
        out.println("\n DEBUT: ");
            Calendar cal= Calendar.getInstance();
            boolean isValid = true;
            do{
                try{
                    do{
                        debut = Integer.parseInt(tmp_string = sc.nextLine());
                        isValid = true;
                    } while(debut < 1900 || debut > cal.get(Calendar.YEAR));
                } catch(NumberFormatException e){
                    isValid = false;
                    err.println("No es una cifra. Inténtalo de nuevo");
                }
            } while(isValid!= true);        
        out.println("\n FECHA DE NACIMIENTO (dd/mm/yyyy): ");
        Date fNacimiento = null;
        isValid = true;
        do{
            date_tmp = sc.nextLine().split("/");
            if (date_tmp[0] == null || date_tmp[1] == null ||date_tmp[2] == null) err.println("Formato no válido");
            else{
            try{
                date0 = Integer.parseInt(date_tmp[0]);
                if (date0 < 0 || date0 > 31){
                    err.println("Error: fecha no válida.");    
                    isValid = false;
                }
                date1 = Integer.parseInt(date_tmp[1]);
                if (date1 < 1 || date1 > 12){
                    err.println("Error: fecha no válida.");    
                    isValid = false;                    
                }
                date2 = Integer.parseInt(date_tmp[2]);  
                if (date2 < 1850 || date2 > cal.get(Calendar.YEAR)){             
                    err.println("Error: fecha no válida.");    
                    isValid = false;  
                }
                fNacimiento = new Date(date2,date1,date0);
            } catch(NumberFormatException e){
                err.println("Error: fecha no válida.");
                isValid = false;
            }
            }
        } while(isValid != true);
        m.setActor(tmp_1, tmp_2, peliculas, debut, fNacimiento);
    }

    /**
     * Añade un directo de la filmoteca
     */
    public void addDirector() {
        String nombre;
        boolean salida = false;
        String nacionalidad;
        String tmp_string, tmp_string2;
        String[] date_tmp = new String[3];
        int date0 = 0,date1 = 0,date2 = 0;
        Date fNacimiento = null;
        String ocupacion;
        ArrayList<String> peliculas = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        out.println("Introduce los siguientes datos: ");
        out.println("\n NOMBRE: ");  
        nombre = sc.nextLine();
        out.println("\n NACIONALIDAD: ");
        nacionalidad = sc.nextLine();
        out.println("\n FECHA DE NACIMIENTO (dd/mm/yyyy): ");
        boolean isValid;
        Calendar cal= Calendar.getInstance();
        do{
            isValid = true;
            date_tmp = sc.nextLine().split("/");
            if (date_tmp[0] == null || date_tmp[1] == null ||date_tmp[2] == null) err.println("Formato de fecha no válido");
            else{
            try{
                date0 = Integer.parseInt(date_tmp[0]);
                if (date0 < 0 || date0 > 31){
                    err.println("Error: fecha no válida.");    
                    isValid = false;
                }
                date1 = Integer.parseInt(date_tmp[1]);
                if (date1 < 1 || date1 > 12){
                    err.println("Error: fecha no válida.");    
                    isValid = false;
                }
                date2 = Integer.parseInt(date_tmp[2]);  
                if (date2 < 1850 || date2 > cal.get(Calendar.YEAR)){             
                    err.println("Error: fecha no válida.");    
                    isValid = false;  
                }
                if (isValid != false) fNacimiento = new Date(date2,date1,date0);
            } catch(NumberFormatException e){
                err.println("Error: fecha no válida.");
                isValid = false;
            }
            }
        } while(isValid != true);
        out.println("\n OCUPACIÓN: ");
        ocupacion = sc.nextLine();
        out.println("\n PELÍCULAS: ");
        do{
            out.println ("\nIntroduce el título de la película: ");
            tmp_string = sc.nextLine();
                if (m.existsInFilmoteca("película", tmp_string) == true){
                    Película p = m.getPelicula(tmp_string);
                    peliculas.add(tmp_string);
                }
                else peliculas.add(tmp_string);
            out.println("\n¿Desea introducir más títulos?: S/N");
            tmp_string2 = sc.nextLine().toLowerCase();
            salida = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
        } while(salida != true);
        m.setDirector(nombre, nacionalidad, fNacimiento, ocupacion, peliculas);
    }

    /**
     * Elimina una película de la filmoteca
     * @param pelicula 
     */
    public void removePelicula(String pelicula) {
        m.remove("película",pelicula);
        out.println("Eliminado correctamente.");

    }

    /**
     * Modifica una película de la filmoteca
     * @param name 
     */
    public void modifyPelicula(String name) {
        if (m.existsInFilmoteca("película", name)== true){
            Scanner sc = new Scanner(System.in);
            Película p = m.getPelicula(name);
            String modificar;
            String tmp_string, tmp_string2;
            float tmp_float = p.duracion;
            Calendar cal= Calendar.getInstance();
            int tmp_int = p.ano;       
            String[] values = new String[9];
            ArrayList<String> direccion = new ArrayList<>();  
            ArrayList<String> reparto = new ArrayList<>();
            boolean introducido = false;
            boolean salir = false;
            boolean isValid = true;

            out.println("¿Desea modificar el título? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n TITULO: ");
                do{
                    values[0] = sc.nextLine();
                    if (m.existsInFilmoteca("película", values[0]) == true){
                        err.println("Ya existe en filmoteca. Introduce otro título.");
                        salir = false;
                    } else salir = true;
                }while (salir!=true);             
            }
            else values[0] = p.titulo;
            out.println("¿Desea modificar la duración? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n DURACION: ");
                do{
                    try{
                        tmp_float = Float.parseFloat(tmp_string = sc.nextLine()); 
                        isValid = true;
                    } catch(NumberFormatException e){
                        isValid = false;
                        err.println("No es una cifra. Inténtalo de nuevo");
                    }
                } while(isValid!= true);
            }
            out.println("¿Desea modificar el año? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n AÑO: ");
                do{
                    try{
                        do{
                            tmp_int = Integer.parseInt(tmp_string = sc.nextLine());
                            isValid = true;
                        } while(tmp_int < 1900 || tmp_int > cal.get(Calendar.YEAR));
                    } catch(NumberFormatException e){
                        isValid = false;
                        err.println("No es una cifra. Inténtalo de nuevo");
                    }
                } while(isValid!= true);
            }
            out.println("¿Desea modificar el pais? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){           
                out.println("\n PAIS: ");
                values[1] = sc.nextLine();
            } else values[1] = p.pais;
            out.println("¿Desea modificar los/las directores/as? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n DIRECCIÓN: ");
                do{
                    out.println ("\nIntroduce el nombre del director/a: ");
                    tmp_string = sc.nextLine();
                    if (this.searchByTitle("director",tmp_string) == true){
                        introducido = direccion.add(tmp_string);
                        if( introducido == false) err.println("Error de introducción del director/a");
                    }
                    else{
                        out.println("\nEl director/a no existe en la base de datos. ¿Desea crearlo? S/N.");
                        tmp_string2 = sc.nextLine().toLowerCase();
                        if (tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes")){
                            m.setDirector(tmp_string, null, null, null, null);
                            introducido = direccion.add(tmp_string);
                            if( introducido == false) err.println("Error de introducción del director/a");
                        }
                    }
                    out.println("\n¿Desea introducir más directores/as?: S/N");
                    tmp_string2 = sc.nextLine().toLowerCase();
                    salir = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
                } while(salir != true);
            } else direccion = p.direccion;
            
            out.println("¿Desea modificar la musica? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n MÚSICA: ");
                values[2] = sc.nextLine();
            } else values[2] = p.musica;
            
            out.println("¿Desea modificar el guion? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n GUIÓN: ");
                values[3] = sc.nextLine();
            } else values[3] = p.musica;

            out.println("¿Desea modificar la fotografía? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n FOTOGRAFÍA: ");
                values[4] = sc.nextLine();
            } else values[4] = p.fotografia;
            
            out.println("¿Desea modificar el reparto? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n REPARTO: ");
                do{
                    out.println ("\nIntroduce el nombre del actor/actriz: ");
                    tmp_string = sc.nextLine();
                    if (this.searchByTitle("actor",tmp_string) == true){
                        introducido = reparto.add(tmp_string);
                        if( introducido == false) err.println("Error de introducción de actor/actriz");
                    }
                    else{
                        out.println("\nEl actor/actriz no existe en la base de datos. ¿Desea crearlo? S/N.");
                        tmp_string2 = sc.nextLine().toLowerCase();
                        if (tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes")){
                            m.setActor(tmp_string, null, null, 0, null);
                            introducido = reparto.add(tmp_string);
                            if( introducido == false) err.println("Error de introducción de actor/actriz");
                        }
                    }
                    out.println("\n¿Desea introducir más actores/actrices?: S/N");
                    tmp_string2 = sc.nextLine().toLowerCase();
                    salir = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
                } while(salir != true);
            } else reparto = p.reparto;
            
            out.println("¿Desea modificar la productora? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n PRODUCTORA: ");
                values[5] = sc.nextLine();
            } else values[5] = p.productora;
            
            out.println("¿Desea modificar el genero? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n GENERO: ");
                values[6] = sc.nextLine();
            } else values[6] = p.genero;
            
            out.println("¿Desea modificar la sinopsis? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n SINOPSIS: ");
                values[7] = sc.nextLine();
            } else values[7] = p.sinopsis;
            m.remove("película", name);
            m.setPelicula(values[0], tmp_float, tmp_int, values[1], direccion, values[2], values[3], values[4], reparto, values[5], values[6], values[7]);            
        }
        else err.println("Elemento no encontrado.");
    }

    /**
     * Elimina un actor de la filmoteca
     * @param actor 
     */
    public void removeActor(String actor) {
        m.remove("actor",actor);
        out.println("Eliminado correctamente.");

    }

    /**
     * Modifica un actor de la filmoteca
     * @param name 
     */
    public void modifyActor(String name) {
        if (m.existsInFilmoteca("actor", name)== true){
            Scanner sc = new Scanner(System.in);
            Actor a = m.getActor(name);
            boolean salida;
            String tmp_1;
            String tmp_2;
            Calendar cal= Calendar.getInstance();
            boolean isValid = true;
            String tmp_string,tmp_string2;
            ArrayList<String> peliculas = new ArrayList<>();
            int debut = 0;
            Date fNacimiento = null;
            String[] date_tmp = new String[3];
            int date0 = 0, date1 = 0, date2 = 0;
            String modificar;
            
            out.println("¿Desea modificar el nombre? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n NOMBRE: ");
                tmp_1 = sc.nextLine();
            } else tmp_1 = a.nombre;

            out.println("¿Desea modificar la nacionalidad? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n NACIONALIDAD: ");
                tmp_2 = sc.nextLine();
            } else tmp_2 = a.nacionalidad;
            
            out.println("¿Desea modificar el título? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n PELÍCULAS: ");
                do{
                    out.println ("\nIntroduce el título de la película: ");
                    tmp_string = sc.nextLine();
                        if (m.existsInFilmoteca("película", tmp_string) == true){
                            Película p = m.getPelicula(tmp_string);
                            peliculas.add(tmp_string);
        }
                        else peliculas.add(tmp_string);
                    out.println("\n¿Desea introducir más títulos?: S/N");
                    tmp_string2 = sc.nextLine().toLowerCase();
                    salida = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
                } while(salida != true);
            } else peliculas = a.cPeliculas;
            
            out.println("¿Desea modificar el año de debut? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n DEBUT: ");
                    do{
                        try{
                            do{
                                debut = Integer.parseInt(tmp_string = sc.nextLine());
                                isValid = true;
                            } while(debut < 1900 || debut > cal.get(Calendar.YEAR));
                        } catch(NumberFormatException e){
                            isValid = false;
                            err.println("No es una cifra. Inténtalo de nuevo");
                        }
                    } while(isValid!= true);  
            } else debut = a.debut;
            
            out.println("¿Desea modificar la fecha de nacimiento? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n FECHA DE NACIMIENTO (dd/mm/yyyy): ");
                isValid = true;
                do{
                    date_tmp = sc.nextLine().split("/");
                    if (date_tmp[0] == null || date_tmp[1] == null ||date_tmp[2] == null) err.println("Formato no válido");
                    else{
                        try{
                            date0 = Integer.parseInt(date_tmp[0]);
                            if (date0 < 0 || date0 > 31){
                                err.println("Error: fecha no válida.");    
                                isValid = false;
                            }
                            date1 = Integer.parseInt(date_tmp[1]);
                            if (date1 < 1 || date1 > 12){
                                err.println("Error: fecha no válida.");    
                                isValid = false;                    
                            }
                            date2 = Integer.parseInt(date_tmp[2]);  
                            if (date2 < 1850 || date2 > cal.get(Calendar.YEAR)){             
                                err.println("Error: fecha no válida.");    
                                isValid = false;  
                            }
                            fNacimiento = new Date(date2,date1,date0);
                        } catch(NumberFormatException e){
                            err.println("Error: fecha no válida.");
                            isValid = false;
                        }
                    }
                } while(isValid != true);
            } else fNacimiento = a.fNacimiento;
            
            m.remove("actor", name);
            m.setActor(tmp_1, tmp_2, peliculas, debut, fNacimiento);        
        }
        else err.println("Elemento no encontrado.");
    }

    /**
     * Elimina un elemento de la filmoteca
     * @param director
     */
    public void removeDirector(String director) {
        m.remove("director",director);
        out.println("Eliminado correctamente.");

    }

    /**
     * Modifica un Director de la filmoteca
     * @param name 
     */
    public void modifyDirector(String name) {
        if (m.existsInFilmoteca("director", name)== true){
            String nombre;
            boolean salida = false;
            String nacionalidad;
            Director d = m.getDirector(name);
            String tmp_string, tmp_string2;
            String[] date_tmp = new String[3];
            int date0 = 0,date1 = 0,date2 = 0;
            Date fNacimiento = null;
            String ocupacion;
            ArrayList<String> peliculas = new ArrayList<>();
            String modificar;
            Scanner sc = new Scanner(System.in);
            
            out.println("¿Desea modificar el nombre? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){           
                out.println("\n NOMBRE: ");  
                nombre = sc.nextLine();
            } else nombre = d.nombre;
            out.println("¿Desea modificar la nacionalidad? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n NACIONALIDAD: ");
                nacionalidad = sc.nextLine();
            } else nacionalidad = d.nacionalidad;
            out.println("¿Desea modificar la fecha de nacimiento? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n FECHA DE NACIMIENTO (dd/mm/yyyy): ");
                boolean isValid;
                Calendar cal= Calendar.getInstance();
                do{
                    isValid = true;
                    date_tmp = sc.nextLine().split("/");
                    if (date_tmp[0] == null || date_tmp[1] == null ||date_tmp[2] == null) err.println("Formato de fecha no válido");
                    else{
                    try{
                        date0 = Integer.parseInt(date_tmp[0]);
                        if (date0 < 0 || date0 > 31){
                            err.println("Error: fecha no válida.");    
                            isValid = false;
        }
                        date1 = Integer.parseInt(date_tmp[1]);
                        if (date1 < 1 || date1 > 12){
                            err.println("Error: fecha no válida.");    
                            isValid = false;
                        }
                        date2 = Integer.parseInt(date_tmp[2]);  
                        if (date2 < 1850 || date2 > cal.get(Calendar.YEAR)){             
                            err.println("Error: fecha no válida.");    
                            isValid = false;  
                        }
                        if (isValid != false) fNacimiento = new Date(date2,date1,date0);
                    } catch(NumberFormatException e){
                        err.println("Error: fecha no válida.");
                        isValid = false;
                    }
                    }
                } while(isValid != true);
            } else fNacimiento = d.fNacimiento;
            
            out.println("¿Desea modificar la ocupación? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n OCUPACIÓN: ");
                ocupacion = sc.nextLine();
            } else ocupacion = d.ocupacion;

            out.println("¿Desea modificar las películas? S/N");
            modificar = sc.nextLine().toLowerCase();
            if (modificar.equals("s") || modificar.equals("si")){
                out.println("\n PELÍCULAS: ");
                do{
                    out.println ("\nIntroduce el título de la película: ");
                    tmp_string = sc.nextLine();
                        if (m.existsInFilmoteca("película", tmp_string) == true){
                            Película p = m.getPelicula(tmp_string);
                            peliculas.add(tmp_string);
                        }
                        else peliculas.add(tmp_string);
                    out.println("\n¿Desea introducir más títulos?: S/N");
                    tmp_string2 = sc.nextLine().toLowerCase();
                    salida = !(tmp_string2.equals("s") || tmp_string2.equals("si") || tmp_string2.equals("y") || tmp_string2.equals("yes"));
                } while(salida != true);
            } else peliculas = d.cPeliculas;
            m.remove("director", nombre);
            m.setDirector(nombre, nacionalidad, fNacimiento, ocupacion, peliculas); 
        }
        else err.println("Elemento no encontrado.");
    }

    /**
     * Importa desde un archivo binario
     * @param typeObject
     * @param dis
     * @param fis
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readBin(String typeObject, ObjectInputStream dis, FileInputStream fis) throws IOException, ClassNotFoundException {
        switch(typeObject){
            case "actor": 
                // ACTORES
                Actor a;
                out.println("Cargando actores...");
                if (fis.available() != 0){ 
                    try{
                        while (null != (a = (Actor) dis.readObject()))
                        {
                           ArrayList<String> peliculas = a.cPeliculas;
                           for (String s : peliculas) m.setPelicula(s, 0, 0, null, null, null, null, null, null, null, null, null);
                           m.setActorIntoHashMap(a);
                        }
                    } catch(StreamCorruptedException e){
                        break;
                    } 
                }
                break;
            case "director":
                // DIRECTORES
                Director d;
                out.println("Cargando directores...");
                if (fis.available() != 0){
                    try{
                        while (null!=(d = (Director) dis.readObject()))
                        {
                           ArrayList<String> peliculas = d.cPeliculas;
                           for (String s : peliculas) m.setPelicula(s, 0, 0, null, null, null, null, null, null, null, null, null);
                           m.setDirectorIntoHashMap(d);
                        }
                    } catch(StreamCorruptedException e){
                        break;
                    }
                }
                break;
            case "película":
                // PELICULAS
                Película p;
                out.println("Cargando películas...");
                if (fis.available() != 0){
                    try{
                        
                        while ((p = (Película) dis.readObject())!=null)
                        {
                            m.setPeliculaIntoHashMap(p);
                        }
                    } catch(StreamCorruptedException e){
                        break;
                    }
                }
                break;
        }
    }
    
    /**
     * Importa desde un archivo de texto
     * @param i
     * @param fr
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private void readTxt(String typeObject,BufferedReader fr)  throws IOException, ClassNotFoundException, ParseException{
        switch(typeObject){
            case "actor":
                String cadena;
                Date date;
                String[] tmp  = new String[3];
                ArrayList cPeliculas = new ArrayList();
                String[] peliculas;
                int debut;
                out.println("Cargando actores...");
                
                /*
                 Vamos leyendo cada línea, primero la separamos por sus #, y después, separamos el campo
                 de las películas por los \t. Si no existen dichas películas
                 en la filmoteca, las añade directamente. Además, tomamos la fecha de nacimiento mediante
                 un split, separando el día, mes y año, y ajustándolo en el constructor de Date.
                */
                
                while((cadena = fr.readLine())!=null) {
                    String[] split = cadena.split("#");
                    peliculas = split[4].split("\t");
                    for (int j=0; j< peliculas.length; j++){
                        cPeliculas.add(peliculas[j]);
                        if (m.existsInFilmoteca("película", peliculas[j]) == false){
                            m.setPelicula(peliculas[j],0,0,null,null,null,null,null,null,null,null,null);     
                        }
                    }
                    if (!"".equals(split[1])){
                        tmp = split[1].split("-");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                        tmp[0] = String.valueOf(Integer.parseInt(tmp[0]));
                        String dateInString = tmp[2] + "/" + tmp[1] + "/" + tmp[0];
                        date = sdf.parse(dateInString);
                    }
                    else date = new Date();
                    try{
                        debut = Integer.parseInt(split[3]);
                    } catch(NumberFormatException e){
                        debut = 0;
                    }
                    m.setActor(split[0], split[2], cPeliculas, debut, date);
                    cPeliculas = new ArrayList();
                }
                fr.close();
                break;
            case "director":
                Director d;
                String cadena2;
                Date date2;
                String[] tmp2 = new String[3];
                ArrayList cPeliculas2 = new ArrayList();
                out.println("Cargando directores...");
                
                /*
                 Vamos leyendo cada línea, primero la separamos por sus #, y después, separamos el campo
                 de las películas por los \t. Si no existen dichas películas
                 en la filmoteca, las añade directamente. Además, tomamos la fecha de nacimiento mediante
                 un split, separando el día, mes y año, y ajustándolo en el constructor de Date.
                */
                while((cadena2 = fr.readLine())!=null) {
                    String[] split = cadena2.split("#");
                    String[] peliculas2 = split[4].split("\t");
                    for (int j = 0; j < peliculas2.length; j++){
                        cPeliculas2.add(peliculas2[j]);
                        if (m.existsInFilmoteca("película", peliculas2[j]) == false){
                            m.setPelicula(peliculas2[j],0,0,null,null,null,null,null,null,null,null,null);
                        }
                    }
                    if (!"".equals(split[1])){
                        tmp = split[1].split("-");
                        date2 = new Date(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]));    
                    }
                    else date2 = new Date();
                    m.setDirector(split[0], split[2], date2, split[3], cPeliculas2);
                    cPeliculas2 = new ArrayList();
                }
                // DIRECTORES
                fr.close();
                break;
            case "película":
                // PELICULAS
                Película p = new Película();
                ArrayList actores = new ArrayList();
                ArrayList directores = new ArrayList();
                String cadena3;
                float numero1;
                int numero2;
                out.println("Cargando películas...");
                
                /*
                 Vamos leyendo cada línea, primero la separamos por sus #, y después, separamos el campo
                 de los directores y los actores por los \t. Si no existen dichos actores y directores
                 en la filmoteca, los añade directamente.
                */
                while ((cadena3 = fr.readLine()) !=null){
                    String[] split = cadena3.split("#");
                    String[] tmp3 = split[4].split("\t");
                    for (int j = 0; j < tmp3.length; j++){
                        directores.add(tmp3[j]);
                        if (m.existsInFilmoteca("director", tmp3[j]) == false){
                            m.setDirector(tmp3[j], null, null, null, null);
                        }
                    }
                    String[] tmp4 = split[8].split("\t");
                    for (int j = 0; j < tmp4.length; j++){
                        actores.add(tmp4[j]);
                        if (m.existsInFilmoteca("actor", tmp4[j]) == false){
                            m.setActor(tmp4[j], null, null, 0, null);
                        }
                    }
                    try{
                        numero1 = Float.parseFloat(split[1]);
                    }catch(NumberFormatException e){
                        numero1 = 0;
                    }
                    try{
                        numero2 =  Integer.parseInt(split[2]);
                    }catch(NumberFormatException ef){
                        numero2 = 0;
                    }
                    m.setPelicula(split[0], numero1, numero2, split[3], directores, split[5], split[6], split[7], actores, split[9], split[10], split[11]);
                    actores = new ArrayList();
                    directores = new ArrayList();
                }
                fr.close();

                break;
        }
    }

    /**
     * Exporta los directores a un archivo csv
     * @throws java.io.IOException
     */
    public void exportDirector() throws IOException {
        HashMap<String,Director> d = m.getDirectorSet();
        if (d!=null){
            try{
                fw5 = new FileWriter(m.THE_DIRECTORES_COL_FILE_PATH_AS_STRING);
                salida8 = new BufferedWriter(fw5);
            } catch(FileNotFoundException e){
                err.println("Cierre el archivo antes de continuar");
                return;
            }
            d.forEach((k,v)->{
                String salida = new String();
                salida = String.format("%-25s %15s %-15s %15s %-100s\n",v.nombre,v.fNacimiento.toString(),v.nacionalidad,v.ocupacion,v.cPeliculas.toString());
                try {
                    salida8.write(salida);
                } catch (IOException ex) {
                    err.println("Error en la creación del archivo");
                }
            });
        }
        else err.println("Director está vacío!");
        salida8.close();
        fw5.close();
    }

    /**
     * Busca una película en la filmoteca
     * @param typeObject
     * @param titulo
     * @return 
     */
    public boolean searchByTitle(String typeObject,String titulo) {
        return m.existsInFilmoteca(typeObject, titulo);
    }

    /**
     * Imprime un elemento de la filmoteca
     * @param typeObject
     * @param titulo 
     */
    public void printElement(String typeObject, String titulo) {
        if (m.existsInFilmoteca(typeObject, titulo) == true){
            String salida = new String();
            switch(typeObject){
                case "película":
                    Película p = m.getPelicula(titulo);
                    if (p.titulo == null) p.titulo = "nulo"; if (p.sinopsis == null) p.sinopsis = "nulo";
                    if (p.direccion == null){ p.direccion = new ArrayList(); p.direccion.add("nulo");} if (p.reparto == null){ p.reparto = new ArrayList(); p.reparto.add("nulo");}
                    if (p.guion == null) p.guion = "nulo"; if (p.musica == null) p.musica = "nulo";
                    if (p.fotografia == null) p.fotografia = "nulo"; if (p.pais == null) p.pais = "nulo";
                    if (p.productora == null) p.productora = "nulo"; if (p.genero == null) p.genero = "nulo";
                    salida = String.format("%-57s %-10.3f %-6d %-25s %-40s %-55s %-40s %-40s %-90s %-108s %-35s %-100s\n",p.titulo,p.duracion,p.ano,p.pais,p.direccion.toString(),p.guion,p.musica,p.fotografia,p.reparto,p.productora,p.genero,p.sinopsis);
                    
                    break;
                case "director":
                    Director d = m.getDirector(titulo);
                    if (d.nombre == null) d.nombre = "nulo"; if (d.ocupacion == null) d.ocupacion = "nulo";
                    if (d.nacionalidad == null) d.nacionalidad = "nulo"; if (d.cPeliculas == null){ d.cPeliculas = new ArrayList(); d.cPeliculas.add("nulo");}
                    if (d.fNacimiento == null) d.fNacimiento.setDate(0/0/0);
                    salida = String.format("%-25s %-25s %-40s %-70s %-100s\n",d.nombre,d.nacionalidad,d.fNacimiento.toString(),d.ocupacion,d.cPeliculas.toString());
                    break;
                case "actor":
                    Actor a = m.getActor(titulo);
                    if (a.nombre == null) a.nombre = "nulo"; if (a.cPeliculas == null){ a.cPeliculas = new ArrayList(); a.cPeliculas.add("nulo");}
                    if (a.nacionalidad == null) a.nacionalidad = "nulo"; if (a.fNacimiento == null) a.fNacimiento.setDate(0/0/0);
                    salida = String.format("%-30s %-30s %-150s %-15d %-15s\n",a.nombre,a.nacionalidad,a.cPeliculas.toString(),a.debut,a.fNacimiento.toString());
                    break;
            }
            out.printf("%s\n",salida);
        } else err.println("Elemento no encontrado.");
    }

    /**
     * Imprime una colección de la filmoteca
     * @param typeObject 
     */
    public void printHashMap(String typeObject) {
        switch(typeObject){
            case "película":
                HashMap<String, Película> selects = new HashMap<>();
                selects.putAll(m.getPeliculaSet());
                selects.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((key) -> {
                    if (!"".equals(key)) this.printElement("película", key);
                });
                break;
            case "actor":
                HashMap<String, Actor> selects1 = new HashMap<>();
                selects1.putAll(m.getActorSet());
                selects1.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((key) -> {
                    this.printElement("actor", key);
                });
                break;
            case "director":
                HashMap<String, Director> selects2 = new HashMap<>();
                selects2.putAll(m.getDirectorSet());
                selects2.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((key) -> {
                    this.printElement("director", key);
                });
        }
    }
        
    public void showFilms(String name){
        if (m.existsInFilmoteca("actor", name) == true){
            Actor a = m.getActor(name);
            ArrayList<Película> p = new ArrayList<>();
            for (String s : a.cPeliculas){
                if (m.existsInFilmoteca("película", s) == true){
                    printElement("película",s);
                }
            }      
        }
        else out.println("El actor no existe en la filmoteca");
    }
}
    
