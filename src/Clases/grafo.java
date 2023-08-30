/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.*;

/**
 *
 * @author Ricar
 */
public class grafo {

    public ArrayList<Vertice> listaVerices;

    public grafo() {
        listaVerices = new ArrayList<>();
    }

    public grafo(ArrayList<Vertice> listaVerices) {
        this.listaVerices = listaVerices;
    }

    public void agregarVertice(Vertice nuevoVertice) {
        listaVerices.add(nuevoVertice);
    }

    public Vertice buscarVertice(String cuidadName) {
        for (Vertice x : listaVerices) {
            if (x.cuidad.equals(cuidadName)) {
                return x;   
            }
        }
        return null;
    }
    
    public ArrayList<Vertice> rProfundidad(Vertice inicio){
        //1-necesitamos una pila
        //2-agregamos los adyacentes no visitados por la pila
        //3-actualizar el visitado
        //4-recorrer la pila hasta que este vacia
        
        Stack<Vertice> pilaNoVisitados=new Stack<>();
        ArrayList<Vertice> recorrido=new ArrayList<>();
        
        ArrayList<Arista> adyacentes=inicio.mostrarAristasAdyacentes();
        
        recorrido.add(inicio);
        inicio.visitado=true;
        
        for(Arista a: adyacentes){  
            a.destino.visitado=true;
            pilaNoVisitados.add(a.destino);
        }
        
        while(!pilaNoVisitados.empty()){
            Vertice tmp =pilaNoVisitados.pop();
            recorrido.add(tmp);
            for(Arista a: tmp.mostrarAristasAdyacentes()){
                if(a.destino.visitado==false){
                    a.destino.visitado =true;
                    pilaNoVisitados.push(tmp);
                }
            }
        }
        return recorrido;
    }

    public ArrayList<ArrayList<String>> rCaminos(String origen, String destino){

        //Array para la respuesta, de tipo doble array, para que se guarden los caminos
        ArrayList<ArrayList<String>> recorrido=new ArrayList<>();
        //Array para el camino 
        ArrayList<String> camino = new ArrayList<>();
        //se añade el primer elemento, sea el origen
        camino.add(origen);
        Vertice tmp = buscarVertice(origen);
        ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;
        
        for(Arista a: adyacentes){
            String actual = a.destino.cuidad;
            //Para que no se alteren los caminos, o se subescriban
            ArrayList<String> copia = (ArrayList<String>)camino.clone(); 
            
            copia.add(actual);
            //Metodo recursivo para mostrar los posibles caminos
            caminos(actual, destino, copia, recorrido);
        }

        return recorrido;
    }
    
    private void caminos(String origen, String destino, ArrayList<String> camino, ArrayList<ArrayList<String>> recorrido){
        
        Vertice tmp = buscarVertice(origen);
        if(tmp.cuidad.equals(destino)){
            //se agrega el posible camino al array de respuesta
            recorrido.add(camino);
        }else{
            ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;
            if(!adyacentes.isEmpty()){
                //se aplica la recursividad
                for(Arista a: adyacentes){
                    String actual = a.destino.cuidad;
                    //Para que no se alteren los caminos, o se subescriban
                    ArrayList<String> copia = (ArrayList<String>)camino.clone();
                    if(!copia.contains(actual)){
                        copia.add(actual);
                        //Metodo privado para mostrar los posibles caminos
                        caminos(actual, destino, copia, recorrido);                        
                    }
                }
            }     
        }
    }    

    public String pesosMasCaminos(String origen, String destino){
        String msg="";
        int peso = 0;

        //Array para la respuesta final de ciudades 
        ArrayList<ArrayList<String>> recorrido=new ArrayList<>();
        //Array para la respuesta final de pesos
        ArrayList<ArrayList<Integer>> pesos =new ArrayList<>();  
        //ArrayList para el peso en cada recorrido:
        ArrayList<Integer> peso2 = new ArrayList<>();  
        //Array para el camino  en cada recorrido
        ArrayList<String> camino = new ArrayList<>();
        //array para guardar las longitudes del grafo
        ArrayList<Integer> arrayLongitud =new ArrayList<>();
        //array para guardar las sumas de los pesos
        ArrayList<Integer> arraypeso1 =new ArrayList<>();
        //se añade el primer elemento, sea el origen
        camino.add(origen);

        Vertice tmp = buscarVertice(origen);        
        ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;

        for(Arista a: adyacentes){
            peso = a.peso;
            String actual = a.destino.cuidad;  

            //Para que no se alteren los caminos, o se subescriban
            ArrayList<String> copia = (ArrayList<String>)camino.clone();
            ArrayList<Integer> copia2 = (ArrayList<Integer>)peso2.clone();
            copia.add(actual);
            copia2.add(peso);

            //Metodo privado para mostrar los posibles caminos
            caminosmasPesos(actual, destino, copia, recorrido, pesos, copia2, arrayLongitud, arraypeso1);
        }
        
        msg += "Los recorridos son: "+recorrido +" \n";
        msg += " Los pesos son: "+arraypeso1+" \n";
        msg += " La longitud es: "+arrayLongitud+" \n";
        
        return msg;
    }
    
