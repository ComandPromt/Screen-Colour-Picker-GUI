package com.colourpicker;

import java.awt.Robot;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyLogger implements NativeKeyListener {

	Robot robot;

	public KeyLogger(Robot r) {

		this.robot = r;

	}

	private static final Path file = Paths.get("color.txt");

	int x = 0;

	int y = 0;

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {

		//

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {

		try {

			if (!(robot instanceof Robot)) {

				robot = new Robot();

			}

			x = ColourPicker.frame.getX();

			y = ColourPicker.frame.getY();

			x -= 10;

			switch (nativeEvent.getKeyCode()) {

			case 57421:

				x++;

				y += 9;

				break;

			case 57419:

				x--;

				y += 9;

				break;

			case 57416:

				y += 8;

				break;

			case 57424:

				y += 10;

				break;

			default:

				try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
						StandardOpenOption.TRUNCATE_EXISTING); PrintWriter writer = new PrintWriter(os)) {

					writer.print(ColourPicker.colour.getRed() + "," + ColourPicker.colour.getGreen() + ","
							+ ColourPicker.colour.getBlue());

					writer.flush();

				}

				catch (IOException ex) {

				}

				System.exit(0);

				break;

			}

			robot.mouseMove(x, ++y);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		//
	}

}
