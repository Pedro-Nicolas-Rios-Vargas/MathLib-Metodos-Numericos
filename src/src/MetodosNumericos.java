package src;
//PEDRO NICOLAS RIOS VARGAS

public class MetodosNumericos {
	
	protected static int cifrasSignificativas;
	public MetodosNumericos() {
		cifrasSignificativas = 0;
	}
	
	public MetodosNumericos(int cifrasSignificativas) {
		this.cifrasSignificativas = cifrasSignificativas;
	}
	
//CALCULO DE ERROR
	
	public double errorAproximado(double valorVerdadero, double valorAproximado) {
		double valorAbsoluto = valorVerdadero - valorAproximado;
		if(valorAbsoluto < 0) {
			valorAbsoluto *= -1;
		}
		if(valorAbsoluto == 0 && valorVerdadero == 0)
			return 0;
		return (valorAbsoluto/valorVerdadero) *100f;
	}
	
	public double errorMeta(int cifrasSignificativas) {
		return 0.5 * MathLib.potencia(10f, 2 - cifrasSignificativas);
	}
	
	//getters & setters
	
	public void setCifrasSignificativas(int cifrasSignificativas) {
		this.cifrasSignificativas = cifrasSignificativas;
	}
	
	public int getCifrasSignificativas() {
		return cifrasSignificativas;
	}
	
	//CONSTANTES
		//public double EULER = eulerElevado(1);
	
}
