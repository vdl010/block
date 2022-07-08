package BloeckeSim;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JTextArea;

/*---------------------HelpWindow---------------------*/

//  Die Klasse HelpWindow beinhaltet ein JFrame, welches durch den Hilfe Button auf dem MainWindow der GUI 1 ge�ffnet wird.
//  Hierbei habe ich mich gegen die Regel, bewusst f�r ein JFrame und gegen ein JPanel entschieden, da der Nutzer so das HelpWindow auf die Seite schieben kann,
//  und so w�hrend dem lesen der Hilfe, gleichzeitig die Erkl�rung im Programm umsetzten kann.
//  Das HelpWindow kann entweder oben rechts auf dem roten Kreuz, oder auf dem OK Button geschlossen werden.
//  Der OK Button befindet sich ebenfalls in der HelpWindow Klasse, genauso wie 2 JTextAreas auf denen die Hilfe, bzw. Erkl�rung steht.

/*----------------------------------------------------*/

public class HelpWindow extends JFrame {
	public HelpWindow() {
		setType(Type.POPUP);
		setResizable(false);
		setSize(1000, 600);
		setTitle("Elastischer Sto� - Hilfe");

		/*---------------------JButton------------------------*/

		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnOK, BorderLayout.SOUTH);

		/*---------------------JTextArea------------------------*/

		JTextArea txtHilfe = new JTextArea();
		txtHilfe.setTabSize(20);
		txtHilfe.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtHilfe.setText(" Erkl\u00E4rung:\r\n Mit diesem Programm k\u00F6nnen Sie das Aufeinanderprallen zwei beliebiger Massen simulieren\r\n und die Anzahl der Kollisionen berechnen.\r\n\r\n Hilfe:\r\n W\u00E4hlen sie zwei jeweils einen Wert f\u00FCr die Masse 1 und die Masse 2 aus der Tabelle aus\r\n oder geben Sie eigene Werte in die daf\u00FCr vorgesehenen Eingabefelder.\r\n Achten Sie darauf, das die Werte positiv sind und die Masse 2 gr\u00F6\u00DFer ist als die Masse 1.\r\n\r\n Durch das Dr\u00FCcken des Start Buttons gelangen Sie zur Simulation.\r\n\r\n Links oben befindet sich die Anzahl der Kollisionen.\r\n\r\n Sie k\u00F6nnen durch das Dr\u00FCcken des Stop Buttons zum Eingabepanel zur\u00FCckkehren.\r\n\r\n Das Programm kann beliebig oft wiederholt werden.\r\n\r\n Sie k\u00F6nnen das Programm oben rechts schlie\u00DFen.");
		getContentPane().add(txtHilfe, BorderLayout.CENTER);

		/*---------------------JTextArea------------------------*/

		JTextArea txtüberschrift = new JTextArea();
		txtüberschrift.setColumns(20);
		txtüberschrift.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
		txtüberschrift.setText(" WICHTIG!!!");
		getContentPane().add(txtüberschrift, BorderLayout.NORTH);

		/*---------------------ActionListener------------------------*/

		btnOK.addActionListener(l -> {
			this.setVisible(false);
		});
	}

}
