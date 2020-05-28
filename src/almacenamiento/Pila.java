/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento;


public class Pila<T> {
    
    private int pointer;
    private Object[] pilaArreglo;
    private int limit;
    private int ingreso;
    
    public Pila(int length){
        limit = length;
        pointer = 0;
        ingreso = -1;
        pilaArreglo = new Object[length];
        
    }
    
    public void push(T caracter){
        if(ingreso < limit){
            pilaArreglo[pointer] = (Object) caracter;
            pointer++;
            ingreso++;
        }else{
            System.out.println("La pila esta llena");
        }
    }
    
    public T pop(){
        if(ingreso == -1){
            System.out.println("La pila esta vacia");
            return null;
        }
        T out = (T) pilaArreglo[pointer-1];
        pilaArreglo[pointer-1] = null;
        pointer--;
        ingreso--;
        return out;
    }
    
    public T seeLastInput(){
        T out = (T) pilaArreglo[pointer-1];
        return out;
    }
    
    public int getPointer(){
        return pointer;
    }
    
    public void imprimirPila(){
        int cont = 0;
        System.out.println("\nCONTENDIO DE PILA\n");
        if(ingreso == -1){
            System.out.println("Pila Vacia\n");
            return;
        }
        
        for(int i = pilaArreglo.length - 1; i >= 0; i--){
            if(pilaArreglo[i] != null){
                System.out.print(pointer-cont+" "+pilaArreglo[i]);
                if(i == pointer-1){
                    System.out.println(" <-- TOPE");
                }else{
                    System.out.println("");
                }
                
                cont++;
            }
        }
    }
    
    public boolean isEmpty(){
        if(ingreso < 0){
            return true;
        }
        return false;
    }
}
