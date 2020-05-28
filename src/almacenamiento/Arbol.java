/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento;

import elementos.Operadores;

/**
 *
 * @author pnrv2
 */
public class Arbol {
    Hijo<Object> raiz;
    Hijo<Object> pointer;
    
    public Arbol(){
        raiz = null;
        pointer = null;
    }
    
    public void crear(Object value){
        if(raiz == null){
            raiz = new Hijo<>(value);
        }
    }
    
    public void insertar(Object value){
        insercion(new Hijo<>(value));
    }
    
    private void insercion(Hijo<Object> insercion){
        Hijo<Object> nuevo = insercion;
        
        if(raiz.derecha == null){
            raiz.derecha = nuevo;
        }else if(raiz.izquierda == null){
            raiz.izquierda = nuevo;
        }
    }
    
    public Hijo<Object> getRaiz(){
        return raiz;
    }
}
