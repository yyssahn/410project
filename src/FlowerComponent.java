

import java.awt.Canvas;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class FlowerComponent extends Canvas{
	private Flower flowerData;
	private int totalWidth;
	private int totalHeight;
	private final int DEFAULTROOTSIZE = 40;
	private int rootSize;


	public FlowerComponent(Flower flowerData) {
		if (flowerData == null)
			return;
		this.flowerData = flowerData;
		totalWidth = flowerData.getCoreSize() + flowerData.getPetalSize();
		rootSize = (flowerData.isHasRoots() ? 1 : 0) * DEFAULTROOTSIZE;
		totalHeight = totalWidth + flowerData.getStemHeight() + rootSize;
		this.setSize(totalWidth, totalHeight);
	}

	//Do not look here, I am a bit ashamed =(
	public void paint(Graphics g){
		if (flowerData == null)
		{	
			System.out.print("Trying to draw an empty Flower");
			return;
		}

		this.setSize(totalWidth, totalHeight);

		//Head
		g.drawOval(0, 0, totalWidth-1, totalWidth-1);
		g.drawOval(flowerData.getPetalSize()/2, flowerData.getPetalSize()/2, 
				flowerData.getCoreSize(),flowerData.getCoreSize());

		//Stem
		g.drawArc(totalWidth/2, totalWidth, 40, flowerData.getStemHeight(), 270, 180);

		//Roots
		g.draw3DRect(totalWidth/2-40, totalHeight-rootSize, 80, rootSize-1, false);
	}

}