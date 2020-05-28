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
    
    public static boolean esValidoElParentesisDeApertura(char sucesor) throws EcuacionException{
        if(sucesor > 0){
            if(!(esParentesisDeApertura(sucesor) || Variables.esUnaVariable(sucesor) || 
                    Constantes.esUnaConstante(sucesor))){
                
                if(Operadores.esUnOperador(sucesor)){
                    //AQUI ESPECIFICO SI EL CARACTER ES UN + O UN - NO LO TOME EN CUENTA YA QUE DEBE AFECTAR A UN OPERANDO
                    if(!(Operadores.jerarquia(sucesor) == 1))
                        throw new OperadorException("Existe un operador realizando una accion invalida");
                }else{
                    throw new EcuacionException("Caracter invalido");
                }
            }
        }
        return true;
    }
    

    
    public static boolean esValidoElParentesisDeCierre(char sucesor) throws EcuacionException{
                
        if(sucesor > 0){
            if(!(esParentesisDeCierre(sucesor) || Operadores.esUnOperador(sucesor))){
                if(Variables.esUnaVariable(sucesor)){
                    throw new VariableException("Existe una variable realizando una operacion invalida");
                }else if(Constantes.esUnaConstante(sucesor)){
                    throw new ConstanteException("Existe una constante realizando una operacion invalida");
                }else{
                    throw new EcuacionException("Caracter invalido");
                }
            }
        }
        return true;
    }
}
