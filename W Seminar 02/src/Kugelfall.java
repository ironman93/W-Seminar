import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.*;
import javax.swing.Timer;


public class Kugelfall extends StandardAnwendung implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		starteAnwendung();
	}
	

	JLabel velocityLabel = new JLabel("velocity: 0");
	JLabel distanceLabel = new JLabel("distance: 0");
	JLabel accelerationLabel = new JLabel("acceleration: 9.81");
	

	JButton startButton = new JButton("Start");

	double t = 0;
	double v = 0;
	double dt = 0.017;
	double k = 0.5;
	double g = 9.81;
	double a = g;
	double m = 100;
	double x = 25;

	
	Color bg = new Color(100,100,100);
	Color ball = new Color(0,220,255);
	Timer timer = new Timer((int)(dt*1000), this);	
	
	
	public Kugelfall() {
		super("titel", 350, 500);
		this.setBackground(bg);

		startButton.addActionListener(this);
		add(startButton);
		startButton.setVisible(true);
		
		velocityLabel.setVisible(true);
		distanceLabel.setVisible(true);
		accelerationLabel.setVisible(true);
		GroupLayout telemetryLayout = new GroupLayout(fenster.getContentPane());
		telemetryLayout.setAutoCreateGaps(true);
		telemetryLayout.setAutoCreateContainerGaps(true);
		fenster.getContentPane().setLayout(telemetryLayout);
		telemetryLayout.setVerticalGroup(
				telemetryLayout.createSequentialGroup()
				.addComponent(startButton)
				.addComponent(velocityLabel)
				.addComponent(distanceLabel)
				.addComponent(accelerationLabel)
		);
		telemetryLayout.setHorizontalGroup(
				telemetryLayout.createParallelGroup(
						GroupLayout.Alignment.LEADING
				)
				.addComponent(startButton)
				.addComponent(accelerationLabel)
				.addComponent(velocityLabel)
				.addComponent(distanceLabel)
		);	
	}
	
	
	
	
	
	
	public void calcAccelaration() {
		a = g-(k/m)*v*v;
	}
	
	public void calcVelocity() {
		v = a*dt+v;
	}
	
	public void calcDistance() {
		x = v*dt+x;
	}
	
	public void fall() {
		calcAccelaration();
		calcVelocity();
		calcDistance();
		if(x > 550) {
			x = 10;
		}
		this.repaint();
		updateTelemetry();
	}
	
	public void updateTelemetry() {
		velocityLabel.setText("velocity: "+Math.round(v));
		distanceLabel.setText("distance: "+Math.round(x));
		accelerationLabel.setText("acceleration: " +Math.round(a));
		
	}
	
	@Override
	public void zeichne(Graphics2D g) {
		g.setColor(ball);
		g.fillOval(200, (int)x, 20, 20);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		add(velocityLabel);
		add(distanceLabel);
		add(accelerationLabel);
		fall();
	}
}
