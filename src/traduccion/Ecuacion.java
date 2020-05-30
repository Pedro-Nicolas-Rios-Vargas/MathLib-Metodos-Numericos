package traduccion;

import Lista.Lista;
import Lista.ListaCola;

import excepciones.ParentesisException;
import excepciones.EcuacionException;
import excepciones.OperadorException;

import java.util.StringTokenizer;

import elementos.*;
/**
 * La clase Ecuacion se encarga de verificar que la ecuacion haya sido introducida correctamente
 * 
 * */

public class Ecuacion {
    

    
    private String ecuacion;
    private int parentesisAperturaCont, parentesisCierreCont;
    private StringTokenizer st;
    private StringBuilder sb;
    
    public Ecuacion(String ecuacion){
        this.ecuacion = ecuacion;
        parentesisAperturaCont = 0;
        parentesisCierreCont = 0;
        
        st = new StringTokenizer(ecuacion, "+,-,*,/,^,(,)",true);
        sb = new StringBuilder(ecuacion.length());
        
    }
    
    public boolean esEcuacion() {
    	char caracter;
    	int index = 0;
    	String modulo = "";
    	String token = "";
    	String tokenAnterior = null;
    	ListaCola<String> cola = new ListaCola<>();
    	try {
	    	while(st.hasMoreTokens()) {
	    		token = st.nextToken();
	    		
	    		if(token.length() == 1) {
	    			caracter = token.charAt(0);
	    			
    			//EMPIEZA VALIDACION DE CARACTERES VALIDOS EN LA ECUACION
	                if(!(Parentesis.esParentesis(caracter) ||
	                        Variables.esUnaVariable(caracter) ||
	                        Operadores.esUnOperador(caracter) ||
	                        Constantes.esUnaConstante(caracter) ||
	                        ExpresionMatematica.esUnaExprecionMatematica(token)))
	                    throw new EcuacionException("La ecuacion contiene caracteres no validos");
	            //TERMINA VALIDACION DE CARACTERES VALIDOS EN LA ECUACION    
	        
	            //EMPIEZA VALIDACION DE PARENTESIS
	                if(Parentesis.esParentesis(caracter)){
	                    if((index == 0 && Parentesis.esParentesisDeCierre(caracter)) ||
	                            (st.hasMoreTokens() == false && Parentesis.esParentesisDeApertura(caracter)))
	                        throw new ParentesisException("Parentesis mal posicionado");
	                
                //INCREMENTO DE NUMERO DE PARENTESIS
                    	validacionParentesis(caracter, tokenAnterior);
	                }
	                if(parentesisAperturaCont < parentesisCierreCont)
	                    throw new ParentesisException("Parentesis mal posicionados");
                //TERMINA VALIDACION DE PARENTESIS
                //EMPIEZA VALIDACION DE OPERADORES
	                if(Operadores.esUnOperador(caracter)){
	                	if((index == 0 && Operadores.esUnOperador(caracter) && Operadores.jerarquia(caracter) != 1) ||
	                			(st.hasMoreTokens() == false && Operadores.esUnOperador(caracter)))
	                		throw new OperadorException("Operador mal posicionado");
	                    
	                
	                	validacionOperadores(tokenAnterior, caracter);
	                }
                //TERMINA VALIDACION DE OPERADORES
                //EMPIEZA VALIDACION DE VARIABLES
	                if(Variables.esUnaVariable(caracter)){
	                	validacionVariables(tokenAnterior);
	                }
                //TERMINA VALIDACION DE VARIABLES
        
                //EMPIEZA VALIDACION DE CONSTANTES
	                if(Constantes.esUnaConstante(caracter)){
	                	validacionConstantes(tokenAnterior);
	                }
                //TERMINA VALIDACION DE CONSTANTES
                //EMPIEZA VALIDACION DE EXPRESIONES MATEMATICAS
	                if(ExpresionMatematica.esUnaExprecionMatematica(token)) {
	                	validacionExpresionMat(token, tokenAnterior);
	                }
                //TERMINA VALIDACION DE EXPRESIONES MATEMATICAS
	    		}else {
    			//VALIDACION DE CADENAS
	    			if(!(Constantes.esUnaConstante(token) ||
	    					ExpresionMatematica.esUnaExprecionMatematica(token)))
	    				throw new EcuacionException("La ecuacion contiene caracteres no validos");
    			//FIN VALIDACION DE CADENAS
	    			
    			//VALIDACION DE CONSTANTES
	    			if(Constantes.esUnaConstante(token)) {
	    				validacionConstantes(tokenAnterior);
	    			}
    			//FIN VALIDACION DE CONSTANTES
    			//VALIDACION DE EXPRESIONES MATEMATICAS
	    			if(ExpresionMatematica.esUnaExprecionMatematica(token)) {
	    				validacionExpresionMat(token, tokenAnterior);
	    			}
    			//FIN VALIDACION DE EXPRESIONES MATEMATICAS
	    		}
	    		tokenAnterior = token;
	    		index++;
	    	}
	    	if(parentesisAperturaCont != parentesisCierreCont)
                throw new ParentesisException("Cierre incorrecto de parentesis");
    	}catch(EcuacionException ecuEx) {
    		System.out.println(ecuEx.getMessage());
    		//return new Object[] {false, null};
    		return false;
    	}
    	//return new Object[] {true,null};
    	return true;
    }
    /*
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
                    //validacionParentesis(caracter, sucesor);
                
                }
                if(parentesisAperturaCont < parentesisCierreCont)
                    throw new ParentesisException("Parentesis mal posicionados");
            //TERMINA VALIDACION DE PARENTESIS
                
            //EMPIEZA VALIDACION DE OPERADORES
                if(Operadores.esUnOperador(caracter)){
                    if((i == 0 && Operadores.esUnOperador(caracter) && Operadores.jerarquia(caracter) != 1) ||
                            (i == length-1 && Operadores.esUnOperador(caracter)))
                        throw new OperadorException("Operador mal posicionado");
                    
                
                    //validacionOperadores(sucesor);
                 }
            //TERMINA VALIDACION DE OPERADORES
        
            //EMPIEZA VALIDACION DE VARIABLES
                if(Variables.esUnaVariable(caracter)){
                    //validacionVariables(sucesor);
                }
            //TERMINA VALIDACION DE VARIABLES
        
            //EMPIEZA VALIDACION DE CONSTANTES
                if(Constantes.esUnaConstante(caracter)){
                    //validacionConstantes(sucesor);
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
    
    */
    private void validacionParentesis(char caracter, String antecesor) throws EcuacionException {
	        if(Parentesis.esParentesisDeApertura(caracter)){
	            if(antecesor != null) {
	            	if(Parentesis.esValidoElParentesisDeApertura(antecesor)) {
	            		parentesisAperturaCont++;
	            	}
	            }else
            		parentesisAperturaCont++;
	        }else{
	            if(antecesor != null) {
	            	if(Parentesis.esValidoElParentesisDeCierre(antecesor)){
	            		parentesisCierreCont++;
	            	}
	            }else
	            	parentesisCierreCont++;
	        }
        
        
    }
    
    private void validacionOperadores(String antecesor, char actual) throws EcuacionException{
    	if(antecesor != null)
    		if(Operadores.esValidoElOperador(antecesor, actual));
    }
    
    private void validacionVariables(String antecesor) throws EcuacionException{
    	if(antecesor != null)
    		if(Variables.esValidaLaVariable(antecesor));
    }
    
    private void validacionConstantes(String antecesor) throws EcuacionException{
        if(antecesor != null)
        	if(Constantes.esValidaLaConstante(antecesor));
    }
    
    private void validacionExpresionMat(String expresion, String antecesor) throws EcuacionException{
    	if(antecesor != null)
    		if(ExpresionMatematica.esValidaLaExpresion(expresion, antecesor));
    }
}
