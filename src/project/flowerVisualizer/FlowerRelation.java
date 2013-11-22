package project.flowerVisualizer;

import java.awt.Color;

//More of a struct
public class FlowerRelation {

	public int fromFlower;
	public int toFlower;
	public int connectionWidth; //Pixels?
	private Color primaryColor = Palette.ORANGE;	

	public FlowerRelation(int fromFlower, int toFlower, int connectionWidth) {
		super();
		this.fromFlower = fromFlower;
		this.toFlower = toFlower;
		this.connectionWidth = connectionWidth;
	}
	
	public Color getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(Color primaryColor) {
		this.primaryColor = primaryColor;
	}
}