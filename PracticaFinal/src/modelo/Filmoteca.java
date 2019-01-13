/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Alex Sánchez
 */
public class Filmoteca implements Serializable{
    
    public final String FILE_ACTORES_BIN= "actores.bin";
    public final String FILE_DIRECTORES_BIN="directores.bin";
    public final String FILE_PELICULAS_BIN="peliculas.bin";
    public final String FILE_ACTORES_TXT= "actores.txt";
    public final String FILE_DIRECTORES_TXT="directores.txt";
    public final String FILE_PELICULAS_TXT="peliculas.txt";
    public final String FILE_PELICULAS_HTML="peliculas.html";
    public final String FILE_DIRECTORES_COL="directores.col";
    
    public final String THE_FOLDER = "Filmot18";

    HashMap<String, Película> cPeliculas;
    HashMap<String, Actor> cActor;
    HashMap<String, Director> cDirector;

    public Filmoteca() {
        this.cPeliculas = new HashMap<>();
        this.cActor = new HashMap<>();
        this.cDirector = new HashMap<>();
    }
    
    /**
     * Convierte la clase Película en un array de String.
     * @param name
     * @return String[] elements.
     */
    public String[] convertPeliculaIntoString(String name){
        Película pelicula = cPeliculas.get(name);
        if (pelicula!=null){
            String[] elements = new String[12];
            elements[0] = pelicula.titulo;
            elements[1] = String.valueOf(pelicula.duracion);
            elements[2] = String.valueOf(pelicula.ano);
            elements[3] = pelicula.pais;
            if (pelicula.direccion!= null) elements[4] = pelicula.direccion.toString();
            else elements[4] = "";
            elements[5] = pelicula.guion;
            elements[6] = pelicula.musica;
            elements[7] = pelicula.fotografia;
            if (pelicula.reparto!= null) elements[8] = pelicula.reparto.toString();
            else elements[8] = ""; 
            elements[9] = pelicula.productora;
            elements[10] = pelicula.genero;
            elements[11] = pelicula.sinopsis;
            return elements;
        }
        else return null;
    }
    
    /**
     * Convierte la clase Actor en un array de String.
     * @param name
     * @param i posición de la Película en el ArrayList.
     * @return String[] elements.
     */
    public String[] convertActorIntoString(String name){
        Actor actor = cActor.get(name);
        if (actor != null){
            String[] elements = new String[5];
            elements[0] = actor.nombre;
            elements[1] = actor.nacionalidad;
            elements[2] = actor.cPeliculas.toString();
            elements[3] = String.valueOf(actor.debut);
            elements[4] = String.valueOf(actor.fNacimiento);
            return elements;
        }
        else return null;
    }
    
    /**
     * Convierte la clase Director en un array de String.
     * @param name
     * @param i posición de la Película en el ArrayList.
     * @return String[] elements.
     */
    public String[] convertDirectorIntoString(String name){
        Director director = cDirector.get(name);
        if (director != null){
            String[] elements = new String[5];
            elements[0] = director.nombre;
            elements[1] = director.nacionalidad;
            elements[2] = String.valueOf(director.fNacimiento);
            elements[3] = director.ocupacion;
            elements[4] = director.cPeliculas.toString();
            return elements;
        }
        else return null;
    }
}
