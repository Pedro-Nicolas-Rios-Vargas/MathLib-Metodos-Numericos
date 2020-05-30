package traduccion;

import Lista.ListaCola;
import elementos.*;

import java.util.StringTokenizer;

public class Sustitucion {
    private StringTokenizer st;
    private StringBuilder sb;
    
    public Sustitucion(String ecuacion){
        st = new StringTokenizer(ecuacion, "+,-,*,/,^, (,)", true);
        sb = new StringBuilder(ecuacion.length());
    }
    
    public Object[] convertir(){
        String modulo = "";
        String token = "";
        String tokenAnterior = null;
        
        
        ListaCola<String> cola = new ListaCola<>();
        
        while(st.hasMoreTokens()){
            token = st.nextToken();
            
            if(token.length() == 1){ 
                /*  Se verifica si el token obtenido es de tamaño 1, esto es para
                    reducir los casos ya que solo puede haber 4 tipos de caracteres
                    con tamaño 1 en la ecuacion, "operadores, numero menor a 10, variables, parentesis"
                    por lo tanto si el token tiene tamaño uno se procede a verificar el valor de
                    ese caracter:
                
                */
                if(Operadores.esUnOperador(token.charAt(0))){
                    /*
                        Si el caracter unico del token resulta ser un operador se debera verificar
                        si el token anterior es un operador tambien, o un parentesis, esto con el fin
                        de saber si el operador se refiere a un valor negativo antes de una operacion
                        de resta.
                        
                        Si el token anterior no existe significa que estamos apenas en el primer caracter
                        del string, por lo tanto sabemos que esta afectando de forma negativa a un valor
                        no realizando la operacion de resta.
                    
                        En el caso de que el token anterior guarde un valor entonces debemos proceder a 
                        identificar el valor, si el token anterior es: "un operador, parentesis, constante o
                        variable"
                    */
                    if(tokenAnterior != null){
                        /*
                            Para verificar si el tokenAnterior es un operador, parentesis, un numero < 10 o
                            una variable se verifica su tamaño.
                        
                            Si el token anterior resulta ser un operador o un parentesis se procede a añadir
                            el token actual a un string llamado "modulo" el cual almacenara el operador y
                            si el token siguiente resulta ser un numero se guardara en el string "modulo"
                            y se procedera a guardar este string en una cola y sustituir el valor por un simbolo
                            "Â§" que simbolizara la existencia de un numero en ese sitio o por "Â¶" que simboliza
                            un -1 el caracter especial "Â¶" aparece unicamente cuando se va a multiplicar por -1
                            para hacer negativa una operacion.
                        */
                        if(tokenAnterior.length() == 1){
                            if(Operadores.esUnOperador(tokenAnterior.charAt(0)) ||
                            		Parentesis.esParentesisDeApertura(tokenAnterior.charAt(0))){
                                modulo += token;
                            }else{
                                sb.append(token);
                            }
                        }else{
                            sb.append(token);
                        }
                    }else{
                        modulo += token;
                    }
                    
                } //IF TOKEN IS A OPERATOR
                else if(Parentesis.esParentesis(token.charAt(0))){
                    if(tokenAnterior != null){
                        if(tokenAnterior.length() == 1){
                            if(Operadores.esUnOperador(tokenAnterior.charAt(0))){
                                if(!modulo.equals("")){
                                    if(modulo.contains("+")){
                                        modulo = "";
                                        sb.append(token);
                                    }else if(modulo.contains("-")){
                                        sb.append("¶*"+token);
                                        modulo = "";
                                    }
                                }else{
                                    sb.append(token);
                                }
                            }else{
                                sb.append(token);
                            }
                        }else{
                            sb.append(token);
                        }
                    }else{
                        sb.append(token);
                    }
                } //IF TOKEN IS A PARENTHESES BRAKETS
                else if(Variables.esUnaVariable(token.charAt(0))){
                    if(tokenAnterior != null){
                        if(tokenAnterior.length() == 1){
                            if(Operadores.esUnOperador(tokenAnterior.charAt(0))){
                                if(!modulo.equals("")){
                                    if(modulo.contains("+")){
                                        modulo = "";
                                        sb.append(token);
                                    }else if(modulo.contains("-")){
                                        sb.append("¶*"+token);
                                        modulo = "";
                                    }
                                }else{
                                    sb.append(token);
                                }
                            }else{
                                sb.append(token);
                            }
                        }else{
                            sb.append(token);
                        }
                    }else{
                        sb.append(token);
                    }
                } //IF TOKEN IS A VARIABLE
                else if(Constantes.esUnaConstante(token.charAt(0))){
                    if(tokenAnterior != null){
                        if(tokenAnterior.length() == 1){
                            if(Operadores.esUnOperador(tokenAnterior.charAt(0))){
                                if(!modulo.equals("")){
                                    if(modulo.contains("+")){
                                        modulo = "";
                                        cola.push(token);
                                        sb.append("§");
                                    }else if(modulo.contains("-")){
                                        modulo += token;
                                        cola.push(modulo);
                                        modulo = "";
                                        sb.append("§");
                                    }
                                }else{
                                    cola.push(token);
                                    sb.append("§");
                                }
                            }else{
                                cola.push(token);
                                sb.append("§");
                            }
                        }else{
                            sb.append(token);
                        }
                    }else{
                        cola.push(token);
                        sb.append("§");
                    }
                } //IF TOKEN IS A CONSTANT
            } //IF TOKEN LENGTH IS 1
            else if(token.length() > 1){
                try{
                    Double.parseDouble(token);
                    
                    if(!modulo.equals("")){
                        if(modulo.contains("+")){
                            modulo = "";
                            cola.push(token);
                            sb.append("§");
                        }else if(modulo.contains("-")){
                            modulo += token;
                            cola.push(modulo);
                            modulo = "";
                            sb.append("§");
                        }
                    }else{
                        cola.push(token);
                        sb.append("§");
                    }
                }catch(NumberFormatException nfE){
                    
                }
            } //IF TOKEN LENGTH IS BIGGER THAN 1
            tokenAnterior = token;
        } //FINAL CICLO WHILE
        return new Object[]{sb.toString(), cola};
    }
}
