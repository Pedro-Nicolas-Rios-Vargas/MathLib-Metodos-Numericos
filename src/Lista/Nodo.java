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
public class Nodo<T> {
    public T valor;
    public Nodo<T> siguiente;
    public boolean valEliminado = false;
    
    public Nodo(){
        this.valor = null;
        this.siguiente = null;
    }
    
    public Nodo(T valor){
        this.valor = valor;
        this.siguiente = null;
    }

    
    public int length(){
        if(siguiente == null){
            return 0;
        }
        return 1 + siguiente.length();
    }
}
