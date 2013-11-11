package project.flowerVisualizer;


import java.util.ArrayList;

// A class that provides methods to run the visualizer
public interface FlowerVisualizer {

	
	/**
	 * An entry point to the Visualizer which draws the flowers on the window of specified size. 
	 * @param flowerList - the list of flowers to draw.
	 * @param width - width of the window where the flowers would be made.
	 * @param height - height of the window where the flowers would be made.
	 * @param relationList - a list of relationships between the flowers.
	 */
	public void drawFlowers(ArrayList<Flower> flowerList, int width, int height, ArrayList<FlowerRelation> relationList);
	
	/**
	 * An entry point to the Visualizer which draws the flowers on the window of specified size. 
	 * @param flowerList - the list of flowers to draw.
	 * @param width - width of the window where the flowers would be made.
	 * @param height - height of the window where the flowers would be made.
	 */
	public void drawFlowers(ArrayList<Flower> flowerList, int width, int height);
	
	
	//TODO: Agree on the standard window size
	/**
	 * An entry point to the Visualizer which draws the flowers on the window of standard size (640x480). 
	 * @param flowerList - the list of flowers to draw.
	 */
	public void drawFlowers(ArrayList<Flower> flowerList);
}
