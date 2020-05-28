package elementos;

import java.util.Arrays;
import java.util.StringTokenizer;
public class Funciones {
    private static final String CARACTERES = "elsincota";
    
    public static boolean esUnCaracterReservado(char caracter){
        if(CARACTERES.contains(Character.toString(caracter))){
            return true;
        }
        return false;
    }
    
    public static boolean funcionSeno(String ingreso){
        String resto;
        if(ingreso.contains("sin")){
            StringTokenizer st = new StringTokenizer(ingreso, "sin");
            resto = st.nextToken();
        }
        return false;
    }
}
