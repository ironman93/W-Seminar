import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.*;


public class RocketLaunch extends StandardAnwendung implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		starteAnwendung();
	}
	
	double t = 0;
	double v = 0;
	double dt = 0.042;
	//double dt = 1;
	double g = 9.81;
	double a = 0;
	int thrust = 34000000;
	double m = 2900000;
	double h = 0;
	double twr = 0;
	int y_pos = 930;
	Color bg = new Color(100,100,100);
	Timer timer = new Timer((int)(dt*1000), this);
	JLabel velocityLabel = new JLabel("velocity: 0");
	JLabel heightLabel = new JLabel("height: 0");
	JLabel accelerationLabel = new JLabel("acceleration: 0");
	JLabel twrLabel = new JLabel("twr: 0");
	
	public RocketLaunch() {
		super("titel", 250, 1000);
		this.setBackground(bg);
		setLayout(null);
		
		JButton startButton = new JButton("start");
		startButton.addActionListener(this);	
		
		add(startButton);
		add(velocityLabel);
		add(heightLabel);
		add(accelerationLabel);
		add(twrLabel);
		
		startButton.setLocation(20, 10);
		startButton.setSize(55,20);
		velocityLabel.setLocation(20, 40);
		velocityLabel.setSize(100,20);
		heightLabel.setLocation(20, 60);
		heightLabel.setSize(100,20);
		accelerationLabel.setLocation(20, 80);
		accelerationLabel.setSize(100,20);
		twrLabel.setLocation(20, 100);
		twrLabel.setSize(100, 20);
		
		startButton.setVisible(true);
		velocityLabel.setVisible(true);
		heightLabel.setVisible(true);
		accelerationLabel.setVisible(true);
		twrLabel.setVisible(true);
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
	
	void calcTwr() {
		twr = thrust/(m*10);
	}
	
	void launch() {
		calcAcceleration();
		calcVelocity();
		calcHeight();
		calcMass();
		calcTwr();
		t = t + dt;
		y_pos = 930 - (int) (h * 0.01);
		updateTelemetry();
		this.repaint();
	}
	
	public void updateTelemetry() {
		velocityLabel.setText("velocity: "+Math.round(v));
		heightLabel.setText("height: "+Math.round(h));
		accelerationLabel.setText("acceleration: " + (double)Math.round(a * 100d) / 100d);
		twrLabel.setText("twr: " + (double)Math.round(twr * 100d) / 100d);
	}
	
	@Override
	public void zeichne(Graphics2D g) {
		g.fillOval(150, (int)y_pos, 20, 20);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		launch();
	}
}
