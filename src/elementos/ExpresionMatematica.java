package elementos;

import excepciones.EcuacionException;
import excepciones.OperadorException;

public class ExpresionMatematica {
	/*	sqrt = RaizCuadrada ; 
	 * 	e = Euler ;
	 *  ln = logaritmo neperiano
	 *  sin = seno
	 *  cos = coseno
	 *  tan = tangente
	 * */
	public static boolean esValidaLaExpresion(String str, String strAntecesor) throws EcuacionException{
		char antecesor;
		if(strAntecesor.length() == 1) {
			antecesor = strAntecesor.charAt(0);
			if(antecesor > 0) {
				if(str.equals("sqrt") || str.equals("ln") ||str.equals("sin") || str.equals("cos") ||
						str.equals("tan") || str.contentEquals("e")) {
						if(!(Parentesis.esParentesisDeApertura(antecesor) || Operadores.esUnOperador(antecesor))) {
							throw new OperadorException("No esta bien estructurada la expresion");
						}
				}
			}
		}else {
			throw new OperadorException("Expresion no valida, expresion antes de otra expresion");
		}
		return true;
	}
	
	public static boolean esUnaExprecionMatematica(String str) {
		if(str.equals("sqrt") || str.equals("e") || str.equals("ln")
				|| str.equals("sin") || str.equals("cos") ||
				str.equals("tan")) {
			return true;
		}
		return false;
	}
}
