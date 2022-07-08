package BloeckeSim;

/*---------------------Blocks---------------------*/

//  Die Klasse Blocks enth�lt alle Variablen, welche in Verbindung mit den beiden Massebl�cken sind.
//  Dazu z�hlt die Masse, die Position, die StartPosition, die Anzahl der Kollisionen, die StartZeit der Simulation und die Gr��e des Simulationsfensters.
//  Die Positionen und damit die Kollisionen der Bl�cke, werden Tick f�r Tick in Abh�ngigkeit der Zeit aktualisiert und berechnet.
//  Angegeben wird die Position �ber Arrays.
//  Mit Hilfe von Matritzen und DGL wird dann die Bewegung der Masse ausgerechnet.
//  Eine grafische Darstellung der Rechnung finden Sie in folgendem Viedeolink.
//  https://www.youtube.com/watch?v=jsYwFizhncE&list=LL&index=66&t=59s

/*--------------------------------------------------------*/

public class Blocks {

	/*---------------------Masse------------------------*/
	
	private double masse1; 
	private double masse2;

	/*---------------------Position------------------------*/
	
	private double position1;
	private double position2; 

	/*---------------------Start-Position------------------------*/
	
	private double originalPos1;
	private double originalPos2; 

	/*---------------------Anzahl der Kollisionen------------------------*/
	
	private long numCollisions = 0; 

	/*---------------------Winkel------------------------*/
	
	private double angle; 

	/*---------------------Start Zeit------------------------*/
	
	private double startTime; 
	
	/*---------------------Bewegungsfeld------------------------*/
	
	private double tBounds; 

	
	/*---------------------Bl�cke erstellen------------------------*/
	
	
	public Blocks(double masse1, double masse2, double position1, double position2) { 

		/*---------------------Masse festlegen------------------------*/
		
		this.masse1 = masse1; 
		this.masse2 = masse2; 

		/*---------------------Position festlegen------------------------*/
		
		this.position1 = position1; 
		this.position2 = position2; 

		/*---------------------Start-Position festlegen------------------------*/
		
		originalPos1 = position1; 
		originalPos2 = position2; 

		/*---------------------Winkel berechnen------------------------*/
		
		angle = Math.atan(Math.sqrt(masse1 / masse2)); 

		/*---------------------Startzeit in Sekunden------------------------*/
		
		startTime = System.currentTimeMillis() / 1000.0; 
		
		/*---------------------Bewegungsfeld berechnen------------------------*/
		
		tBounds = originalPos2 * Math.sqrt(masse2); 
	}

	
	/*---------------------Animation------------------------*/
	
	
	public void step() { 

		/*---------------------Zeit berechnen------------------------*/
		
		double time = System.currentTimeMillis() / 1000.0; // Zeit in Sekunden

		/*---------------------Dauer der Animation------------------------*/
		
		double animationDuration = 5; 
		
		/*---------------------Berechnung der Dauer------------------------*/
		
		double t = (((animationDuration / 2) - (time - startTime)) / (animationDuration / 2)) * tBounds; 

		/*---------------------Berechnung der Kollisonen------------------------*/
		
		numCollisions = (long) (angleAtTime(t) / angle); 

		/*---------------------Positionen in Abh�ngigkeit der Zeit------------------------*/
		
		double[] positions = getPositions(t);
		
		/*---------------------Position 1 und 2------------------------*/
		
		position1 = positions[0]; 
		position2 = positions[1]; 
	}

	
	/*---------------------Berechnung der Position------------------------*/
	
	
	private double[] getPositions(double t) { 

		double[][] positions = multiplyMatrices(new double[][] { { originalPos1, t } },
				getRotationMatrix(-getRotationAngle(t)));

		if ((angleAtTime(t) + angle) % (2 * angle) < angle) {
			positions[0][0] = Math.abs(positions[0][0]);
		}

		positions[0][0] = positions[0][0] / Math.sqrt(masse1);
		positions[0][1] = positions[0][1] / Math.sqrt(masse2);

		return positions[0];
	}

	
	/*---------------------Winkel Berechnung------------------------*/
	
	
	private double getRotationAngle(double t) { 
		return (int) ((angleAtTime(t) + angle) / (2 * angle)) * (2 * angle);
	}

	
	/*---------------------Winkel in Abh�ngigkeit der Zeit------------------------*/
	
	
	private double angleAtTime(double t) { 

		double tempAngle = Math.atan(originalPos1 / t);

		if (t == 0) { 
			return Math.PI / 2;
		}

		if (tempAngle < 0) { 
			return Math.PI + tempAngle;
		} else {
			return tempAngle;
		}
	}

	
	/*---------------------RotationsMatrix------------------------*/
	
	
	public static double[][] getRotationMatrix(double angleRad) { 
		return new double[][] { { Math.cos(angleRad), -Math.sin(angleRad) },
				{ Math.sin(angleRad), Math.cos(angleRad) } };
	}

	
	/*---------------------Multiplikation der Matrizen------------------------*/
	
	
	public static double[][] multiplyMatrices(double[][] m1, double[][] m2) { 
		int r1 = m1.length;
		int c2 = m2[0].length;

		double[][] m3 = new double[r1][c2];

		for (int xx = 0; xx < r1; xx++) {
			for (int yy = 0; yy < c2; yy++) {
				for (int zz = 0; zz < m1[0].length; zz++) {
					m3[xx][yy] += m1[xx][zz] * m2[zz][yy];
				}
			}
		}

		return m3; 
	}

	/*---------------------Ausgabe von der Position 1------------------------*/
	
	public double getPosition1() { 
		return position1;
	}

	/*---------------------Ausgabe von der Position 2------------------------*/
	
	public double getPosition2() { 
		return position2;
	}

	/*---------------------Ausgabe von den Kollisionen------------------------*/
	
	public long getNumCollisions() { 
		return numCollisions;
	}

	/*---------------------Ausgabe der Werte als Text------------------------*/
	
	public String toString() {
		return ("Mass 1: " + masse1 + "\n" + "Mass 2: " + masse2 + "\n" + "Vel 1: " + "\n" + "Pos 1: " + position1 + "\n"
				+ "Pos 2: " + position2 + "\n");
	}
}
