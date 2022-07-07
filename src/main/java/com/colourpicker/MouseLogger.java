package com.colourpicker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class MouseLogger implements NativeMouseListener {

	private static final Path file = Paths.get("color.txt");

	@Override
	public void nativeMouseClicked(NativeMouseEvent nativeEvent) {

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent nativeEvent) {

		try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING); PrintWriter writer = new PrintWriter(os)) {

			writer.print(ColourPicker.colour.getRed() + "," + ColourPicker.colour.getGreen() + ","
					+ ColourPicker.colour.getBlue());

			writer.flush();

		}

		catch (IOException ex) {

		}

		System.exit(0);

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent nativeEvent) {

		// TODO Auto-generated method stub

	}

}
