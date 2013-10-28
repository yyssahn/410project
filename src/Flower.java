
public class Flower {
	
	private int xpos;	//Leftmost point
	private int ypos;	//Bottom point
	
	private int stemHeight;
	private int numberOfPetals;
	private int coreSize;
	private int petalSize;
	
	private boolean hasRoots;	//For imports?
	private boolean hasLeaves;

	private String className;
	
	
	//TODO: For the future extensions:
		//private String [] methodName;
		//private SMTHField parentField ?
	
	/** Generate a nice flower given all the possible parameters.
	 * @param xpos - Leftmost point of the rectangle that surrounds the flower
	 * @param ypos - The lowest point of the rectangle that surrounds the flower
	 * @param stemHeight - The height of the stem, not counting the head and petals
	 * @param numberOfPetals
	 * @param coreSize - The size of the core of the head to which the petals are connected
	 * @param petalSize - The radius within which the petals are (petalSize + coreSize = Head size)
	 * @param hasRoots
	 * @param hasLeaves
	 * @param className
	 */
	public Flower(int xpos, int ypos, int stemHeight, int numberOfPetals, int coreSize, boolean hasRoots, boolean hasLeaves, String className) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.stemHeight = stemHeight;
		this.numberOfPetals = numberOfPetals;
		this.coreSize = coreSize;
		this.hasRoots = hasRoots;
		this.hasLeaves = hasLeaves;
		this.className = className;
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


	public int getCoreSize() {
		return coreSize;
	}


	public void setCoreSize(int coreSize) {
		this.coreSize = coreSize;
	}


	public int getPetalSize() {
		return petalSize;
	}


	public void setPetalSize(int petalSize) {
		this.petalSize = petalSize;
	}


	public boolean isHasRoots() {
		return hasRoots;
	}


	public void setHasRoots(boolean hasRoots) {
		this.hasRoots = hasRoots;
	}


	public boolean isHasLeaves() {
		return hasLeaves;
	}


	public void setHasLeaves(boolean hasLeaves) {
		this.hasLeaves = hasLeaves;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return this.className;
	}
	
}