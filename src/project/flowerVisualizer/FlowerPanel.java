package project.flowerVisualizer;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class FlowerPanel extends Canvas {

	private int flowersWidth = 0;
	private int flowersHeight = 0;
	private int offset = 0;
	private int miny = 10;
	//TODO: explain that
	private int relationArcCoefficient = 40;
	private ArrayList<FlowerUIComponent> components = new ArrayList<FlowerUIComponent>();
	private ArrayList<FlowerUI> flowers;
	private ArrayList<FlowerRelation> relations; 
	final private Color CONNECTIONCOLOR = new Color(243, 156, 18, 255);
	
	private int scaleX, scaleY, scaleHeight;
	private String scaleMiddleString, scaleUpperString;
	
	/**
	 * Prepares a nice scale to show the number of lines of code involved.
	 * @param x - x coordinate of the bottom of the scale.
	 * @param y - y coordinate of the bottom. (Measured from the bottom of the panel.)
	 * @param height - height from the bottom of the scale to the top.
	 * @param middleText - text to be displayed at the middle of the scale.
	 * @param upperText - text to be displayed at the top of the scale.
	 */
	public void prepareScale(int x, int y, int height, String middleText, String upperText){
		scaleX = x;
		scaleY = y;
		scaleHeight = height;
		scaleMiddleString = middleText;
		scaleUpperString = upperText;
	}
	
	private static final long serialVersionUID = -4877282940745566348L;
	
	/**Required to be executed after all the FlowerComponents are added.
	 * Sets the sizes correctly.
	 */	
	public void init()
	{
		flowersHeight = 200;
		flowers =  new ArrayList<FlowerUI>();
		for (FlowerUIComponent current:components)
			{
			current.init();
			flowersWidth+=current.getWidth();
			if (current.getHeight() > flowersHeight)
				flowersHeight = current.getHeight();
			flowers.addAll(current.getFlowerUIList());
			}
		this.setPreferredSize(new Dimension(flowersWidth, flowersHeight));
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
						
		for (FlowerUIComponent current: components)
		{
			current.paintReuseGraphics(g, offset, Math.max(getHeight()-current.getHeight(), miny));
			offset+=current.getWidth();
		}
		
		//g.drawArc(0, 0, 100, 100, 0, 180);
		
		for (FlowerRelation current: relations)
			drawRelation(current, g);
		
		drawScale(g);
		
		g.dispose();
		g.finalize();
	}
	
	private void drawRelation(FlowerRelation relation, Graphics g)
	{
		//undirected for now
		int leftIndex = Math.min(relation.fromFlower,relation.toFlower);
		int rightIndex = Math.max(relation.fromFlower,relation.toFlower);
		
		Point left = flowers.get(leftIndex).connectionCenter;
		Point right = flowers.get(rightIndex).connectionCenter;
		
		int maxheight = Math.min(left.y, right.y) - (rightIndex-leftIndex) * relationArcCoefficient;
		
		((Graphics2D) g).setStroke(new BasicStroke(relation.connectionWidth));
		g.setColor(CONNECTIONCOLOR);
		g.drawArc(left.x, maxheight, right.x-left.x, (left.y-maxheight)*2, 90, 90);
		g.drawArc(left.x, maxheight, right.x-left.x, (right.y-maxheight)*2, 0, 90);
		
	}
	
	
	public void drawScale(Graphics g){
		//Check for scale not set
		if (scaleHeight == 0 )
			return;
		
		g.setColor(Palette.setTransparency(Palette.MIDNIGHT_BLUE, 200));
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawLine(scaleX, this.getHeight() - scaleY, scaleX, this.getHeight()-scaleY-scaleHeight);
		
		g.drawString(scaleUpperString, scaleX-20, this.getHeight() - scaleY - scaleHeight-g.getFont().getSize());
		
		g.drawString(scaleMiddleString, scaleX-20, this.getHeight() - scaleY - (scaleHeight+g.getFont().getSize())/2);
	}
	
	public void add(FlowerUIComponent toAdd){
		components.add(toAdd);
	}
}