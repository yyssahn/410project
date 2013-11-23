package project.flowerVisualizer;

import java.awt.Graphics;
import java.util.ArrayList;


public abstract class FlowerUIComponent{

	protected int height;
	protected int width;
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	
	/**
	 * Flattens the tree to a FlowerUI list using tree traversal from left to right.
	 * @return A list containing only FlowerUIs.
	 */
	public abstract ArrayList<FlowerUI> getFlowerUIList();
	
	/**
	 * Wraps around a corresponding object (Flower, FlowerComponent, FlowerComposite). This should be done for the component to work properly!
	 * @param wrapped
	 * @return Self - makes wrapping easier.
	 */
	public abstract FlowerUIComponent wrapAround(FlowerComponent wrapped);
	
	/**
	 * Draws the component.
	 * @param g - Graphics to draw the component.
	 * @param x - horizontal displacement.
	 * @param y - vertical displacement.
	 */
	public abstract void paintReuseGraphics(Graphics g, int x, int y);
	
	/**
	 * Initializes the component to be drawn. Mostly sets the height and width.
	 */
	public abstract void init();
}
