/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Alex Sánchez
 */
public class Película implements Comparable, Serializable{

    // ATRIBUTOS DE PELICULA
    public String titulo;
    public float duracion;
    public int ano;
    public String pais;
    public ArrayList<String> direccion;
    public String guion;
    public String musica;
    public String fotografia;
    public ArrayList<String> reparto;
    public String productora;
    public String genero;
    public String sinopsis;

    /**
     * Constructor Película
     *
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
    public Película(String titulo, float duracion, int ano, String pais, ArrayList<String> direccion, String guion, String musica, String fotografia, ArrayList<String> reparto, String productora, String genero, String sinopsis) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.ano = ano;
        this.pais = pais;
        this.direccion = direccion;
        this.guion = guion;
        this.musica = musica;
        this.fotografia = fotografia;
        this.reparto = reparto;
        this.productora = productora;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Película() {
        this.ano = 0;
        this.direccion = new ArrayList();
        this.duracion = 0;
        this.fotografia = new String();
        this.genero = new String();
        this.guion = new String();
        this.musica = new String();
        this.pais = new String();
        this.productora = new String();
        this.reparto = new ArrayList();
        this.sinopsis = new String();
        this.titulo = new String();
    }

    @Override
    public int compareTo(Object o) {
        Película p = (Película) o;
        if (this.ano > p.ano) return 1;
        if (this.ano < p.ano) return -1;
        else return 0;
    }
}