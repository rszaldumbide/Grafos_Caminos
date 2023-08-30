/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Ricar
 */
public class Vertice {

    public String cuidad;
    public ArrayList<Arista> aristasAdyacentes;
    
    public int x, y;
    public boolean visitado;
        
    public Vertice(String cuidad, int x, int y) {
        this.cuidad = cuidad;
        aristasAdyacentes = new ArrayList<>();
        this.x=x;
        this.y=y;
        this.visitado=false;
    }

    public void agregarArista(Arista nuevaArista) {
        aristasAdyacentes.add(nuevaArista);
    }
    
    public ArrayList<Arista> mostrarAristasAdyacentes(){
        return aristasAdyacentes;
    }

    @Override
    public String toString() {
        return  cuidad;
    }
    
    
}
