package pro.geektalk.onlineoroffline.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// -- Variables for gui layout
	final GridLayout layout = new GridLayout(5, 0);
	Container pane;

	// -- Components for the gui
	JLabel[] labels = { new JLabel("Coded by Jacob - hackcomunity.com"),
			new JLabel("Website: "), new JLabel("Status: ") };
	JTextField website = new JTextField();
	JButton test = new JButton("Test");

	// -- Constructor for this class, called upon new stance

	public Gui() {
		// -- Pane components "sit" on
		pane = this.getContentPane();

		// -- Gui settings
		this.setVisible(true);
		this.setSize(300, 150);
		this.setTitle("Online or Offline?");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(layout);

		// -- Center first label and add all 3
		labels[0].setHorizontalAlignment(JLabel.CENTER);
		for (JLabel label : labels) {
			pane.add(label);
		}

		// -- Border on textbox
		website.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		website.setToolTipText("Enter website here");
		website.addKeyListener(new KeyListener() { // -- Lets you press enter to
													// test

			@Override
			public void keyPressed(KeyEvent k) {
				if (k.getKeyCode() == 10) {
					test();
				}

			}

			@Override
			public void keyReleased(KeyEvent k) {
				// System.out.println(k.getKeyChar() +"    " +k.getKeyCode());

			}

			@Override
			public void keyTyped(KeyEvent k) {
				// System.out.println(k.getKeyChar() +"    " +k.getKeyCode());

			}

		});
		pane.add(website);

		// -- Make the button do something
		test.setToolTipText("You can also press enter to check the website!");
		test.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				test();

			}

		});
		pane.add(test);

	}

	// -- Changes the text of a label?
	public void changeLabelText(final JLabel label, final String text) {
		try {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					label.setText(text);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- done when button is pressed
	public void test() {
		String text = website.getText();
		if (!text.isEmpty()) {
			if (text.contains("www."))
				text = text.replace("www.", "");
			if (!text.contains("http://") || !text.contains("https://")) {
				text = "http://" + text;
				changeLabelText(labels[1], "Website: " + text);
				this.website.setText("");
				connect(text);
			}
		} else {
			return;
		}
	}

	// -- Http connections stuff
	public void connect(final String link) {
		HttpURLConnection con = null;
		URL url = null;
		try {
			url = new URL(link);
			con = (HttpURLConnection) url.openConnection();
			changeLabelText(
					labels[2],
					String.format("Status: Code [%s] - Message [%s]",
							con.getResponseCode(), con.getResponseMessage()));
			System.out.println(String.format("Code: %s \nMessage: %s",
					con.getResponseCode(), con.getResponseMessage()));
		} catch (IOException e) {
			try {
				changeLabelText(labels[2], String.format(
						"Status: Code [%s]  -  Message [%s]",
						con.getResponseCode(), con.getResponseMessage()));
				System.out.println(String.format("Code: %s \nMessage: %s",
						con.getResponseCode(), con.getResponseMessage()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		con.disconnect();
	}
}
