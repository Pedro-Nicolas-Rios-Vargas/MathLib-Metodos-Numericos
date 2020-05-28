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
    
    public static boolean esValidaLaVariable(char sucesor) throws VariableException, ConstanteException{
        if(sucesor > 0){
            if(!(Parentesis.esParentesisDeCierre(sucesor) || Operadores.esUnOperador(sucesor))){
                if(Variables.esUnaVariable(sucesor))
                    throw new VariableException("Accion no valida entre variables");
                else if(Constantes.esUnaConstante(sucesor))
                    throw new ConstanteException("Accion no valida entre variable y constante");
            }
        }
        return true;
    }
    
    public static boolean esUnaVariable(char caracter){
        if((caracter >= 65 && caracter <= 90) ||
                (caracter >= 97 && caracter <= 122 ||
                (caracter == 164 || caracter == 165))){
            
            return true;
            
        }
        return false;
    }
    
}
