import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.*;
import javax.swing.Timer;


public class RocketLaunch extends StandardAnwendung implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		starteAnwendung();
	}
	
	double t = 0;
	double v = 0;
	double dt = 0.017;
	double g = 9.81;
	double a = 0;
	double thrust = 34000000;
	double m = 2900000;
	double h = 0;
	int y_pos = 475;
	Color bg = new Color(100,100,100);
	
	public RocketLaunch() {
		super("titel", 250, 500);
		this.setBackground(bg);
		JButton startButton = new JButton("start");
		add(startButton);
		startButton.setVisible(true);
		startButton.addActionListener(this);
	}
	
	void calcAcceleration(){
		a = (thrust - (m*g))/m;
	}
	
	void calcVelocity() {
		v = a*dt+v;
	}
	
	void calcHeight() {
		h = v*dt+h;
	}
	
	void calcMass(){
		m = m-(14000*dt);
	}
	
	void launch() {
		System.out.println(h);
		calcAcceleration();
		calcVelocity();
		calcHeight();
		calcMass();
		t = t + dt;
		y_pos = y_pos - (int)h;
		
		this.repaint();
		if (t<160) {
			launch();
		}
	}
	
	@Override
	public void zeichne(Graphics2D g) {
		g.fillOval(50, (int)y_pos, 20, 20);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		launch();
	}
}
