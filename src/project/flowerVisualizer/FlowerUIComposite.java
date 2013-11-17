package project.flowerVisualizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.LinkedList;

public class FlowerUIComposite extends FlowerUIComponent{

	private LinkedList<FlowerUIComponent> children = new LinkedList<FlowerUIComponent>();
	private FlowerComposite flowerCompositeData;

	Font f = new Font("Monospaced", Font.BOLD, 12);

	@Override
	public ArrayList<FlowerUI> getFlowerUIList() {
		ArrayList<FlowerUI> toReturn = new ArrayList<FlowerUI>();
		for (FlowerUIComponent currentChild : children)
			toReturn.addAll(currentChild.getFlowerUIList());
		return toReturn;
	}

	@Override
	public FlowerUIComponent wrapAround(FlowerComponent wrapped) {
		FlowerComposite wrappedComposite = (FlowerComposite) wrapped;
		this.flowerCompositeData = wrappedComposite;
		for (FlowerComponent current : wrappedComposite.getChildren())
			this.children.add(current.makeUIWrap());
		return this;
	}

	@Override
	public void paintReuseGraphics(Graphics g, int x, int y) {
		g.translate(x, y);
		Shape clipTemp = g.getClip();
		g.setClip(0, 0, width-1, height-1);
		
		//Prints its name
		g.setColor(Color.DARK_GRAY);
		g.setFont(f);
		g.drawString(flowerCompositeData.getName(), 2, 14);

		//Draws the rectangle for itself
		g.setColor(flowerCompositeData.primaryColor);
		g.fillRect(0, 0, width-1, height-1);

		//Draws children
		int disx = flowerCompositeData.getOffsetSize();
		int disy = f.getSize()+4;
		for (FlowerUIComponent current : children)
		{
			current.paintReuseGraphics(g, disx, Math.max(disy, this.height - current.height));
			disx += current.width;
		}
		
		g.setClip(clipTemp);
		g.translate(-x, -y);
	}

	@Override
	public void init() {
		int newWidth = 2 * flowerCompositeData.getOffsetSize();
		int maxHeight = 10;
		for (FlowerUIComponent current : children)
			{
			current.init();
			newWidth += current.getWidth();
			if (current.height > maxHeight)
				maxHeight = current.height;
			}
		width = newWidth;
		height = maxHeight + 2 * flowerCompositeData.getOffsetSize() + f.getSize();		
	}
}
