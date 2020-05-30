/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

/**
 *
 * @author pnrv2
 */
import excepciones.*;
public class Parentesis {
    
    public static boolean esParentesis(char caracter){
        return caracter == 40 || caracter == 41;
    }
    
    public static boolean esParentesisDeApertura(char caracter){
        return caracter == 40;
    }
    
    public static boolean esParentesisDeCierre(char caracter){
        return caracter == 41;
    }
    
    public static boolean esValidoElParentesisDeApertura(String strAntecesor) throws EcuacionException{
    	char antecesor;
    	if(strAntecesor.length() == 1) {
    		antecesor = strAntecesor.charAt(0);
	        if(antecesor > 0){
	            if(!(esParentesisDeApertura(antecesor) || Operadores.esUnOperador(antecesor))){
	                
	                if(Variables.esUnaVariable(antecesor) || 
		                    Constantes.esUnaConstante(antecesor)){
	                	/*
	                    //AQUI ESPECIFICO SI EL CARACTER ES UN + O UN - NO LO TOME EN CUENTA YA QUE DEBE AFECTAR A UN OPERANDO
	                    if(!(Operadores.jerarquia(antecesor) == 1))
	                        throw new OperadorException("Existe un operador realizando una accion invalida");
                        */
	                	throw new EcuacionException("Variable o constante mal posicionada");
	                }else{
	                    throw new EcuacionException("Caracter invalido");
	                }
	            }
	        }
    	}else {
    		if(!ExpresionMatematica.esUnaExprecionMatematica(strAntecesor))
    			throw new EcuacionException("Caracter no valido");
    	}
        return true;
    }
    

    
    public static boolean esValidoElParentesisDeCierre(String strAntecesor) throws EcuacionException{
        char antecesor;
    	if(strAntecesor.length() == 1) {
    		antecesor = strAntecesor.charAt(0);
	        if(antecesor > 0){
	            if(!(esParentesisDeCierre(antecesor) ||
	            		Variables.esUnaVariable(antecesor) ||
	            		Constantes.esUnaConstante(antecesor))){
	                if(Operadores.esUnOperador(antecesor)){
	                    throw new OperadorException("Existe un operador realizando una operacion invalida");
	                }else{
	                    throw new EcuacionException("Caracter invalido");
	                }
	            }
	        }
        }else {
        	if(ExpresionMatematica.esUnaExprecionMatematica(strAntecesor))
        		throw new OperadorException("Expresion matematica mal posicionada");
        }
        return true;
    }
}
