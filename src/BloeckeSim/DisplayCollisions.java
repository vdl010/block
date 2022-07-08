package BloeckeSim;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;

@SuppressWarnings("serial")

/*---------------------DisplayCollisions---------------------*/

//  In der Klasse DisplayCollision erstellen wir das JPanel (createAndShowGUI), welches beim Dr�cken des Start Buttons vom MainWindow, mit dem EingabePanel getauscht wird.
//  Auf dem Panel (createAndShowGUI) findet die Simulation statt. 
//  Es enth�lt den Hintergrund, den Boden, die Wand, links oben eine wei�e Schrift mit der Kollisionsanzahl und die beiden Massebl�cke.
//  �ber die ScreenSize wird der Startpunkt der Massen und der Anschlagspunkt an der Wand festgelegt.

/*-----------------------------------------------------------*/

public class DisplayCollisions extends JComponent { 

	/*---------------------Bildschirmgr��e bekommen------------------------*/
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/*---------------------Bildschirmaufl�sung ausgeben------------------------*/
	
	private double height = screenSize.getHeight(); 
	private double width = screenSize.getWidth(); 

	/*---------------------Fenstergr��e------------------------*/
	
	private int windHeight; 
	private int windWidth; 

	/*---------------------Externes Bild im RAM (Bl�cke)------------------------*/
	
	private BufferedImage bi; 
	
	/*---------------------Bildinhalt (Hintergrund)(Notwendig f�r BufferedImage)------------------------*/
	
	private Graphics bg; 

	/*---------------------Bl�cke------------------------*/
	
	private Blocks blocks; 

	/*---------------------JPanel------------------------*/
	
	private JPanel panel; 

	public DisplayCollisions(int width, int height, Blocks blocks) {

		/*---------------------Externes Bild erstellen------------------------*/
		
		bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		
		/*---------------------Bildinhalt------------------------*/
		
		bg = bi.getGraphics(); 
		
		/*---------------------Bl�cke setzen------------------------*/
		
		this.blocks = blocks; 

		/*---------------------Fenstergr��e / Fensterbreite------------------------*/
		
		windHeight = height; 
		windWidth = width; 

		/*---------------------GUI------------------------*/
		
		this.panel = createAndShowGUI(); 
	}

	public JPanel getPanel() {
		
		/*---------------------Panel zur�ckgeben --> Zum in das Fenster einbinden------------------------*/
		
		return this.panel; 
	}

	private void paintBlocks() { 

		/*---------------------Boden festlegen (Auf dem die Bl�cke sich bewegen)------------------------*/
		
		int bottom = windHeight - 50;
		
		/*---------------------Bl�cke Gr��e------------------------*/
		
		int size1 = 30;
		int size2 = 90;

		/*---------------------Bl�cke Farbe------------------------*/
		
		bg.setColor(new Color(40, 180, 180));
		
		/*---------------------Abstand Block Wand------------------------*/
		
		int wallAndFirstBlockWidth = 50 + size1;
		
		/*---------------------Bl�cke Position------------------------*/
		
		bg.fillRect((int) blocks.getPosition1() - size1 + wallAndFirstBlockWidth, bottom - size1, size1, size1);
		bg.fillRect((int) blocks.getPosition2() + wallAndFirstBlockWidth, bottom - size2, size2, size2);
	}

	private void paintBackground() { 

		/*---------------------Farbe Hintergrund------------------------*/
		
		bg.setColor(new Color(40, 40, 40)); 
		bg.fillRect(0, 0, (int) width, (int) height);

		/*---------------------Farbe / Boden / Linke Wand------------------------*/
		
		bg.setColor(new Color(20, 20, 20)); 
		bg.fillRect(0, windHeight - 50, windWidth, 50);
		bg.fillRect(0, 0, 50, windHeight);

		/*---------------------Farbe Schrift (Kollisionen)------------------------*/
		
		bg.setColor(new Color(223, 223, 223)); 
		bg.drawString(blocks.getNumCollisions() + "", 100, 100);
	}

	public void paintComponent(Graphics g) { 

		blocks.step();

		/*---------------------Bl�cke und Hintergrund farbig machen------------------------*/
		
		paintBackground();
		paintBlocks();
		
		/*---------------------Graphics Zeichnen------------------------*/
		
		g.drawImage(bi, 0, 0, null); 

		/*---------------------Aktualisieren------------------------*/
		
		repaint(); 
	}

	public Dimension getPreferredSize() { 

		/*---------------------H�he / Breite des Panels anpassen------------------------*/
		
		try {
			return new Dimension(1000, 500);
		} catch (NullPointerException e) {
			return new Dimension(1000, 1000); 
		}
	}

	public JPanel createAndShowGUI() { 

		/*---------------------JPanel sichtbar machen------------------------*/
		
		JPanel f = new JPanel();

		f.add(this);
		f.setVisible(true);
		f.setFocusable(true);

		return f;
	}
}
