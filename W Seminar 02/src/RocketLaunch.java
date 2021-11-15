import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
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
	int y_pos = 930;
	Color bg = new Color(100,100,100);
	Timer timer = new Timer((int)(dt*1000), this);
	JLabel velocityLabel = new JLabel("velocity: 0");
	JLabel heightLabel = new JLabel("distance: 0");
	JLabel accelerationLabel = new JLabel("acceleration: 9.81");
	
	public RocketLaunch() {
		super("titel", 250, 1000);
		this.setBackground(bg);
		
		
		JButton startButton = new JButton("start");
		//add(startButton);
		//startButton.setVisible(true);
		startButton.addActionListener(this);
		
		//startButton.setMaximumSize(new Dimension(100, 50));
		
		GroupLayout layout = new GroupLayout(fenster.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		fenster.getContentPane().setLayout(layout);
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(startButton)
				.addComponent(velocityLabel)
				.addComponent(heightLabel)
				.addComponent(accelerationLabel)
		);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(startButton)
				.addComponent(velocityLabel)
				.addComponent(heightLabel)
				.addComponent(accelerationLabel)
		);		

		setLayout(layout);
		startButton.setVisible(true);
		velocityLabel.setVisible(true);
		heightLabel.setVisible(true);
		accelerationLabel.setVisible(true);
		
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
		calcAcceleration();
		calcVelocity();
		calcHeight();
		calcMass();
		t = t + dt;
		y_pos = 930 - (int) (h * 0.01);
		//y_pos = (int)( h * 0.1);
		updateTelemetry();
		this.repaint();
	}
	
	public void updateTelemetry() {
		velocityLabel.setText("velocity: "+Math.round(v));
		heightLabel.setText("hoehe: "+Math.round(h));
		accelerationLabel.setText("acceleration: " +Math.round(a));
		
	}
	
	@Override
	public void zeichne(Graphics2D g) {
		g.fillOval(200, (int)y_pos, 20, 20);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		launch();
	}
}
