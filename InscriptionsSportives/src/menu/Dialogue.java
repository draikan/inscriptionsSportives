package menu;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;


public class Dialogue extends JDialog {

	private static final long serialVersionUID = 1L;

	public Dialogue(JFrame parent, String title, String message) {
		super(parent, title);
		System.out.println("creating the window..");
		// set the position of the window
		Point p = new Point(400, 400);
		setLocation(p.x, p.y);

		// Create a message
		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel(message));
		// get content pane, which is usually the
		// Container of all the dialog's components.
		getContentPane().add(messagePane);

		// Create a button
		JPanel container = new JPanel();
		JButton bouton = new JButton("Oui");
	    JButton bouton2 = new JButton("Non");
	    bouton.addActionListener(new BoutonListener());
	    bouton2.addActionListener(new Bouton2Listener());
	    JPanel south = new JPanel();
	    south.add(bouton);
	    south.add(bouton2);
	    container.add(south);
		// set action listener on the button

		getContentPane().add(container, BorderLayout.PAGE_END);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	// override the createRootPane inherited by the JDialog, to create the rootPane.
	// create functionality to close the window when "Escape" button is pressed
	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		Action action = new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("escaping..");
				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", action);
		return rootPane;
	}

	// an action listener to be used when an action is performed
	// (e.g. button is pressed)
	class BoutonListener implements ActionListener {

		//close and dispose of the window.
		public void actionPerformed(ActionEvent e) {
			System.out.println("disposing the window..");
			System.exit(0);
		}
	}
	class Bouton2Listener implements ActionListener {

		//close and dispose of the window.
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("disposing the window..");
			setVisible(false);
		}
	}

	public static void main(String[] a) {
		Dialogue dialog = new Dialogue(new JFrame(), "Avertissement", "Voulez-vous vraiment quitter ?");
		// set the size of the window
		dialog.setSize(300, 150);
	}
}