    int tmpLongi;
    int pesofinal;
    private void caminosmasPesos(String origen, String destino, ArrayList<String> camino, ArrayList<ArrayList<String>> recorrido,
                                    ArrayList<ArrayList<Integer>> pesos, ArrayList<Integer> peso2, ArrayList<Integer> arrayLongitud, ArrayList<Integer> arraypeso1){
        int peso = 0;
        pesofinal=0;
        tmpLongi=0;
        Vertice tmp = buscarVertice(origen);
        if(tmp.cuidad.equals(destino)){
            recorrido.add(camino);
            //pesos.add(peso2);
                for(int j=0; j<peso2.size(); j++){
                    pesofinal+=peso2.get(j);
                }
                arraypeso1.add(pesofinal);
                pesos.add(arraypeso1);

            tmpLongi=peso2.size();
            arrayLongitud.add(tmpLongi);
            
        }else{            
            ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;
            if(!adyacentes.isEmpty()){
                
                for(Arista a: adyacentes){
                    
                    peso = a.peso;                  
                    String actual = a.destino.cuidad;
       
                    //Para que no se alteren los caminos, o se subescriban
                    ArrayList<String> copia = (ArrayList<String>)camino.clone();
                    ArrayList<Integer> copia2 = (ArrayList<Integer>)peso2.clone();
                    
                    if(!copia.contains(actual)){
                        copia.add(actual);
                        copia2.add(peso);
                        //Metodo privado para mostrar los posibles caminos
                        caminosmasPesos(actual, destino, copia, recorrido, pesos, copia2, arrayLongitud, arraypeso1);                        
                    
                    }
                }
             
            }     
        }
    }
//    public ArrayList<ArrayList<Integer>> rCaminosMasPesos(String origen, String destino){
//    lon=0;
//        //Array para la respuesta, de tipo doble array, para que se guarden los caminos
//        ArrayList<ArrayList<Integer>> recorrido=new ArrayList<>();
//        //Array para el camino 
//        ArrayList<Integer> camino = new ArrayList<>();
//        //se añade el primer elemento, sea el origen
//        camino.add(Integer.parseInt(origen));
//        Vertice tmp = buscarVertice(origen);
//        ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;
//        
//        for(Arista a: adyacentes){
//            int actual = Integer.parseInt(a.destino.cuidad);
//            //peso=peso+a.peso;
//            //Para que no se alteren los caminos, o se subescriban
//            ArrayList<Integer> copia = (ArrayList<Integer>)camino.clone(); 
//            copia.add(actual);
//            //Metodo recursivo para mostrar los posibles caminos
//            caminosMasPesos(actual, destino, copia, recorrido);
//        }
//
//        return recorrido;
//    }
//    
//    private void caminosMasPesos(int origen, String destino, ArrayList<Integer> camino, ArrayList<ArrayList<Integer>> recorrido){
//        
//        Vertice tmp = buscarVertice(String.valueOf(origen));
//        if(tmp.cuidad.equals(destino)){
//            //se agrega el posible camino al array de respuesta
//            recorrido.add(camino);
//        }else{
//            ArrayList<Arista> adyacentes=tmp.aristasAdyacentes;
//            if(!adyacentes.isEmpty()){
//                //se aplica la recursividad
//                for(Arista a: adyacentes){
//                    int actual = Integer.parseInt(a.destino.cuidad);
//                    //Para que no se alteren los caminos, o se subescriban
//                    ArrayList<Integer> copia = (ArrayList<Integer>)camino.clone();
//                    
//                    if(!copia.contains(actual)){
//                        copia.add(actual);
//                        //Metodo privado para mostrar los posibles caminos
//                        caminosMasPesos(actual, destino, copia, recorrido);                        
//                    }
//                }
//            }     
//        }
//    }
}
