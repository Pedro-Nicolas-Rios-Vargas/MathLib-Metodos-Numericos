package src;
//PEDRO NICOLAS RIOS VARGAS

import almacenamiento.Hijo;
import traduccion.Funcion;

public class MathLib extends MetodosNumericos{
	
	public final double PI = pi();
	public final double E = eulerElevado(1, false);
	private String unidadAngular;
	private Hijo funcion;
	private Hijo funcionDerivada;
	
	public MathLib(int cifrasSignificativas) {
		super(cifrasSignificativas);
	}
	
//CONSTRUCTOR PARA FUNCIONES TRIGONOMETRICAS
	public MathLib(int cifrasSignificativas, String unidadAngular) {
		super(cifrasSignificativas);
		this.unidadAngular = unidadAngular;
	}
	
//CONSTRUCTOR PARA INGRESO DE UNA FUNCION
	public MathLib(int cifrasSignificativas, Hijo funcion) {
		super(cifrasSignificativas);
		this.funcion = funcion;
	}
	
	public MathLib(int cifrasSignificativas, Hijo funcion, Hijo funcionDerivada) {
		super(cifrasSignificativas);
		this.funcion = funcion;
		this.funcionDerivada = funcionDerivada;
	}
	
	public static double suma(double aSumar, double sumado) {
		return aSumar + sumado;
	}
	
	public static double resta(double aRestar, double restado) {
		return aRestar - restado;
	}
	
	public static double multiplicacion(double multiplicado, double multiplicador) {
		return multiplicado * multiplicador;
	}
	
	public static double division(double dividendo, double divisor) {
		return dividendo / divisor;
	}
	
	public static double potencia(double valor, int potencia) {
		if(potencia == 0)
			return 1;
		double resultado = valor;
		boolean negativo = false;
		if(potencia < 0) {
			potencia *= -1;
			negativo = true;
		}
		
		for(int i = 1; i < potencia; i++) {
			resultado *= valor;
		}
		
		if(negativo) {
			resultado = 1/resultado;
		}
		return resultado;
	}
	
	public static double factorial(int factor) throws NumberFormatException{
		if(factor < 0)
			throw new NumberFormatException();
		if(factor == 0 || factor == 1)
			return 1;
		return factor * factorial(factor-1);
	}
	
	public double raizCuadrada(double d, boolean imprimir) throws MathLibException {
		if(d < 0)
			throw new MathLibException("SYNTAX ERROR: No se puede sacar raiz a un numero negativo");
		double xi = 1, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		if(imprimir)
			System.out.print("Iteracion\t xi \t\t xi+1 \t\t Er \n");
		while(true) {
			xiPlusOne = (1/2f) * (xi + (d/xi));
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(imprimir)
				System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \n", iteracion, xi, xiPlusOne, errorAproximado);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta) {
				break;
			}
		}
		return xiPlusOne;
	}
	
//FUNCIONES EXPONENCIALES
	public double eulerElevado(double potencia, boolean imprimir) {
		double xi = 0, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		if(imprimir)
			System.out.print("Iteracion\t xi \t\t xi+1 \t\t Er \n");
		while (true) {
			xiPlusOne = xi + (potencia(potencia, iteracion) / factorial(iteracion));
			
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(errorAproximado < 0)
				errorAproximado *= -1;
			if(imprimir)
				System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \n", iteracion, xi, xiPlusOne, errorAproximado);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta) {
				break;
			}
			
		}
		return xiPlusOne;
	}
	
	public double ln(double valor, boolean imprimir) throws MathLibException{
		if(valor == 0) {
			throw new MathLibException("SYNTAX ERROR: Logaritmo de 0 no es determinado");
		}else if(valor < 0) {
			throw new MathLibException("SYNTAX ERROR: Logaritmo de un numero negativo no es determinado");
		}
		double xi = 0, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		if(imprimir)
			System.out.print("Iteracion\t xi \t\t xi+1 \t\t Er \n");
		while(true) {
			xiPlusOne = xi + (1/((2f * iteracion) + 1)) * potencia(((valor - 1)/(valor + 1)), (2 * iteracion) + 1);
			
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(imprimir)
				System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \n", iteracion, xi, xiPlusOne, errorAproximado);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta)
				break;
		}
		return 2 * xiPlusOne;
	}
