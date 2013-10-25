
public class Flower {
	
	private int xpos;	//Leftmost point
	private int ypos;	//Bottom point
	
	private int stemHeight;
	private int numberOfPaddles;
	private int coreSize;
	private int paddleSize;
	
	private boolean hasRoots;	//For imports?
	private boolean hasLeaves;
	
	
	//TODO: For the future extensions:
		//private String [] methodName;
		//private SMTHField parentField ?
	
	/** Generate a nice flower given all the possible parameters.
	 * @param xpos - Leftmost point of the rectangle that surrounds the flower
	 * @param ypos - The lowest point of the rectangle that surrounds the flower
	 * @param stemHeight - The height of the stem, not counting the head and paddles (?)
	 * @param numberOfPaddles
	 * @param coreSize - The size of the core of the head to which the paddles are connected
	 * @param paddleSize - The radius within which the paddles are (padlleSize + coreSize = Head size)
	 * @param hasRoots
	 * @param hasLeaves
	 */
	public Flower(int xpos, int ypos, int stemHeight, int numberOfPaddles,
			int coreSize, boolean hasRoots, boolean hasLeaves) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.stemHeight = stemHeight;
		this.numberOfPaddles = numberOfPaddles;
		this.coreSize = coreSize;
		this.hasRoots = hasRoots;
		this.hasLeaves = hasLeaves;
	}

	
	//TODO: A constructor with less arguments? With which ones?
	
	
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


	public int getNumberOfPaddles() {
		return numberOfPaddles;
	}


	public void setNumberOfPaddles(int numberOfPaddles) {
		this.numberOfPaddles = numberOfPaddles;
	}


	public int getCoreSize() {
		return coreSize;
	}


	public void setCoreSize(int coreSize) {
		this.coreSize = coreSize;
	}


	public int getPaddleSize() {
		return paddleSize;
	}


	public void setPaddleSize(int paddleSize) {
		this.paddleSize = paddleSize;
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
	
	
	
	
	
}
