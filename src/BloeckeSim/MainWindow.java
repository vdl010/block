package BloeckeSim;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/*---------------------MainWindow---------------------*/

//  In der Klasse MainWindow erstellen wir das Frame auf dem das Programm l�uft.
//  Hierzu legen wir auf das Frame ein JPanel (controlPanel) mit einem JButton (btnStartStop) um zwischen der Simulation und der Eingabe zu wechseln.

//  �ber das controlPanel (JPanel) legen wir das settingsPanel (JPanel), welches in das eingabePanel (JPanel) links und das tabellePanel (JPanel) rechts unterteilt sind.
//  Das eingabePanel (JPanel) ist wiederrum in das wert1Panel (JPanel) und das wert2Panel (JPanel) unterteilt.
//  Diese beiden Panel wiederum enthalten ein JTextField (txtMasseWuerfel1), (txtMasseWuerfel2) und ein JLabel (lblMasseWuerfel1), (lblMasseWuerfel2). 
//  Das JTextField beschriftet das JLabel mit der Masse 1 und der Masse 2 und das JLabel ist f�r die Eingabe da.
//  Au�erdem ist das JLabel mit einem ActionListener verkn�pft, so k�nnen Werte aus der Tabelle in das Eingabefeld �bertragen werden.

//  Auf dem tabellePanel (JPanel) liegen zwei (JLabel) das lblMasse1 und das lblMasse2.
//  Diese beschriften die Tabelle, welche darunter im tabellebuttonPanel (JPanel liegt).
//  Die Tabelle im tabellenbuttonPanel enth�lt 20 Buttons, welche �ber die add Funktion hinzugef�gt und �ber eine For Schleife und zwei Variablen bef�llt wird.
//  Durch dr�cken der einzelnen Buttons wird der Wert des Buttons, (welcher auf dem Button steht), �ber einen ActionListener in das Eingabefeld aus dem controlPanel �bertragen.

//  Rechts oben im tabellePanel befindet sich der Hilfe Button (btnHelp).
//  Dieser ist mit einem ActionListener verkn�pft und �ffnet das HelpWindow.

//  In der KLassse MainWindow befindet sich au�erdem die Klasse NumbersOnlyDocument.
//  Sie ist daf�r da das nur positive Zahlen eingegeben werden k�nnen und sperrt alle anderen Tasten der Tastatur.

//  Der Start-Stop-Button fr�gt �ber ein ActionListener noch zus�tzlich ab, ob �berhaupt ein Wert eingegeben wurde.
//  Falls dies nicht der Fall ist, wird beim dr�cken des Buttons nicht zur Simulation gewechselt sondern ein beep Ton erzeugt.

/*----------------------------------------------------*/

public class MainWindow extends JFrame {
	private JTextField txtMasseWuerfel1;
	private JTextField txtMasseWuerfel2;

	private boolean isRunning = false; 

	public MainWindow() {
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(1000, 600);
		setTitle("Elastischer Sto�");

		/*---------------------JPanel------------------------*/
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBounds(0, 500, 984, 61);
		getContentPane().add(controlPanel);
		controlPanel.setLayout(new GridLayout(0, 1, 0, 0));

		/*---------------------JButton------------------------*/
		
		JButton btnStartStop = new JButton("Start");
		btnStartStop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		controlPanel.add(btnStartStop);

		/*---------------------JPanel------------------------*/
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 0, 984, 502);
		getContentPane().add(settingsPanel);
		settingsPanel.setLayout(new GridLayout(1, 0, 0, 0));

		/*---------------------JPanel------------------------*/
		
		JPanel eingabePanel = new JPanel();
		settingsPanel.add(eingabePanel);
		eingabePanel.setLayout(null);

		/*---------------------JPanel------------------------*/
		
		JPanel wert1Panel = new JPanel();
		wert1Panel.setBounds(32, 75, 428, 89);
		eingabePanel.add(wert1Panel);
		wert1Panel.setLayout(null);

		/*---------------------JTextField------------------------*/
		
		txtMasseWuerfel1 = new JTextField();
		txtMasseWuerfel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMasseWuerfel1.setBounds(133, 50, 162, 25);
		wert1Panel.add(txtMasseWuerfel1);
		txtMasseWuerfel1.setColumns(10);
		txtMasseWuerfel1.setDocument(new NumbersOnlyDocument()); 

		/*---------------------JLabel------------------------*/
		
		JLabel lblMasseWuerfel1 = new JLabel("Masse W\u00FCrfel 1");
		lblMasseWuerfel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasseWuerfel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMasseWuerfel1.setBounds(133, 13, 162, 36);
		wert1Panel.add(lblMasseWuerfel1);

		/*---------------------JPanel------------------------*/
		
		JPanel wert2Panel = new JPanel();
		wert2Panel.setLayout(null);
		wert2Panel.setBounds(32, 282, 428, 89);
		eingabePanel.add(wert2Panel);

		/*---------------------JTextField------------------------*/
		
