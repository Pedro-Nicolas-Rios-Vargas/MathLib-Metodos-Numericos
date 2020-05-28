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
import Lista.Lista;
import excepciones.*;

public class Operadores {
    // * = 42 ; + = 43 ; - = 45 ; / = 47 ; ^ = 94
    
    public static boolean esValidoElOperador(char sucesor) throws EcuacionException{
        
        if(sucesor > 0){
            if(!(Parentesis.esParentesisDeApertura(sucesor) ||
                    Variables.esUnaVariable(sucesor) ||
                    Constantes.esUnaConstante(sucesor))){
                if(esUnOperador(sucesor) && !(jerarquia(sucesor) == 1))
                    throw new OperadorException("No se pueden realizar operaciones entre operadores");
                else if(Parentesis.esParentesisDeCierre(sucesor))
                    throw new ParentesisException("Parentesis de cierre operacion no valida");
            }
        }
        return true;
    }
    
    public static boolean esUnOperador(char caracter){
        // * = 42 ; + = 43 ; - = 45 ; / = 47 ; ^ = 94  
        if(caracter == 42 || caracter == 43 
                || caracter == 45 || caracter == 47 || caracter == 94)
            return true;
        return false;
    }
    
    public static boolean operadorMenor(char operadorEntrante, char operadorAlmacenado){
        int jerarquiaEntrante = jerarquia(operadorEntrante);
        int jerarquiaAlmacenado = jerarquia(operadorAlmacenado);
        
        if(jerarquiaEntrante <= jerarquiaAlmacenado)
            return true;
        
        return false;
    }
    
    public static int jerarquia(char operador){
        if(operador == 43 || operador == 45)
            return 1;
        if(operador == 42 || operador == 47)
            return 2;
        //retorna el valor del maximo exponente en la jerarquia de los operadores que seria la potencia o ^
        return 3;
    }
    
}
