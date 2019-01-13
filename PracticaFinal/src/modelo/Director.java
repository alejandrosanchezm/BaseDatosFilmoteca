/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alex Sánchez
 */
public class Director implements Serializable{
    
    public String nombre;
    public String nacionalidad;
    public Date fNacimiento;
    public String ocupacion;
    public ArrayList<String> cPeliculas;

    /**
     * Constructor Director
     * @param nombre
     * @param nacionalidad
     * @param fNacimiento
     * @param ocupacion
     * @param cPeliculas
     */
    public Director(String nombre, String nacionalidad, Date fNacimiento, String ocupacion, ArrayList<String> cPeliculas) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fNacimiento = fNacimiento;
        this.ocupacion = ocupacion;
        this.cPeliculas = cPeliculas;
    }

    public Director() {
        this.nombre = new String();
        this.nacionalidad = new String();
        this.fNacimiento = null;
        this.ocupacion = new String();
        this.cPeliculas = new ArrayList();
    }

}