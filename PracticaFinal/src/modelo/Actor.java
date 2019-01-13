/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 *
 * @author Alex Sánchez
 */
public class Actor implements Serializable{
    
    // Atributos de la clase ACTOR
    public String nombre;
    public String nacionalidad;
    public ArrayList<String> cPeliculas;
    public int debut;
    public Date fNacimiento;

    /**
     * Constructor Actor
     * @param nombre
     * @param nacionalidad
     * @param cPeliculas
     * @param debut
     * @param fNacimiento
     */
    public Actor(String nombre, String nacionalidad, ArrayList<String> cPeliculas, int debut, Date fNacimiento) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.cPeliculas = cPeliculas;
        this.debut = debut;
        this.fNacimiento = fNacimiento;
    }  

    public Actor() {
        this.nombre = new String();
        this.cPeliculas = new ArrayList();
        this.debut = 0;
        this.fNacimiento = null;
        this.nacionalidad = new String();
    }
}
