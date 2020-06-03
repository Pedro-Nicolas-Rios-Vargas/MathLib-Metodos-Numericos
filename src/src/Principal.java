package src;
//PEDRO NICOLAS RIOS VARGAS

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import src.MathLib;
import src.MathLibException;

import traduccion.*;

import almacenamiento.Hijo;

import Lista.ListaCola;
public class Principal {
	
	private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		//MathLib math = new MathLib(9);
		//System.out.println("El valor de pi: " + math.PI + "\n\n");
		try {
			menu();
		}catch(IOException ioE) {
			ioE.printStackTrace();
		}
	}
	
	private static void menu() throws IOException {
		MathLib math;
		float valor;
		String in;
		int cifrasSignificativas;
		
		
		boolean isDone = false;
		String entrada;
		int opc;
		while(!isDone) {
			System.out.println("FUNCIONES DE CALCULADORA");
			System.out.println("[1] Raiz Cuadrada");
			System.out.println("[2] e^x");
			System.out.println("[3] Logaritmo Neperiano");
			System.out.println("[4] Funciones Trigonometricas");
			System.out.println("[5] Metodo de Biseccion");
			System.out.println("[6] Salir");
			System.out.print("Que desea realizar? ");
			entrada = BR.readLine();
			if(validacionEntradaMenu(entrada) == 0)
				continue;
			opc = Integer.parseInt(entrada);
			
			switch(opc) {
//FUNCION RAIZ
				case 1:
					double raiz;	//raiz

					
					System.out.println("SOY UN SUPER PROGRAMA QUE RESULEVE RAICES");
					while(true) {
						System.out.print("A que valor quieres que le saque raiz? ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
						
					}
					
					math = new MathLib(cifrasSignificativas);
					//SACAR LA RAIZ DE UN VALOR X
					try {
					raiz = math.raizCuadrada(valor, true);

					System.out.printf("\nLa raiz de: \"%f\" es: %f\n\n", valor, raiz);
					}catch(MathLibException mlE) {
						System.out.println("\n" + mlE.getMessage() + "\n");
					}
					
					break;
//FUNCION e^X
				case 2:
					double euler;
					System.out.println("FUNCION \"e^x\"");
					while(true) {
						System.out.print("Ingrese el valor al que quiere elevar \"e\": ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					
					math = new MathLib(cifrasSignificativas);
					euler = math.eulerElevado(valor, true);
					System.out.printf("\nEuler Elevado al valor \"%f\" es: %f\n\n", valor, euler);
					break;
//FUNCION Ln
				case 3:
					double ln;
					System.out.println("FUNCION \"logaritmo neperiano/natural");
					while(true) {
						System.out.print("Ingrese el valor del que quiere encontra el logaritmo natural: ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException mfE) {
							System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					math = new MathLib(cifrasSignificativas);
					try {
						ln = math.ln(valor, true);
						System.out.printf("\nLogaritmo neperiano de \"%f\" es: %f\n\n", valor, ln);
					}catch(MathLibException mlE) {
						System.out.println("\n" + mlE.getMessage() + "\n");
					}
					
					break;
//FUNCIONES TRIGONOMETRICAS
					case 4:
						menuFunTrigonometricas();
						break;
//BISECCION
					case 5:
						double biseccion;
						double x1, x2;
						String resultado;
						boolean validacion = false;
						String ecuacion;
						
						Hijo arbol;
						
						System.out.println("FUNCION \"Raiz mas pequeña de una Funcion f(x)");
						
						while(true) {
							System.out.print("De cuanto quiere que sea la cifra Significativa? ");
							in = BR.readLine();
							try {
								cifrasSignificativas = Integer.parseInt(in);
								if(cifrasSignificativas < 0)
									throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
								
								break;
							}catch(MathLibException mlE) {
								System.out.println("\n" + mlE.getMessage() +"\n");
							}catch(NumberFormatException nfE) {
								System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
								continue;
							}
						} //FINAL WHILE INGRESO DE CIFRA SIGNIFICATIVA
						math = new MathLib(cifrasSignificativas, "D");
						
						while(true) {
							System.out.print("Ingrese la ecuacion: ");
							in = BR.readLine();
							Ecuacion ecu = new Ecuacion(in);
							
							validacion = ecu.esEcuacion();
							
							if(!validacion){
			                    System.out.printf("La ecuacion %s no es valida \n\n", in);
			                    continue;
			                }else{
			                    System.out.printf("\n\"La ecuacion %s es valida\" \n\n", in);
			                    
			                    Sustitucion sustituto = new Sustitucion(in);
			                    Object[] array = sustituto.convertir();
			                    //System.out.printf("\n\n %s \n\n", array[0]);
			                    
			                    ecuacion = in;
			                    in = array[0].toString();
			                    
			                //ENVIO DE LA ECUACION DESPUES DE EVALUACION
			                
			                    EcuacionesFijas postFija = new EcuacionesFijas();
			                    
			                    resultado = postFija.postFija(in);
			                    //System.out.println("Ecuacion Postfija: "+resultado+"\n\n");
			                    
			                    Funcion fdex = new Funcion(resultado, (ListaCola) array[1], (ListaCola) array[2]);
			                    arbol = fdex.postFijaaArbol();
			                    /*
			                    double res = fdex.ArbolaFuncion(arbol, 1, math);
			                    System.out.printf("El resultado de %s es: %f\n", ecuacion, res);
			                     */
			                    break;
			                }
						} //FINAL WHILE INGRESO DE FUNCION
						while(true) {
							System.out.print("Ingrese el limite inferior: ");
							in = BR.readLine();
							try {
								x1 = Float.parseFloat(in);
								break;
							}catch(NumberFormatException mfE) {
								System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
								continue;
							}
						} //FINAL WHILE INGRESO DE LIMITE INFERIOR
						while(true) {
							System.out.print("Ingrese el limite superior: ");
							in = BR.readLine();
							try {
								x2 = Float.parseFloat(in);
								break;
							}catch(NumberFormatException mfE) {
								System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
								continue;
							}
						} //FINAL WHILE INGRESO DE LIMITE SUPERIOR
						
						
						try {
							math.setFuncion(arbol);
							biseccion = math.raizBiseccion(x1, x2);
							System.out.printf("\nRaiz minima de la funcion \"%s\" con limite inferior \"%f\" y superior \"%f\" es: %.9f\n\n", ecuacion, x1, x2, biseccion);
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() + "\n");
						}
						break;
//SALIR
				case 6:
					isDone = true;
					break;
				default:
					System.out.println("\nOpcion no valida\n");
					break;
			} //FINAL SWITCH
			
		} //FINAL WHILE MENU
	} //FINAL METODO MENU
	
	public static void menuFunTrigonometricas() throws IOException {
		MathLib math;
		float valor;
		String in;
		int cifrasSignificativas;
		String unidadDeAngulo = "";
		
		boolean isDone = false;
		String entrada;
		int opc;
		
		unidadDeAngulo = seleccionUnidadDeAngulo();
		
		while(!isDone) {
			System.out.printf("FUNCIONES TRIGONOMETRICAS\t\"%s\"\n", unidadDeAngulo);
			System.out.println("[1] Seleccion unidadDeAngulo");
			System.out.println("[2] seno de x");
			System.out.println("[3] coseno de x");
			System.out.println("[4] tangente de x");
			System.out.println("[5] Salir");
			System.out.print("Que desea realizar? ");
			entrada = BR.readLine();
			if(validacionEntradaMenu(entrada) == 0)
				continue;
			opc = Integer.parseInt(entrada);
			switch(opc) {
//SELECCION DE UNIDAD ANGULAR
				case 1:
					unidadDeAngulo = seleccionUnidadDeAngulo();
					break;
//FUNCION SENO
				case 2:
					double sin;
					System.out.printf("FUNCION seno x\t\"%s\"\n", unidadDeAngulo);
					while(true) {
						System.out.print("Ingrese el valor para sacar seno: ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					math = new MathLib(cifrasSignificativas, unidadDeAngulo);
					sin = math.sin(valor, true);
					System.out.printf("El valor seno de \"%f\" en %s es : %f \n\n", valor, (unidadDeAngulo.equals("R")) ? "RADIANES" : "GRADOS", sin);
					break;
//FUNCION COSENO
				case 3:
					double cos;
					System.out.printf("FUNCION coseno x\t\"%s\"\n", unidadDeAngulo);
					while(true) {
						System.out.print("Ingrese el valor para sacar coseno: ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					math = new MathLib(cifrasSignificativas, unidadDeAngulo);
					cos = math.cos(valor, true);
					System.out.printf("El valor coseno de \"%f\" en %s es : %f \n\n", valor, (unidadDeAngulo.equals("R")) ? "RADIANES" : "GRADOS", cos);
					break;
//FUNCION TANGENTE
				case 4:
					double tan;
					System.out.printf("FUNCION tangente x\t\"%s\"\n", unidadDeAngulo);
					while(true) {
						System.out.print("Ingrese el valor para sacar la tangente: ");
						in = BR.readLine();
						try {
							valor = Float.parseFloat(in);
							break;
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de ingreso no valido. Favor de ingresar un valor numerico positivo\n");
							continue;
						}
					}
					
					while(true) {
						System.out.print("Cual quiere que sea el valor simbolico de redondeo? ");
						in = BR.readLine();
						try {
							cifrasSignificativas = Integer.parseInt(in);
							if(cifrasSignificativas < 0)
								throw new MathLibException("SYNTAX ERROR: Cifras Significativas no pueden ser negativas");
							
							break;
						}catch(MathLibException mlE) {
							System.out.println("\n" + mlE.getMessage() +"\n");
						}catch(NumberFormatException nfE) {
							System.out.println("\nValor de Ingreso no valido. Favor de ingreso un valor numerico positivo.\n");
							continue;
						}
					}
					math = new MathLib(cifrasSignificativas, unidadDeAngulo);
					tan = math.tan(valor, true);
					System.out.printf("El valor tangente de \"%f\" en %s es : %f \n\n", valor, (unidadDeAngulo.equals("R")) ? "RADIANES" : "GRADOS", tan);
					break;
//SALIR DEL MENU
				case 5:
					isDone = true;
					break;
				default:
					System.out.println("\nOpcion no valida\n");
					break;
			}
		}
	} //FINAL METODO MENU TRIGONOMETRICO
	
	private static String seleccionUnidadDeAngulo() throws IOException{
		String entrada, unidadDeAngulo = "";
		int opc;
		boolean isDone = false;
		while(!isDone) {
			System.out.println("\tGRADOS\t\tRADIANES");
			System.out.println("\t1\t\t\t2");
			System.out.print("En que unidad quiere el resultado? ");
			entrada = BR.readLine();
			if(validacionEntradaMenu(entrada) == 0)
				continue;
			opc = Integer.parseInt(entrada);
			switch(opc) {
				case 1:
					unidadDeAngulo = "D";
					isDone = true;
					break;
				case 2:
					unidadDeAngulo = "R";
					isDone = true;
					break;
				default:
					System.out.println("\nOpcion inexistente, seleccione una opcion valida\n");
					break;
			}
		}
		return unidadDeAngulo;
	}
	
	private static int validacionEntradaMenu(String entrada) {
		try {
			return Integer.parseInt(entrada);
		}catch(NumberFormatException nfE) {
			System.out.println("\nSolo numeros\n");
		}
		return 0;
	}
	
	
	

	
}