		txtMasseWuerfel2 = new JTextField();
		txtMasseWuerfel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMasseWuerfel2.setColumns(10);
		txtMasseWuerfel2.setBounds(133, 50, 162, 25);
		txtMasseWuerfel2.setDocument(new NumbersOnlyDocument()); 
		wert2Panel.add(txtMasseWuerfel2);

		/*---------------------JLabel------------------------*/
		
		JLabel lblMasseWuerfel2 = new JLabel("Masse W\u00FCrfel 2");
		lblMasseWuerfel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasseWuerfel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMasseWuerfel2.setBounds(133, 13, 162, 36);
		wert2Panel.add(lblMasseWuerfel2);

		/*---------------------JPanel------------------------*/
		
		JPanel tabellePanel = new JPanel();
		settingsPanel.add(tabellePanel);
		tabellePanel.setLayout(null);

		/*---------------------JPanel------------------------*/
		
		JPanel tabellebuttonPanel = new JPanel();
		tabellebuttonPanel.setBounds(100, 99, 311, 289);
		tabellePanel.add(tabellebuttonPanel);
		tabellebuttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		/*---------------------JLabel------------------------*/
		
		JLabel lblMasse2 = new JLabel("Masse 2");
		lblMasse2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasse2.setBounds(256, 59, 155, 35);
		tabellePanel.add(lblMasse2);
		lblMasse2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		/*---------------------JLabel------------------------*/
		
		JLabel lblMasse1 = new JLabel("Masse 1");
		lblMasse1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasse1.setBounds(100, 59, 155, 35);
		tabellePanel.add(lblMasse1);
		lblMasse1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		/*---------------------JButton------------------------*/
		
		JButton btnHelp = new JButton("Hilfe");
		btnHelp.setBounds(393, 11, 89, 23);
		tabellePanel.add(btnHelp);

		/*---------------------JPanel------------------------*/
		
		JPanel simulationPanel = new JPanel();
		simulationPanel.setBounds(0, 0, 984, 502);
		getContentPane().add(simulationPanel);
		simulationPanel.setLayout(new GridLayout(1, 0, 0, 0));
		simulationPanel.setVisible(false);

		/*---------------------ActionListener------------------------*/
		
		btnStartStop.addActionListener(l -> { 
			
			/*---------------------Eingabeabfrage / Beep------------------------*/
			
			if (txtMasseWuerfel1.getText().equals("") || txtMasseWuerfel2.getText().equals("")) { 
				Toolkit.getDefaultToolkit().beep();
				return;
			}

			/*---------------------Simulationsabfrage / JA ? --> Stoppen------------------------*/
			
			if (isRunning) {
				settingsPanel.setVisible(true);
				simulationPanel.setVisible(false);
				btnStartStop.setText("Start");
				simulationPanel.removeAll();

				/*---------------------Simulationsabfrage / Nein ? --> Starten------------------------*/			

			} else {
				settingsPanel.setVisible(false);
				simulationPanel.setVisible(true);
				btnStartStop.setText("Stop");

				simulationPanel.removeAll();
				double masse1 = Double.parseDouble(txtMasseWuerfel1.getText());
				double masse2 = Double.parseDouble(txtMasseWuerfel2.getText());

				Blocks blocks = new Blocks(masse1, masse2, 200, 700);
				JPanel simulation = new DisplayCollisions(simulationPanel.getBounds().width + 10,
						simulationPanel.getBounds().height + 5, blocks).getPanel();
				simulation.setVisible(true);
				simulationPanel.add(simulation);
			}
			
			/*---------------------Aktualisieren von isRunning------------------------*/
			
			isRunning = !isRunning; 
		});

		/*---------------------for - Schleife / Tabellen Werte------------------------*/
		
		for (int i = 0; i < 10; i++) { 
			int masseeinswert = i + 1; 
			int massezweiwert = 1 * (int) Math.pow(10, i); 

			/*---------------------JButtons------------------------*/
			
			JButton masseeins = new JButton(Integer.toString(masseeinswert)); 
			JButton massezwei = new JButton(Integer.toString(massezweiwert)); 

			/*---------------------Add Funktion der Buttons------------------------*/
			
			tabellebuttonPanel.add(masseeins);
			tabellebuttonPanel.add(massezwei); 

			/*---------------------ActionListener Werte �bertragen------------------------*/
			
			masseeins.addActionListener(l -> { 
				txtMasseWuerfel1.setText(Integer.toString(masseeinswert));
			});

			massezwei.addActionListener(l -> { 
				txtMasseWuerfel2.setText(Integer.toString(massezweiwert));
			});
		}

		/*---------------------Help Window------------------------*/
		
		HelpWindow help = new HelpWindow();
		btnHelp.addActionListener(l -> {
			help.setVisible(true);
		});
	}

	/*---------------------NumbersOnlyDocument / Beep------------------------*/
	
	class NumbersOnlyDocument extends PlainDocument { 

		@Override
		public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
			if (s.matches("\\d+")) {
				super.insertString(offset, s, attributeSet);
			} else {
				Toolkit.getDefaultToolkit().beep(); 
			}
		}
	}
}
