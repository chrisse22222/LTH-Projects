package inl3;

public class Complex {
	private double re;
	private double im;
	
	/** Skapar en komplex variabel med realdelen re och imagin�rdelen im */
	public Complex (double re, double im) {
		this.re = re;
		this.im = im;
	}
	
	public double getRe() {
		return re;
	}
	
	public double getIm() {
		return im;
	}
	
	/** Tar reda p� talets absolutbelopp i kvadrat */
	public double getAbs2() {
		double abs = Math.pow(re, 2) + Math.pow(im, 2);
		return abs;
	}
	
	/** Adderar det komplexa talet c till detta tal */
	public void add (Complex c) {
		re += c.getRe();
		im += c.getIm();
	}
	
	/** Multiplicerar detta tal med det komplexa talet */
	public void mul (Complex c) {
		double reMul = re * c.getRe() - im * c.getIm();
		im = im * c.getRe() + re * c.getIm();
		re = reMul;
	}
}
