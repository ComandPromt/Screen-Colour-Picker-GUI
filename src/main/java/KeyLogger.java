
import java.awt.Robot;
import java.nio.file.Path;
import java.nio.file.Paths;

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

			}

			robot.mouseMove(x, ++y);

		}

		catch (Exception e) {

		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		//
	}

}
