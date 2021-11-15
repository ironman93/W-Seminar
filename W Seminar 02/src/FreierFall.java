
public class FreierFall {

	double y = 0, v = 0;
	double g = 9.81;
	double a = g;
	double t = 0;
	double deltaT = 0.05;
	double k = 0.6, m = 100;
	public FreierFall() {
		// TODO Auto-generated constructor stub
	}
	
	public void berechneSchritt()
	{
		
		v += a*deltaT;
		t+=deltaT;
		y+=v*deltaT;
		a = g - k*v*v/m;
		
	}
	
	public void berechenGesamt(int schritte)
	{
		System.out.println("Zeit: "+round(t,2)+" Beschleunigung: "+round(a,2)+" Geschwindigkeit: "+round(v,2)
				+" Ort: "+round(y,2));
		for(int i = 0;i<schritte;i++)
		{
			berechneSchritt();
			System.out.println("Zeit: "+round(t,2)+" Beschleunigung: "+round(a,2)+" Geschwindigkeit: "+round(v,2)
			+" Ort: "+round(y,2));
		}
	}

	double getY() {
		return y;
		
	}
	
	double getV()
	{
		return v;
	}
	
	double getA()
	{
		return a;
	}
	
	double getT()
	{
		return t;
	}
	
	private double round(double value, int decimalPoints) {
	      double d = Math.pow(10, decimalPoints);
	      return Math.round(value * d) / d;
	   }
	
	public static void main(String[] args)
	{
		FreierFall ff = new FreierFall();
		ff.berechenGesamt(100);
	}
}

