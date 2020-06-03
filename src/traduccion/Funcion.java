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
import src.MathLibException;

public class Funcion {
    
    private String ecuacionPostFija;
    private ListaCola<String> colaConstantes;
    private ListaCola<String> colaFunciones;
    
    public Funcion(String ecuacionPostFija, ListaCola<String> colaConstantes, ListaCola<String> colaFunciones){
        this.ecuacionPostFija = ecuacionPostFija;
        this.colaConstantes = colaConstantes;
        this.colaFunciones = colaFunciones;
    }
    
    public Hijo postFijaaArbol(){
        Pila<Object> pila = new Pila<>(ecuacionPostFija.length());
        Arbol arbol;
        char caracter = 0;
        double constante;
        String funcion;
        for(int i = 0; i < ecuacionPostFija.length(); i++){
            caracter = ecuacionPostFija.charAt(i);
            //245 = Â§ = CONSTANTE | 244 = Â¶ = -1 | 207 = ¤ = EXPRESION MATEMATICA
            if(caracter == '§'){
                constante = Double.parseDouble(colaConstantes.pop());
                pila.push(constante);
            }else if(caracter == '¶'){
                pila.push(-1d);
                
            }else if(caracter == '¤') {
            	funcion = colaFunciones.pop();
            	
            	arbol = new Arbol();
            	arbol.crear(funcion);
            	arbol.insertar(pila.pop());
            	
            	pila.push(arbol.getRaiz());
            	
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
    public static double ArbolaFuncion(Hijo<Object> arbolHijo, double x, MathLib math){
        
    	Hijo<Object> aux = arbolHijo;
        char operador = 0;
        String funcion = "";
        double res1 = 0f;
        double res2 = 0f;
        if(aux.valor instanceof Hijo){
            aux = (Hijo) aux.valor;
        }
        
        if(aux.valor instanceof Character){
            operador  = (Character) aux.valor;
        }else if(aux.valor instanceof Double){
            return (double) aux.valor;
        }else if(aux.valor instanceof String) {
        	funcion = (String) aux.valor;
        	if(funcion.equals("sqrt")) {
        		try {
					return math.raizCuadrada(ArbolaFuncion(aux.derecha, x, math), false);
				} catch (MathLibException e) {
					System.out.println("Error al momento de calcular la raiz cuadrada");
				}
        	}else if(funcion.equals("ln")){
        		try {
        			return math.ln(ArbolaFuncion(aux.derecha,x,math), false);
        		}catch(MathLibException e) {
        			System.out.println("Error al momento de calcular ln");
        		}
        	}else if(funcion.equals("sin")) {
        		return math.sin(ArbolaFuncion(aux.derecha,x,math), false);
        	}else if(funcion.equals("cos")) {
        		return math.cos(ArbolaFuncion(aux.derecha,x,math), false);
        	}else if(funcion.equals("tan")) {
        		return math.tan(ArbolaFuncion(aux.derecha,x,math), false);
        	}else if(funcion.equals("e")) {
        		return math.eulerElevado(ArbolaFuncion(aux.derecha,x,math), false);
        	}
        }
        if(Variables.esUnaVariable(operador)){
            return x;
        }
        res1 = ArbolaFuncion(aux.izquierda, x, math);
        res2 = ArbolaFuncion(aux.derecha, x, math);
        if(operador == 42){         //*
            return res1 * res2;
        }else if(operador == 43){   //+
            return res1 + res2;
        }else if(operador == 45){   //-
            return res1 - res2;
        }else if(operador == 47){   // /
            return res1 / res2;
        }else if(operador == 94){   //^
        	return (double) MathLib.potencia(res1, (int) res2);
            //return (double) MathLib.potencia(ArbolaFuncion(aux.izquierda, x), (int) ArbolaFuncion(aux.derecha, x));
        }
        
        return 0;
    }
}
