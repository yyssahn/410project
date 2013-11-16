package project.flowerVisualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.util.ArrayList;

public class FlowerPanel extends Panel {

	private int flowersWidth = 0;
	private int offset = 0;
	private int miny = 10;
	//TODO: explain that
	private int relationArcCoefficient = 40;
	private ArrayList<FlowerRelation> relations; 
	final private Color CONNECTIONCOLOR = new Color(243, 156, 18, 255);
	
	private static final long serialVersionUID = -4877282940745566348L;
	
	/**Required to be executed after all the FlowerComponents are added.
	 * Sets the sizes correctly.
	 */	
	public void init()
	{
		for (Component current:getComponents())
			flowersWidth+=current.getWidth();
		this.setPreferredSize(new Dimension(flowersWidth, 200));
	}
	
	/**
	 * @param relationList - list of relations between the flowers
	 */
	public void addRelations(ArrayList<FlowerRelation> relationList){
		this.relations = relationList;
	};

	public void paint(Graphics g)
	{
		
		//this.setPreferredSize(new Dimension(flowersWidth, 200));
		
		offset = Math.max((getWidth() - flowersWidth)/2, 5);
						
		for (Component current: getComponents())
		{
			((FlowerUIComponent) current).paintReuseGraphics(g, offset, Math.max(getHeight()-current.getHeight(), miny));
			offset+=((FlowerUIComponent) current).totalWidth;
		}
		
		//g.drawArc(0, 0, 100, 100, 0, 180);
		
		for (FlowerRelation current: relations)
			drawRelation(current, g);
		
		g.dispose();
		g.finalize();
		
	}
	
	private void drawRelation(FlowerRelation relation, Graphics g)
	{
		//undirected for now
		int leftIndex = Math.min(relation.fromFlower,relation.toFlower);
		int rightIndex = Math.max(relation.fromFlower,relation.toFlower);
		
		Point left = ((FlowerUIComponent) getComponent(leftIndex)).connectionCenter;
		Point right = ((FlowerUIComponent) getComponent(rightIndex)).connectionCenter;
		
		int maxheight = Math.min(left.y, right.y) - (rightIndex-leftIndex) * relationArcCoefficient;
		
		((Graphics2D) g).setStroke(new BasicStroke(relation.connectionWidth));
		g.setColor(CONNECTIONCOLOR);
		g.drawArc(left.x, maxheight, right.x-left.x, (left.y-maxheight)*2, 90, 90);
		g.drawArc(left.x, maxheight, right.x-left.x, (right.y-maxheight)*2, 0, 90);
		
	}
}