/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Ricar
 */
public class Arista {
    
    public Vertice origen;
    public Vertice destino;
    public int peso;

    public Arista() {
    }
       
    public Arista(Vertice origen, Vertice destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
 
}
