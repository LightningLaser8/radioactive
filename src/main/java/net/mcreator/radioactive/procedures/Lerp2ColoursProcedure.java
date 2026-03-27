package net.mcreator.radioactive.procedures;

public class Lerp2ColoursProcedure {
	public static double execute(double col1, double col2, double factor) {
		return 255 << 24 | (int) (((int) col1 >> 16 & 255) * (1 - factor) + ((int) col2 >> 16 & 255) * factor) << 16 | (int) (((int) col1 >> 8 & 255) * (1 - factor) + ((int) col2 >> 8 & 255) * factor) << 8
				| (int) (((int) col1 & 255) * (1 - factor) + ((int) col2 & 255) * factor);
	}
}
