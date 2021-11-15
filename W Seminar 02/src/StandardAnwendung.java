import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * Von JPanel abgeleitete Klasse zur Vereinfachung der Informatik Aufgaben. 
 * 
 * Abgeleitete Klassen haben eine kurze main-Methode
 * und einen Standardkonstruktor:
 * 
   class Beispiel extends StandardAnwendung{
       public static void main(String[] a){
            starteAnwendung();
       }
       public Beispiel(){
       	    super("Beispiel",500,400); // legt Titel und Groesse des Fensters fest
       	    
            // HIER startet die eigentliche Anwendung
       }
   }
 */
public abstract class StandardAnwendung extends JPanel {
	
	protected JFrame fenster;      //  Abgeleiteten Klassen koennnen auf das Fenster der Anwendung zugreifen
	                               //  z.B. fenster.setResizable(false)
	
	/**
	 *  Startet einen Thread in dem ein Objekt der Hauptklasse erzeugt wird.   
	 */
	protected static void starteAnwendung(){		
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		String hauptKlasseName = trace[trace.length-1].getClassName();
		// Swing-Anwendungen muessen in einem eigenen Thread laufen
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Class<?> hauptKlasse = Class.forName(hauptKlasseName);
					hauptKlasse.getConstructor().newInstance();
				} catch (NoSuchMethodException e){
					JOptionPane.showMessageDialog(null, "<html>Ihre Klasse <strong>"+hauptKlasseName+
							                            "</strong> hat keinen oeffentlichen Standardkonstruktor,"+
							                            "\n deshalb kann die Anwendung nicht gestartet werden!", 
							                            "Bittte korrigieren Sie Ihr Programm",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected StandardAnwendung(String titel, int b, int h){
		this.fenster = new JFrame(titel);	
		this.fenster.setSize(b, h);
		fenster.setLocation(MouseInfo.getPointerInfo().getLocation());
		this.fenster.getContentPane().add(this);
		
		this.fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenster.setVisible(true);
	}
	
	public void zeichne(Graphics2D g) {		
		// kann von abgeleiteten Klassen ueberschrieben werden um zu zeichnen
	}	

	@Override
	public final void paintComponent(Graphics g) { // final verhindert das ueberschreiben in abgeleiteten Klassen
		super.paintComponent(g);                   // zum Zeichnen verwenden abgeleitet Klassen die Methode zeichne.
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform merk = g2.getTransform();
		this.zeichne(g2);
		g2.setTransform(merk);
	}
	
	@Override
	public final void paint(Graphics g) {  // verhindert, dass abgeleitete Klassen die Methode ueberschrieben wird,
		super.paint(g);                    // (damit nicht paint statt paintComponent gerufen wird).
	}
}