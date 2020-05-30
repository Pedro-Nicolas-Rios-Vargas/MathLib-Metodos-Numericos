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
public class Variables {
    
    public static boolean esValidaLaVariable(String strAntecesor) throws EcuacionException, VariableException, ConstanteException, OperadorException, ParentesisException{
    	char antecesor;
    	if(strAntecesor.length() == 1) {
    		antecesor = strAntecesor.charAt(0);
	        if(antecesor > 0){
	            if(!(Parentesis.esParentesisDeApertura(antecesor) || Operadores.esUnOperador(antecesor))){
	                if(Variables.esUnaVariable(antecesor))
	                    throw new VariableException("Accion no valida entre variables");
	                else if(Constantes.esUnaConstante(antecesor))
	                    throw new ConstanteException("Accion no valida entre variable y constante");
	                else if(ExpresionMatematica.esUnaExprecionMatematica(strAntecesor))
	                	throw new OperadorException("Expresion matematica mal posicionada");
	                else if(Parentesis.esParentesisDeCierre(antecesor))
	                	throw new ParentesisException("Parentesis de cierre mal posicionado");
	            }
	        }
    	}else {
    		if(!ExpresionMatematica.esUnaExprecionMatematica(strAntecesor))
    			throw new EcuacionException("Caracteres no validos");
			else
    			throw new OperadorException("Expresion matematica mal posicionada");
    	}
        return true;
    }
    
    public static boolean esUnaVariable(char caracter){
        if(((caracter >= 65 && caracter < 69) || (caracter > 69 && caracter <= 90)) ||
                ((caracter >= 97 && caracter < 101) || (caracter > 101 && caracter <= 122)) ||
                (caracter == 164 || caracter == 165)){
            
            return true;
            
        }
        return false;
    }
    
}
