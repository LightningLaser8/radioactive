package net.mcreator.radioactive.procedures;

public class Lerp3ColoursProcedure {
	public static double execute(double col1, double col2, double col3, double factor) {
		return factor < 0.5 ? Lerp2ColoursProcedure.execute(col1, col2, factor * 2) : Lerp2ColoursProcedure.execute(col2, col3, (factor - 0.5) * 2);
	}
}
