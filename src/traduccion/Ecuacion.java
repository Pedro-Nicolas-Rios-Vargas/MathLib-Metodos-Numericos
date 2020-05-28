package traduccion;

import Lista.Lista;
import excepciones.ParentesisException;
import excepciones.EcuacionException;
import excepciones.OperadorException;

import elementos.*;
/**
 * La clase Ecuacion se encarga de verificar que la ecuacion haya sido introducida correctamente
 * 
 * */

public class Ecuacion {
    

    
    private String ecuacion;
    private int parentesisAperturaCont, parentesisCierreCont;
    
    public Ecuacion(String ecuacion){
        this.ecuacion = ecuacion;
        parentesisAperturaCont = 0;
        parentesisCierreCont = 0;
    }
    
    public boolean esEcuacion(){
        char caracter = 0;
        char sucesor = 0;
        int length = ecuacion.length();
        //validacion de contenido
        try{
            for(int i = 0; i < length; i++){
                caracter = ecuacion.charAt(i);
                
                if(i+1 < length){
                    sucesor = ecuacion.charAt(i+1);
                }else{
                    sucesor = 0;
                }
            //EMPIEZA VALIDACION DE CARACTERES VALIDOS EN LA ECUACION
                if(!(Parentesis.esParentesis(caracter) ||
                        Variables.esUnaVariable(caracter) ||
                        Operadores.esUnOperador(caracter) ||
                        Constantes.esUnaConstante(caracter)))
                    throw new EcuacionException("La ecuacion contiene caracteres no validos");
            //TERMINA VALIDACION DE CARACTERES VALIDOS EN LA ECUACION    
        
            //EMPIEZA VALIDACION DE PARENTESIS
                if(Parentesis.esParentesis(caracter)){
                    if((i == 0 && Parentesis.esParentesisDeCierre(caracter)) ||
                            (i == length-1 && Parentesis.esParentesisDeApertura(caracter)))
                        throw new ParentesisException("Parentesis mal posicionado");
                
                
                //INCREMENTO DE NUMERO DE PARENTESIS
                    validacionParentesis(caracter, sucesor);
                
                }
                if(parentesisAperturaCont < parentesisCierreCont)
                    throw new ParentesisException("Parentesis mal posicionados");
            //TERMINA VALIDACION DE PARENTESIS
        
            //EMPIEZA VALIDACION DE OPERADORES
                if(Operadores.esUnOperador(caracter)){
                    if((i == 0 && Operadores.esUnOperador(caracter) && Operadores.jerarquia(caracter) != 1) ||
                            (i == length-1 && Operadores.esUnOperador(caracter)))
                        throw new OperadorException("Operador mal posicionado");
                    
                
                    validacionOperadores(sucesor);
                 }
            //TERMINA VALIDACION DE OPERADORES
        
            //EMPIEZA VALIDACION DE VARIABLES
                if(Variables.esUnaVariable(caracter)){
                    validacionVariables(sucesor);
                }
            //TERMINA VALIDACION DE VARIABLES
        
            //EMPIEZA VALIDACION DE CONSTANTES
                if(Constantes.esUnaConstante(caracter)){
                    validacionConstantes(sucesor);
                }
            //TERMINA VALIDACION DE CONSTANTES
                
            }
            
            if(parentesisAperturaCont != parentesisCierreCont)
                throw new ParentesisException("Cierre incorrecto de parentesis");
        }catch(EcuacionException eE){
            System.out.println(eE.getMessage());
            return false;
        }
        return true;
    }
    
    private void validacionParentesis(char caracter, char sucesor) throws EcuacionException {
        if(Parentesis.esParentesisDeApertura(caracter)){
            if(Parentesis.esValidoElParentesisDeApertura(sucesor))
                parentesisAperturaCont++;
            
        }else{
            if(Parentesis.esValidoElParentesisDeCierre(sucesor))
                parentesisCierreCont++;
        }
        
        
    }
    
    private void validacionOperadores(char sucesor) throws EcuacionException{
        if(Operadores.esValidoElOperador(sucesor));
    }
    
    private void validacionVariables(char sucesor) throws EcuacionException{
        if(Variables.esValidaLaVariable(sucesor));
    }
    
    private void validacionConstantes(char sucesor) throws EcuacionException{
        if(Constantes.esValidaLaConstante(sucesor));
    }
}
