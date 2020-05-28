/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento;


public class Hijo<T> {
    public T valor;
    public Hijo<T> izquierda, derecha, padre;
    
    public Hijo(){
        valor = null;
        izquierda = null;
        derecha = null;
        padre = null;
    }
    
    public Hijo(T valor){
        this.valor = valor;
        izquierda = null;
        derecha = null;
        padre = null;
    }
}
