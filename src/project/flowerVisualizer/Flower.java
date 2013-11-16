package project.flowerVisualizer;

import java.util.ArrayList;


public class Flower extends FlowerComponent{
	
	private int xpos;	//Leftmost point
	private int ypos;	//Bottom point
	
	private int stemHeight;
	private int numberOfPetals;
	private int coreRadius;
	private int petalRadius;
	
	private boolean hasLeaves;
	private int numberOfRoots; //= 6;
	
	//TODO: For the future extensions:
		//private String [] methodName;
		//private SMTHField parentField ?
	
	/** Generate a nice flower given all the possible parameters.
	 * @param xpos - Leftmost point of the rectangle that surrounds the flower
	 * @param ypos - The lowest point of the rectangle that surrounds the flower
	 * @param stemHeight - The height of the stem, not counting the head and petals
	 * @param numberOfPetals
	 * @param coreSize - The size of the core of the head to which the petals are connected
	 * @param petalRadius - The radius within which the petals are (petalSize + coreSize = Head size)
	 * @param numberOfRoots
	 * @param hasLeaves
	 * @param className
	 */
	public Flower(int xpos, int ypos, int stemHeight, int numberOfPetals, int coreSize, int petalRadius, 
			int numberOfRoots, boolean hasLeaves, String className) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.stemHeight = stemHeight;
		this.numberOfPetals = numberOfPetals;
		this.coreRadius = coreSize;
		this.petalRadius = petalRadius;
		this.numberOfRoots = numberOfRoots;
		this.hasLeaves = hasLeaves;
		this.name = className;
		
		//Primary color is the color of the core.
		this.primaryColor = Palette.ALIZARIN;
	}

	
	//TODO: A constructor with fewer arguments? With which ones?
	
	
	//Getters and setters
	//TODO: Protect the vars; for instance, from a negative input.

	public int getXpos() {
		return xpos;
	}


	public void setXpos(int xpos) {
		this.xpos = xpos;
	}


	public int getYpos() {
		return ypos;
	}


	public void setYpos(int ypos) {
		this.ypos = ypos;
	}


	public int getStemHeight() {
		return stemHeight;
	}


	public void setStemHeight(int stemHeight) {
		this.stemHeight = stemHeight;
	}


	public int getNumberOfPetals() {
		return numberOfPetals;
	}


	public void setNumberOfPetals(int numberOfPetals) {
		this.numberOfPetals = numberOfPetals;
	}


	public int getCoreRadius() {
		return coreRadius;
	}


	public void setCoreRadius(int coreRadius) {
		this.coreRadius = coreRadius;
	}


	public int getPetalRadius() {
		return petalRadius;
	}


	public void setPetalRadius(int petalRadius) {
		this.petalRadius = petalRadius;
	}


	public boolean hasRoots() {
		return numberOfRoots > 0;
	}

	public boolean hasLeaves() {
		return hasLeaves;
	}


	public void setHasLeaves(boolean hasLeaves) {
		this.hasLeaves = hasLeaves;
	}

	public int getNumberOfRoots() {
		return numberOfRoots;
	}


	public void setnumberOfRoots(int numberOfRoots) {
		this.numberOfRoots = numberOfRoots;
	}


	@Override
	public ArrayList<Flower> getFlowerList() {
		ArrayList<Flower> toReturn = new ArrayList<Flower>();
		toReturn.add(this);
		return toReturn;
	}
	
}