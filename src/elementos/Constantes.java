/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import excepciones.OperadorException;
import excepciones.ParentesisException;
import excepciones.VariableException;

public class Constantes {
    
	public static boolean esUnaConstante(String constant) {
		try {
			Double.parseDouble(constant);
			return true;
		}catch(NumberFormatException nfE) {
			
		}
		return false;
	}
	
    public static boolean esUnaConstante(char caracter){
        if(caracter >= 48 && caracter <= 57)
            return true;
        return false;
    }
    
    public static boolean esValidaLaConstante(String strAntecesor) throws OperadorException, VariableException, ParentesisException{
    	char antecesor;
    	if(strAntecesor.length() == 1) {
    		antecesor = strAntecesor.charAt(0);
	        if(!(Parentesis.esParentesisDeApertura(antecesor) || Operadores.esUnOperador(antecesor))){
	            /*if(esUnaConstante(sucesor))
	                throw new OperadorException("Accion no valida entre constantes");
	            else */
	        	if(Variables.esUnaVariable(antecesor)) {
	        		
        			throw new VariableException("Accion no valida entre variable y constante");
        			
	        	}else if(ExpresionMatematica.esUnaExprecionMatematica(strAntecesor)) {
	        		
                	throw new OperadorException("Expresion matematica mal posicionada");
                	
	        	}else if(Parentesis.esParentesisDeCierre(antecesor)) {
	        		
    				throw new ParentesisException("Parentesis de cierre mal posicionado");
    				
	        	}
	        }
    	}else {
    		throw new OperadorException("Expresion matematica mal posicionada");
    	}
        return true;
    }
}
