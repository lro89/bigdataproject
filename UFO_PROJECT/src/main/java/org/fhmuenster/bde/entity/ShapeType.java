package org.fhmuenster.bde.entity;

import java.util.EnumSet;
import java.util.Set;

/**
 * Arten von UFO-Shapes.
 *
 */
public enum ShapeType {

	CHANGED("changed"), CHANGING("Changing"), CHEVRON("Chevron"), CIGAR("Cigar"), CIRCLE(
			"Circle"), CONE("Cone"), CRESCENT("Crescent"), CROSS("Cross"), CYLINDER(
			"Cylinder"), DELTA("Delta"), DIAMOND("Diamond"), DISK("Disk"), DOME(
			"Dome"), EGG("Egg"), FIREBALL("Fireball"), FLARE("Flare"), FLASH(
			"Flash"), FORMATION("Formation"), HEXAGON("Hexagon"), LIGHT("Light"), OTHER(
			"Other"), OVAL("Oval"), PYRAMID("pyramid"), RECTANGLE("Rectangle"), ROUND(
			"Round"), SPHERE("Sphere"), TEARDROP("Teardrop"), TRIANGLE(
			"Triangle"), TRIANGULAR("TRIANGULAR"), UNKNOWN("Unknown"), UNSPECIFIED(
			"Unspecified");

	/**
	 * Liefert ein Set aller Shape-Arten
	 */
	public static Set<ShapeType> ALL = EnumSet.allOf(ShapeType.class);

	/**
	 * Bezeichnung des Shape
	 */
	private final String name;

	ShapeType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
