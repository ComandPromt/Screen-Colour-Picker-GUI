
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import org.jnativehook.GlobalScreen;

public class ColourPicker {

	public static Color colour;

	public static JWindow frame;

	Robot robot;

	public ColourPicker() {

		try {

			GlobalScreen.registerNativeHook();

			GlobalScreen.addNativeMouseListener(new MouseLogger());

			GlobalScreen.addNativeKeyListener(new KeyLogger(robot));

		}

		catch (Exception e) {

		}

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		panel.setLayout(new BorderLayout());

		frame = new JWindow();

		frame.getContentPane().setLayout(new BorderLayout());

		frame.setPreferredSize(new Dimension(128, 128));

		frame.setAlwaysOnTop(true);

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();

		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\u00A0");

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		panel_1.add(lblNewLabel);

		Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

		frame.setCursor(cursor);

		frame.pack();

		frame.setVisible(true);

		try {

			robot = new Robot();

			while (true) {

				colour = robot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x,
						MouseInfo.getPointerInfo().getLocation().y);

				frame.setLocation(MouseInfo.getPointerInfo().getLocation().x + 10,
						MouseInfo.getPointerInfo().getLocation().y - 10);

				BufferedImage image = robot.createScreenCapture(
						new Rectangle(MouseInfo.getPointerInfo().getLocation(), new Dimension(10, 10)));

				panel.removeAll();

				panel.add(new JLabel(new ImageIcon(
						image.getScaledInstance(panel.getWidth(), panel.getHeight(), BufferedImage.SCALE_FAST))),
						BorderLayout.CENTER);

				panel_1.setBackground(colour);

				panel.validate();

				Thread.sleep(10);

			}

		}

		catch (InterruptedException ex) {

			Logger.getLogger(ColourPicker.class.getName()).log(Level.SEVERE, null, ex);

		}

		catch (AWTException ex) {

			Logger.getLogger(ColourPicker.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

	public static void main(String[] args) {
		new ColourPicker();
	}
}