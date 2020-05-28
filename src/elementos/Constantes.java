/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import excepciones.OperadorException;
import excepciones.VariableException;

public class Constantes {
    
    public static boolean esUnaConstante(char caracter){
        if(caracter >= 48 && caracter <= 57)
            return true;
        return false;
    }
    
    public static boolean esValidaLaConstante(char sucesor) throws OperadorException, VariableException{
        if(!(Parentesis.esParentesisDeCierre(sucesor) || Operadores.esUnOperador(sucesor))){
            /*if(esUnaConstante(sucesor))
                throw new OperadorException("Accion no valida entre constantes");
            else */if(Variables.esUnaVariable(sucesor))
                throw new VariableException("Accion no valida entre variable y constante");
        }
        return true;
    }
}
