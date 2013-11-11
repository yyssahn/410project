package project.flowerVisualizer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

public class FlowerPanel extends Panel {

	private int flowersWidth = 0;
	private int offset = 0;
	private int miny = 10;
	
	private static final long serialVersionUID = -4877282940745566348L;
	
	/**Required to be executed after all the FlowerComponents are added.
	 * Sets the sizes correctly.
	 */
	public void init()
	{
		for (Component current:getComponents())
			flowersWidth+=current.getWidth();
	}
	
	public void paint(Graphics g)
	{
		
		this.setPreferredSize(new Dimension(flowersWidth, 200));;
		
		offset = Math.max((getWidth() - flowersWidth)/2, 5);
						
		for (Component current: getComponents())
		{
			((FlowerComponent) current).paintReuseGraphics(g, offset, Math.max(getHeight()-current.getHeight(), miny));
			offset+=((FlowerComponent) current).totalWidth;
		}
		
		//g.drawArc(0, 0, 100, 100, 0, 180);
		
		g.dispose();
		g.finalize();
		
	}
}