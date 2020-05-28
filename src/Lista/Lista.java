/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista;

/**
 *
 * @author pnrv2
 */
public class Lista<T> {
    private Nodo raiz;
    private Nodo puntero;
    
    
    /**
    *   El metodo push sirve para ingresar un valor en la lista
    *
    *   {@link #push()} 
    *   @param valor dato de tipo {@code T} que sera ingresado
     */
    public void push(T valor){
        Nodo nuevo = new Nodo(valor);
        
        if(raiz == null){
            raiz = nuevo;
            
        }else{
            puntero = raiz;
            while(puntero.siguiente != null){
                puntero = puntero.siguiente;
            }
            puntero.siguiente = nuevo;
        }
    }
    
    /**
     * El metodo pop sirve para sacar un valor en la lista
     * 
     * {@link #pop()}
     */
    public T pop(){
        puntero = raiz;
        T value = null;
        while(puntero.siguiente != null){
            //System.out.println("El contenido es: "+puntero.valor);
            
            if(!puntero.valEliminado){
                break;
            }
            puntero = puntero.siguiente;
        }
        if(!puntero.valEliminado){
            value = (T) puntero.valor;
            puntero.valEliminado = true;
        }
        return value;
    }
    
    /**El metodo hasNext verifica si existe otro elemento dentro de la lista.
     * 
     * {@link #hasNext()}
     * 
     * @return  {@code true} si existe un siguiente elemento
     *          {@code false} si no hay mas elementos
     */
    public boolean hasNext(){
        puntero = raiz;
        while(puntero.siguiente != null){
            if(!puntero.valEliminado){
                return true;
            }
            puntero = puntero.siguiente;
        }
        
        if(!puntero.valEliminado){
            return true;
        }
        return false;
    }
    
    /** El metodo length retorna un valor entero refiriendo a la cantidad de
     *  elementos que tiene dentro
     *  {@link #length()}
     * 
     *  @return {@code 0} si la lista esta vacia
     *          {@code >0} si la lista contiene elementos
     */
    public int length(){
        if(raiz == null){
            return 0;
        }
        
        Nodo pointerAux = raiz;
        int count = 1;
        while(pointerAux.siguiente != null){
            count++;
        }
        return count;
    }
    
}
