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
public class ListaCola<T> {
    private Nodo<T> raiz;
    private Nodo<T> puntero;
    
    
    /**
    *   El metodo push sirve para ingresar un valor en la lista
    *
    *   {@link #push()} 
    *   @param valor dato de tipo {@code T} que sera ingresado
     */
    public void push(T valor){
        Nodo<T> nuevo = new Nodo(valor);
        
        if(raiz == null){
            raiz = nuevo;
            puntero = raiz;
        }else{
            //puntero = raiz;
            puntero.siguiente = nuevo;
            puntero = puntero.siguiente;
        }
    }
    
    /**
     * El metodo pop sirve para sacar un valor de la lista
     * 
     * {@link #pop()}
     */
    public T pop(){
        //puntero = raiz;
        T value = null;
        Nodo<T> aux = null;
        
        if(raiz != null){
            aux = raiz.siguiente;
        }
        
        value = raiz.valor;
        raiz.siguiente = null;
        raiz = aux;
        /*
        while(puntero.siguiente != null){
            //System.out.println("El contenido es: "+puntero.valor);
            if(!puntero.valEliminado){
                
                value = (T) puntero.valor;
                if(!puntero.siguiente.valEliminado){
                    puntero = puntero.siguiente;
                }else
                    break;
                    
                
            }else
                break;
        }
        //System.out.println("El contenido es: "+puntero.valor);
        if(!puntero.valEliminado){
            value = (T) puntero.valor;
            puntero.valEliminado = true;
        }
        */
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
        Nodo<T> aux;
        if(raiz == null){
            return false;
        }
        return true;
    }
    
    /** El metodo length retorna un valor entero refiriendo a la cantidad de
     *  elementos que tiene dentro
     *  {@link #length()}
     * 
     *  @return {@code 0} si la lista esta vacia
     *          {@code >0} si la lista contiene elementos
     */
    public int length(){
        return raiz.length();
    }
    
}
