# Screen-Colour-Picker-GUI

![Preview](preview.png)

This program selects a color from the screen. The color box can be moved using the movement arrows (up, down, left, right).
The color is saved in a txt file so that another program can read it and get the selected color

## Use

~~~java

	try {

		Robot robot = new Robot();

		robot.mouseMove(0, 0);

		Runtime.getRuntime().exec("java -jar PATH OF JAR");
	
	}

	catch (Exception e1) {

	}

~~~