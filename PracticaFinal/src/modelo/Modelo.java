/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Alex Sánchez
 */
public class Modelo {

    /* COLECCION DE DATOS SOBRE LOS ARCHIVOS Y LA CARPETA
     * Tenemos Tipo PATH, STRING Y FILE
     */
    Filmoteca f = new Filmoteca();

    public final String THE_FOLDER_PATH_AS_STRING = 
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER;
    public final Path THE_FOLDER_AS_PATH = 
        Paths.get(System.getProperty("user.home"),
        "Desktop", f.THE_FOLDER);
        
    public final String THE_ACTOR_BIN_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_ACTORES_BIN;
    
    public final String THE_DIRECTOR_BIN_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_DIRECTORES_BIN; 
    
    public final String THE_PELICULAS_BIN_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_PELICULAS_BIN; 
    
    public final String THE_ACTOR_TXT_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_ACTORES_TXT;
    
    public final String THE_DIRECTOR_TXT_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_DIRECTORES_TXT; 
    
    public final String THE_PELICULAS_TXT_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_PELICULAS_TXT;
    
    public final String THE_PELICULAS_HTML_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_PELICULAS_HTML; 
    public final String THE_DIRECTORES_COL_FILE_PATH_AS_STRING =
        System.getProperty("user.home")
        + File.separator
        + "Desktop" 
        + File.separator
        + f.THE_FOLDER
        + File.separator
        + f.FILE_DIRECTORES_COL;
    
    public final String HTML_HEAD = "<html>\n" +
    "<head>\n" +
    "<meta charset=\"UTF-8\"/>\n" +
    "  <style type=\"text/css\">\n" +
    "  @charset \"UTF-8\";\n" +
    "  table {\n" +
    "      border-collapse: collapse;\n" +
    "      border: black 5px solid;\n" +
    "  }\n" +
    "  tr {\n" +
    "      border-collapse: collapse;\n" +
    "      border: black 5px solid;\n" +
    "  }\n" +
    "  th {\n" +
    "      border-collapse: collapse;\n" +
    "      border: black 2px solid;  \n" +
    "  }\n" +
    "  td {\n" +
    "      border-collapse: collapse;\n" +
    "      border: black 2px solid;  \n" +
    "  }\n" +
    "  </style>\n" +
    "</head>\n" +
    "<body>\n" +
    "<table style=\"width:100%\">\n" +
    "  <tr>\n" +
    "    <th>Título</th>\n" +
    "    <th>Año</th> \n" +
    "    <th>Duración</th>\n" +
    "    <th>País</th>\n" +
    "    <th>Dirección</th>\n" +
    "    <th>Guión</th>\n" +
    "    <th>Música</th>\n" +
    "    <th>Fotografía</th>\n" +
    "    <th>Reparto</th>\n" +
    "    <th>Productora</th>\n" +
    "    <th>Género</th>\n" +
    "    <th>Sinopsis</th>\n" +
    "  </tr>";
    
    public final String HTML_END = "</table>\n" +
    "</body>\n" +
    "</html>";

    /**
     * Añade una película en la filmoteca
     * @param titulo
     * @param duracion
     * @param ano
     * @param pais
     * @param direccion
     * @param guion
     * @param musica
     * @param fotografia
     * @param reparto
     * @param productora
     * @param genero
     * @param sinopsis 
     */
    public void setPelicula(String titulo, float duracion, int ano, String pais, ArrayList<String> direccion, String guion, String musica, String fotografia, ArrayList<String> reparto, String productora, String genero, String sinopsis){
        try{
            Película tmp = new Película(titulo,duracion,ano,pais,direccion,guion,musica,fotografia,reparto,productora,genero,sinopsis);
            f.cPeliculas.put(titulo,tmp);
        } catch(NullPointerException e){
            err.println("Error: No se pudo añadir la película");
        }
    }
    
    /**
     * Añade una película a la filmoteca
     * @param nombre
     * @param nacionalidad
     * @param fNacimiento
     * @param ocupacion
     * @param cPeliculas 
     */
    public void setDirector(String nombre, String nacionalidad, Date fNacimiento, String ocupacion, ArrayList<String> cPeliculas){
        try{
            Director tmp = new Director(nombre,nacionalidad,fNacimiento,ocupacion,cPeliculas);
            f.cDirector.put(nombre,tmp);
        } catch(NullPointerException e){
            err.println("Error: No se pudo añadir el/la director/a");
        }
    }
    
    /**
     * Añade un actor en la filmoteca
     * @param nombre
     * @param nacionalidad
     * @param cPeliculas
     * @param debut
     * @param fNacimiento
     */
    public void setActor(String nombre, String nacionalidad, ArrayList<String> cPeliculas, int debut, Date fNacimiento){
        try{
            Actor tmp = new Actor(nombre,nacionalidad,cPeliculas,debut,fNacimiento);
            f.cActor.put(tmp.nombre,tmp);
        } catch(NullPointerException e){
            err.println("Error: No se pudo añadir el/la actor/a");
        }
    }
    
