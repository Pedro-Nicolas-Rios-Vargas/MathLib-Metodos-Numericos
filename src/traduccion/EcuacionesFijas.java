/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traduccion;

import almacenamiento.Pila;

import elementos.Parentesis;
import elementos.Operadores;

public class EcuacionesFijas {
    private Pila<Character> pila;
    private StringBuilder epos;
    private int limit;
    
    public EcuacionesFijas(){
        epos = new StringBuilder();
        
    }
    
    public String postFija(String ecuacion){
        limit = ecuacion.length();
        char popped = 0;
        char charOperador = 0;
        
        pila = new Pila<>(limit);
        
        char aux;
        
        for(int i = 0; i < limit; i++){
            aux = ecuacion.charAt(i);
            if(!pila.isEmpty()){
                if(Parentesis.esParentesis(pila.seeLastInput())){
                    charOperador = 0;
                }else{
                    charOperador = pila.seeLastInput();
                }
            }
            if(Parentesis.esParentesisDeApertura(aux)){
                pila.push(aux);
                pila.imprimirPila();
                /*
                if(charOperador != 0){
                    charOperador = 0;
                }
                */
                
            }else if(Operadores.esUnOperador(aux)){
                /*si ya existe un operador en la pila se entra en el if
                
                Si no existe un operador en la pila se agrega uno
                */
                if(charOperador != 0){
                    /*  se compara el operador entrante con el ultimo almacenado
                        si el entrante tiene un valor menor en jerarquia al almacenado
                        se sacara al de mayor jerarquia y sera añadida a la ecuacion transformada
                    
                        Si no, si resulta ser que es mayor o igual al ya almacenado se añadira a la pila y
                        remplazara el valor de operador mas grande ingresado.
                    */
                    if(Operadores.operadorMenor(aux, charOperador)){
                        popped = (Character) pila.pop();
                        epos.append(popped);
                        
                        charOperador = aux;
                        pila.push(aux);
                        pila.imprimirPila();
                    }else{
                        pila.push(aux);
                        charOperador = aux;
                        pila.imprimirPila();
                    }
                }else{
                    charOperador = aux;
                    pila.push(aux);
                    pila.imprimirPila();
                }
                
            }else if(Parentesis.esParentesisDeCierre(aux)){
                /*
                if(charOperador != 0){
                    charOperador = 0;
                }
                */
                do{
                    popped = (Character) pila.pop();
                    pila.imprimirPila();
                    
                    if(Operadores.esUnOperador(popped)){
                        epos.append(popped);
                    }
                }while(!Parentesis.esParentesisDeApertura(popped));
            
            }else{
                epos.append(aux);
                
            }
            
        }
        //System.out.println(epos.toString());
        
        while (pila.getPointer() > 0) {            
            popped = (Character) pila.pop();
            pila.imprimirPila();
            if(Operadores.esUnOperador(popped)){
                epos.append(popped);
            }
        }
        
        return epos.toString();
    }
}
