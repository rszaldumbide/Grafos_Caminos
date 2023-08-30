/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author ricar
 */
public class nodo {
    
    private String vertice;
    private int peso;
    
    public nodo(String ver, int peso){
        vertice=ver;
        this.peso=peso;
    }

    public String getVertice() {
        return vertice;
    }

    public void setVertice(String vertice) {
        this.vertice = vertice;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
    
}
