package project.flowerVisualizer;

import java.awt.Color;
import java.util.ArrayList;

	/**
	 * A parent to the Flower and Flower Composite in order to implement Composite pattern for the package/Flower tree.
	 * Also has the primary color that would be used to draw.
	 * @author Mike
	 *
	 */
public abstract class FlowerComponent {

	/**
	 * Each component would have a color associated.
	 */
	protected Color primaryColor;
	
	/**
	 * A string that would be displayed on the screen for this Component.
	 */
	protected String name; 

	/**
	 * Flattens the tree to a Flower list using tree traversal from left to right.
	 * @return A list containing only Flowers.
	 */
	public abstract ArrayList<Flower> getFlowerList();

	/**
	 * 
	 * @return The base color of the component.
	 */
	public Color getPrimaryColor() {
		return primaryColor;
	}

	/**
	 * Changes the basic color of the component.
	 * @param primaryColor - the Color to set.
	 */
	public void setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