    /**
     * Devuelve el HashMap de Películas
     * @return 
     */
    public HashMap<String, Película> getPeliculaSet(){
        return f.cPeliculas;
    }

    /**
     * Devuelve el HashMap de Director
     * @return 
     */
    public HashMap<String, Director> getDirectorSet(){
        return f.cDirector;
    }
    
    /**
     * Devuelve el HashMap de Actor
     * @return
     */
    public HashMap<String, Actor> getActorSet(){
        return f.cActor;
    }
    
    /**
     * Introduce una película en un HashMap
     * @param p 
     */
    public void setPeliculaIntoHashMap(Película p){
        f.cPeliculas.put(p.titulo,p);
    }
    
    /**
     * Introduce un director en un HashMap
     * @param d
     */
    public void setDirectorIntoHashMap(Director d){
        f.cDirector.put(d.nombre,d);
    }
    
    /**
     * Introduce un actor en un HashMap
     * @param a 
     */
    public void setActorIntoHashMap(Actor a){
        f.cActor.put(a.nombre, a);
    }

    /**
     * Elimina un objeto de la Filmoteca
     * @param typeObject
     * @param nombre 
     */
    public void remove(String typeObject, String nombre) {
        switch(typeObject){
            case "película":
                try{
                    if (this.existsInFilmoteca(typeObject, nombre) == true){
                        f.cPeliculas.remove(nombre);
                    }
                    else out.println("No existe el elemento.");
                } catch(NullPointerException e){
                    err.println("Error. No existe el archivo a eliminar.");
                }
                break;
            case "actor":
                try{
                    if (this.existsInFilmoteca(typeObject, nombre) == true){
                        f.cActor.remove(nombre);
                    }
                    else out.println("No existe el elemento.");
                }catch(NullPointerException e){
                    err.println("Error. No existe el archivo a eliminar.");
                }
                break;
            case "director":
                try{
                    if (this.existsInFilmoteca(typeObject, nombre) == true){
                        f.cDirector.remove(nombre);
                    }
                    else out.println("No existe el elemento."); 
                } catch(NullPointerException e){
                    err.println("Error. No existe el archivo a eliminar.");
                }
                break;
        }
    }
    
    /**
     * Modifica un actor de la filmoteca
     * @param a
     * @param nombre 
     */
    public void modifyActor(Actor a, String nombre){
        f.cActor.replace(nombre, a);
    }

    /**
     * Modifica un director de la filmoteca
     * @param d
     * @param nombre 
     */
    public void modifyDirector(Director d, String nombre){
        f.cDirector.replace(nombre, d);
    }
    
    /**
     * Modifica una película de la filmoteca
     * @param p
     * @param nombre 
     */
    public void modifyPelícula(Película p, String nombre){
        f.cPeliculas.replace(nombre, p);
    }
    
    /**
     * Comprueba si existe un elemento o no en la filmoteca
     * @param typeObject
     * @param name
     * @return 
     */
    public boolean existsInFilmoteca(String typeObject, String name){
        boolean exist = false;
        switch(typeObject){
            case "película":
                exist= f.cPeliculas.containsKey(name); break;
            case "actor":
                exist= f.cActor.containsKey(name); break;
            case "director":
                exist= f.cDirector.containsKey(name); break;
        }
        return exist;
    }
    
    /**
     * Devuelve un actor por su nombre
     * @param name
     * @return 
     */
    public Actor getActor(String name){
        return f.cActor.get(name);
    }
    
    /**
     * Devuelve un director por su nombre
     * @param name
     * @return 
     */
    public Director getDirector(String name){
        return f.cDirector.get(name);
    }
    
    /**
     * Devuelve una película por su título
     * @param name
     * @return 
     */
    public Película getPelicula(String name){
        return f.cPeliculas.get(name);
    }
    
    /**
     * Devuelve una colección en forma de Strings
     * @param typeObject
     * @param name
     * @return 
     */
    public String[] getCollection(String typeObject,String name){
        switch(typeObject){
            case "película":
                String[] element = f.convertPeliculaIntoString(name);
                return element;
            case "actor":
                String[] element1 = f.convertActorIntoString(name);
                return element1;
            case "director":
                String[] element2 = f.convertDirectorIntoString(name);
                return element2;
        }
        return null;
    }

    public void addFilmTo(String typeObject, String name, String titulo){
        switch (typeObject){
            case "director":
                Director d = this.getDirector(name);
                if (d.cPeliculas.contains(titulo) == false) d.cPeliculas.add(titulo);
                break;
            case "actor":
                Actor a = this.getActor(name);
                if (a.cPeliculas.contains(titulo) == false) a.cPeliculas.add(titulo);
                break;
        }
    }

}