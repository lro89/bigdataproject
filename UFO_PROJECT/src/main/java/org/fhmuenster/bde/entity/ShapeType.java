package org.fhmuenster.bde.entity;

import java.util.EnumSet;
import java.util.Set;

/**
 * Arten von UFO-Shapes.
 * 
 */
public enum ShapeType {

	CHANGING("Changing"), CHEVRON("Chevron"), CIGAR("Cigar"), CIRCLE("Circle"), CONE(
			"Cone"), CROSS("Cross"), CYLINDER("Cylinder"), DIAMOND("Diamond"), DISK(
			"Disk"), EGG("Egg"), FIREBALL("Fireball"), FLARE("Flare"), FLASH(
			"Flash"), FORMATION("Formation"), LIGHT("Light"), OTHER("Other"), OVAL(
			"Oval"), RECTANGLE("Rectangle"), ROUND("Round"), SPHERE("Sphere"), TEARDROP(
			"Teardrop"), TRIANGLE("Triangle"), UNKNOWN("Unknown");

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