//FINAL FUNCIONES EXPONENCIALES
	
//FUNCIONES TRIGONOMETRICAS
	public double sin(double valor, boolean imprimir) {
		boolean mayoraPI = false; 
		if(unidadAngular.equals("D")) {
			if(valor >= 360) {
				valor = valor - (((int) valor / 360) * 360);
			}
			if(valor >= 180) {
				mayoraPI = true;
				valor -= 180;
			}else if(valor > 90) {
				valor = 180 - valor;
			}
			valor = (valor * PI) / 180f;
		}else if(unidadAngular.equals("R")) {
			if(valor >= (PI * 2)) {
				valor -= ((int) (valor / (2 * PI))) * (2 * PI);
			}
			if(valor >= PI) {
				mayoraPI = true;
				valor -= PI;
			}else if(valor > (PI / 2)) {
				valor = PI - valor;
			}
		}
		
		double xi = 0, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		if(imprimir)
			System.out.print("Iteracion\t xi \t\t xi+1 \t\t Er\n");
		while(true) {
			xiPlusOne = xi + (potencia(-1, iteracion)/factorial((2 * iteracion) + 1)) * potencia(valor, (2 * iteracion) +1);
			
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(errorAproximado < 0) {
				errorAproximado *= -1;
			}
			if(imprimir)
				System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \n", iteracion, xi, xiPlusOne, errorAproximado);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta)
				break;
		}
		if(mayoraPI && xiPlusOne != 0) {
			xiPlusOne *= -1;
		}
		return xiPlusOne;
	} //FINAL FUNCION SENO
	
	public double cos(double valor, boolean imprimir) {
		boolean mayoraPI = false;
		
		if(unidadAngular.equals("D")) {
			if(valor >= 360) {
				valor = valor - (((int) valor / 360) * 360);
			}
			if(valor >= 270) {
				
				valor = 360 - valor;
				
			}if(valor > 90 && valor < 270) {
				mayoraPI = true;
				if(valor > 90 && valor < 180) {
					valor = 180 - valor;
				}else {
					valor -= 180;
				}
			}
			valor *= PI / 180f;
		}else if(unidadAngular.equals("R")) {
			if(valor >= (PI * 2)) {
				valor -= ((int) (valor / (2 * PI))) * (2 * PI);
			}
			if(valor >= (3 * PI / 2)) {
				valor = 2 * PI - valor;
			}
			if(valor > (PI / 2) && valor < (3 * PI / 2)) {
				mayoraPI = true;
				if(valor > (PI / 2) && valor < PI) {
					valor = PI - valor;
				}else {
					valor -= PI;
				}
			}
		}
		double xi = 0, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		if(imprimir)
			System.out.println("Iteracion\t xi \t\t xi+1 \t\t Er\n");
		while(true) {
			xiPlusOne = xi + ((potencia(-1, iteracion) / factorial(2 * iteracion)) * potencia(valor, (2 * iteracion)));
			
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(errorAproximado < 0) {
				errorAproximado *= -1;
			}
			if(imprimir)
				System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \n", iteracion, xi, xiPlusOne, errorAproximado);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta)
				break;
		}
		if(mayoraPI && xiPlusOne != 0) {
			xiPlusOne *= -1;
		}
		return xiPlusOne;
	} //FINAL FUNCION COSENO
	
	public double tan(double valor, boolean imprimir) {
		double tangente = sin(valor, imprimir) / cos(valor, imprimir);
		if(tangente == 0) {
			tangente *= -1;
		}
		return tangente;
		
	}
	
	public double raizBiseccion(double x1, double x2) throws MathLibException{
		double fx1, fx2, xM = 0, xMPlusOne, fxM, compFuciones;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		
		fx1 = f(x1);
		fx2 = f(x2);
		
		if((fx1 * fx2) > 0) {
			throw new MathLibException("Ambas funciones son positivas");
		}
		System.out.println("Iteracion\t x1 \t\t x2 \t\t f(x1) \t\t xM \t\t f(xM) \t\t f(x1) * f(x2) \t\t Ea\n");
		while(true) {
			xMPlusOne = (x1 + x2) / 2;
			
			fxM= f(xMPlusOne);
		
			compFuciones = fx1 * fxM;
			errorAproximado = errorAproximado(xMPlusOne, xM);
			if(errorAproximado < 0)
				errorAproximado *= -1;
			System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \t %.5f \t %.5f \t %.5f \t %.5f \n", iteracion, x1, x2, fx1, xMPlusOne, fxM, compFuciones, errorAproximado);
			
			if(compFuciones > 0) {
				x1 = xMPlusOne;
				fx1 = fxM;
			}else if( compFuciones < 0) {
				x2 = xMPlusOne;
			}else{
				return xMPlusOne;
			}
			
			if(errorAproximado < errorMeta || iteracion == 8)
				return xMPlusOne;
			xM = xMPlusOne;
			iteracion++;
		} //FINAL CICLO WHILE
		
	} //FINAL METODO RAIZBISECCION
	
	public double incrementos(double xi, double deltaX, int q) {
		double xiPlusOne;
		double errorAproximado;
		double fx, fxLessOne = 0;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		System.out.println("Iteracion \t Δx \t\t xi \t\t f(xi) \t\t Ea\n");
		while(true) {
			fx = f(xi);
			errorAproximado = errorAproximado(fx, fxLessOne);
			if(errorAproximado < 0)
				errorAproximado *= -1;
			System.out.printf("%d \t\t %.5f \t %.5f \t%.5f \t %.5f\n", iteracion, deltaX, xi, fx, errorAproximado);
			if(fx < 0) {
				xi += deltaX;
				fxLessOne = fx;
			}else if(fx > 0) {
				deltaX /= q;
				xi = deltaX;
			}
			if(errorAproximado < errorMeta) {
				break;
			}
			
			
			iteracion++;
		}
		return xi;
		
	}
	
	public double newtonRaphson(double xi) {
		double xiPlusOne;
		double fx;
		double fxPrima;
		double errorAproximado;
		double errorMeta = errorMeta(cifrasSignificativas);
		int iteracion = 0;
		
		System.out.println("Iteracion \t xi \t\t f(x) \t\t f'(x) \t\t xi+1 \t\t Ea");
		while(true) {
			fx = f(xi);
			fxPrima = fPrima(xi);
			xiPlusOne = xi - (fx/fxPrima);
			errorAproximado = errorAproximado(xiPlusOne, xi);
			if(errorAproximado < 0)
				errorAproximado *= -1;
			System.out.printf("%d\t\t %.5f \t %.5f \t %.5f \t %.5f \t %.5f\n", iteracion, xi, fx, fxPrima, xiPlusOne, errorAproximado);
			
			if(errorAproximado < errorMeta || fxPrima == 0) {
				break;
			}
			
			iteracion++;
			xi = xiPlusOne;
		}
		
		return xiPlusOne;
		
	}
	
	private double f(double x) {
	/*
		FUNCION DEL SALON EJERCICIO 
		6x^3 - 5x^2 + 7x - 2
		return resta(suma( resta(multiplicacion(6, potencia(x, 3)),multiplicacion(5, potencia(x, 2))), multiplicacion(7, x)), 2);
	*/
	/*
	 	FUNCION PARA PARACAIDISTA
	 	resta(division(multiplicacion(multiplicacion(9.8, x), resta(1, eulerElevado(multiplicacion(-1,multiplicacion(division(16, x), 8))))), 16), 32)
	*/
	/*
		return (((9.8 * x) / 16) * (1 - eulerElevado(-(16/x) * 8))) - 32;
	*/
		return Funcion.ArbolaFuncion(funcion, x, this);
	}
	
	private double fPrima(double x) {
		return Funcion.ArbolaFuncion(funcionDerivada, x, this);
	}
	
	public void setFuncion(Hijo funcion) {
		this.funcion = funcion;
	}
	
	public void setFuncionDerivada(Hijo funcionDerivada) {
		this.funcionDerivada = funcionDerivada;
	}
	private double pi() {
		double xi = 0, xiPlusOne;
		double errorAproximado;
		double errorMeta = errorMeta(9);
		int iteracion = 0;
		while(true) {
			xiPlusOne = xi + ((potencia(2, iteracion) * potencia(factorial(iteracion), 2))/factorial((2 * iteracion) + 1));
			
			errorAproximado = errorAproximado(xiPlusOne, xi);
			iteracion++;
			xi = xiPlusOne;
			if(errorAproximado < errorMeta)
				break;
		}
		return 2 * xiPlusOne;
	}
	
	
}
