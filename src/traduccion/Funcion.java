/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traduccion;

import almacenamiento.*;

import Lista.ListaCola;
import elementos.Variables;
import elementos.Operadores;

import src.MathLib;

public class Funcion {
    
    private String ecuacionPostFija;
    private ListaCola<String> cola;
    
    public Funcion(String ecuacionPostFija, ListaCola<String> colaConstantes){
        this.ecuacionPostFija = ecuacionPostFija;
        this.cola = colaConstantes;
    }
    
    public Hijo postFijaaArbol(){
        Pila<Object> pila = new Pila<>(ecuacionPostFija.length());
        Arbol arbol;
        char caracter = 0;
        double constante;
        for(int i = 0; i < ecuacionPostFija.length(); i++){
            caracter = ecuacionPostFija.charAt(i);
            //245 = Â§ = CONSTANTE 244 = Â¶ = -1
            if(caracter == '§'){
                constante = Double.parseDouble(cola.pop());
                pila.push(constante);
            }else if(caracter == '¶'){
                pila.push(-1d);
            }else if(Variables.esUnaVariable(caracter)){
                pila.push(caracter);
            }else if(Operadores.esUnOperador(caracter)){
                arbol = new Arbol();
                arbol.crear(caracter);
                arbol.insertar(pila.pop());
                arbol.insertar(pila.pop());
                
                pila.push(arbol.getRaiz());
            }
        }
        
        return (Hijo) pila.pop();
    }
    public static double ArbolaFuncion(Hijo<Object> arbolHijo, double x){
        Hijo<Object> aux = arbolHijo;
        char operador = 0;
        
        double res1 = 0f;
        double res2 = 0f;
        if(aux.valor instanceof Hijo){
            aux = (Hijo) aux.valor;
        }
        
        if(aux.valor instanceof Character){
            operador  = (Character) aux.valor;
        }else if(aux.valor instanceof Double){
            return (double) aux.valor;
        }
        if(Variables.esUnaVariable(operador)){
            return x;
        }
        res1 = ArbolaFuncion(aux.izquierda, x);
        res2 = ArbolaFuncion(aux.derecha, x);
        if(operador == 42){         //*
            return res1 * res2;
        }else if(operador == 43){   //+
            return res1 + res2;
        }else if(operador == 45){   //-
            return res1 - res2;
        }else if(operador == 47){   // /
            return res1 / res2;
        }else if(operador == 94){   //^
            return (double) MathLib.potencia(ArbolaFuncion(aux.derecha, x), (int) ArbolaFuncion(aux.izquierda, x));
        }
        
        return 0;
    }
}
