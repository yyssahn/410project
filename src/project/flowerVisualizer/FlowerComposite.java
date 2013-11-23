package project.flowerVisualizer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class for implementing Composite pattern for the tree of Flowers.
 * The primary color is the color the box around flowers would be.
 * @author Mike
 *
 */
public class FlowerComposite extends FlowerComponent{

	/**
	 * Size of the offset from the border to the flowers, in pixels.
	 */
	private int offsetSize = 5;
	private LinkedList<FlowerComponent> children = new LinkedList<FlowerComponent>();

	@Override
	public ArrayList<Flower> getFlowerList()
	{
		ArrayList<Flower> toReturn = new ArrayList<Flower>();
		for (FlowerComponent currentChild : children)
			toReturn.addAll(currentChild.getFlowerList());
		return toReturn;
	}

	/**
	 * Adds a FlowerComponent to the list of children.
	 * @param flowerComponent
	 */
	public void add(FlowerComponent flowerComponent)
	{
		children.add(flowerComponent);
	}


	//////Getters and setters section.
	/**
	 * 
	 * @return Size of the offset between border and flowers (pixels).
	 */
	public int getOffsetSize() {
		return offsetSize;
	}

	/**
	 * Sets the offset between Flowers and the border.
	 * @param offsetSize - the size of offset to set (pixels).
	 */
	public void setOffsetSize(int offsetSize) {
		this.offsetSize = offsetSize;
	}

	public LinkedList<FlowerComponent> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<FlowerComponent> children) {
		this.children = children;
	}

	@Override
	public FlowerUIComponent makeUIWrap() {
		return new FlowerUIComposite().wrapAround(this);
	}

}
