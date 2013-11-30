package project.flowerVisualizer;


import java.util.ArrayList;

// A class that provides methods to run the visualizer
public interface FlowerVisualizer {	
	/**
	 * An entry point to the Visualizer which draws the flowers on the window of specified size. 
	 * @param flowerTree - the tree of Flowers to draw the package info.
	 * @param width - width of the window where the flowers would be made.
	 * @param height - height of the window where the flowers would be made.
	 * @param relationList - a list of relationships between the flowers, can be null.
	 */
	public void drawFlowers(FlowerComposite flowerTree, int width, int height, ArrayList<FlowerRelation> relationList);
	
	/**
	 * An entry point to the Visualizer which draws the flowers on the window of specified size. 
	 * @param flowerList - the list of flowers to draw.
	 * @param width - width of the window where the flowers would be made.
	 * @param height - height of the window where the flowers would be made.
	 * @param relationList - a list of relationships between the flowers, can be null.
	 */
	public void drawFlowers(ArrayList<Flower> flowerList, int width, int height, ArrayList<FlowerRelation> relationList);
	
	/**
	 * Adds a nice scale to show the number of lines of code involved. Ignored if no drawFlowers was executed beforehand.
	 * @param x - x coordinate of the bottom of the scale.
	 * @param y - y coordinate of the bottom. (Measured from the bottom of the window.)
	 * @param height - height from the bottom of the scale to the top.
	 * @param middleText - text to be displayed at the middle of the scale.
	 * @param upperText - text to be displayed at the top of the scale.
	 */
	public void requestScale(int x, int y, int height, String middleText, String upperText);
	
}
